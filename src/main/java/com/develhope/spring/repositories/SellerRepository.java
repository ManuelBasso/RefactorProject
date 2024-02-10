package com.develhope.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.develhope.spring.entities.typeOfUsers.Seller;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {

    
} 