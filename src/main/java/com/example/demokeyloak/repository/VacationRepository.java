package com.example.demokeyloak.repository;

import com.example.demokeyloak.entity.Users;
import com.example.demokeyloak.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Integer> {
}
