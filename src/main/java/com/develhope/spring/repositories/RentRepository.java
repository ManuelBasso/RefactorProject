package com.develhope.spring.repositories;

import com.develhope.spring.entities.StatusOfVehicle.RentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<RentInfo, Long> {
}