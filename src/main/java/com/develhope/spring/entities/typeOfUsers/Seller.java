package com.develhope.spring.entities.typeOfUsers;

import com.develhope.spring.configurations.Role;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "sellers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends User {

    Role role = Role.ROLE_SELLER;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String phoneNumber;
}
