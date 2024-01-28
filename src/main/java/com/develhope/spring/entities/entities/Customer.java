package com.develhope.spring.entities.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class Customer {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;


    public Customer(String firstName, String lastName, String phoneNumber, String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
}





