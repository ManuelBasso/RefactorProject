package com.develhope.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.develhope.spring.entities.typeOfUsers.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}