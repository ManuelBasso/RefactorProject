package com.develhope.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.develhope.spring.entities.vehicleTypes.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}