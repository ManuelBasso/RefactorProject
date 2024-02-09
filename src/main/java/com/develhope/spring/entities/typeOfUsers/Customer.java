package com.develhope.spring.entities.typeOfUsers;

import com.develhope.spring.configurations.Role;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "customers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User {

    Role role = Role.ROLE_CUSTOMER;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

}
