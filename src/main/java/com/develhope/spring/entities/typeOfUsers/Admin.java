package com.develhope.spring.entities.typeOfUsers;

import com.develhope.spring.configurations.Role;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    Role role = Role.ROLE_ADMIN;
    
}
