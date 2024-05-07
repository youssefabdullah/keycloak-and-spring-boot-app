package com.example.demokeyloak.service;

import com.example.demokeyloak.entity.Booking;

import java.util.List;

public interface BookingService {
    public Booking book();
    public List<Booking> findAll();
    Booking submit(int id);
}
