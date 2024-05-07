package com.example.demokeyloak.service.serviceImpl;

import com.example.demokeyloak.entity.Booking;
import com.example.demokeyloak.entity.Users;
import com.example.demokeyloak.entity.Vacation;
import com.example.demokeyloak.repository.BookingRepository;
import com.example.demokeyloak.repository.UserRepository;
import com.example.demokeyloak.repository.VacationRepository;
import com.example.demokeyloak.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;
    private final UserRepository repository2;
    private final VacationRepository repository3;

    @Override
    public Booking book() {
        Booking booking = new Booking();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwtToken = (Jwt) authentication.getPrincipal();
        String username = jwtToken.getClaimAsString("preferred_username");
        String email = jwtToken.getClaimAsString("email");

        Users users = repository2.findByEmail(email);

        if (users == null) {
            users = Users.builder().name(username).email(email).totalVacationBalance(2).build();
            repository2.save(users);
        }
        if (users.getTotalVacationBalance() == 0) {
            throw new RuntimeException("You don't have days vacation");
        }
        booking.setUser(users);
        Vacation vacation = Vacation.builder().startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .build();
        vacation.setNumberOfDays(Period.between(vacation.getStartDate(), vacation.getEndDate()).getDays() + 1);

        if (users.getTotalVacationBalance() < vacation.getNumberOfDays()) {
            throw new RuntimeException("The number of days is over of total vacation");
        }
        repository3.save(vacation);
        booking.setVacation(vacation);
        return repository.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return repository.findAll();
    }

    @Override
    public Booking submit(int id) {
        Booking booking = repository.findById(id).get();
        Users users = repository2.findById(booking.getUser().getId()).get();
        users.setTotalVacationBalance(users.getTotalVacationBalance() - booking.getVacation().getNumberOfDays());
        repository2.save(users);
        booking.setUser(users);
        booking.setStutas(true);
        return repository.save(booking);

    }
}
