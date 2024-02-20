package com.develhope.spring.entities.typeOfUsers;

import com.develhope.spring.configurations.Role;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "adminId")
@Table(name = "Admins")
public class Admin extends User {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;*/

    private Role role = Role.ROLE_ADMIN;

    
}
