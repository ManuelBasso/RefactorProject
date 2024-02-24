package com.develhope.spring.customer;

import com.develhope.spring.configurations.Role;
import com.develhope.spring.user.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@PrimaryKeyJoinColumn(name = "customerId")
@Table(name = "Customers")
@Getter
@Setter
public class Customer extends User {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;*/

    private Role role = Role.ROLE_CUSTOMER;
    private String phoneNumber;

}
