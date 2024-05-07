package com.example.demokeyloak.repository;

import com.example.demokeyloak.entity.Booking;
import com.example.demokeyloak.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
