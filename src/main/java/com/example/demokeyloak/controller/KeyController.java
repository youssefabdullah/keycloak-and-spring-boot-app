package com.example.demokeyloak.controller;

import com.example.demokeyloak.entity.Booking;
import com.example.demokeyloak.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class KeyController {
    @Autowired
    private BookingService service;

    @GetMapping("/admin/getAllBooking")
    public List<Booking> getAdminDetails() {
        return service.findAll().stream()
                .filter(booking -> book().getStutas()==null)
                .collect(Collectors.toList());
    }

    @PostMapping("/admin/submit/{id}")
    public Booking submit(@PathVariable Integer id) {
        return service.submit(id);
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUserDetails() {

        return ResponseEntity.ok("Hello user");
    }

    @PostMapping("/user/book")
    public Booking book() {
        return service.book();
    }

}
