package com.develhope.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.develhope.spring.entities.typeOfUsers.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}