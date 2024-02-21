package com.develhope.spring.entities.typeOfUsers;

import com.develhope.spring.utilities.Role;

import jakarta.persistence.*;
import lombok.*;

@Entity
@PrimaryKeyJoinColumn(name = "customerId")
@Table(name = "Customers")
public class Customer extends User {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;*/

    private Role role = Role.ROLE_CUSTOMER;
    private String phoneNumber;

}
