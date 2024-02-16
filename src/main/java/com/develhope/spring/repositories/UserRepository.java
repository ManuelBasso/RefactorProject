package com.develhope.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.develhope.spring.entities.typeOfUsers.User;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

}