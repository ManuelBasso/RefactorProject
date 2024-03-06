package com.develhope.spring.repositories;

import com.develhope.spring.entities.StatusOfVehicle.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
