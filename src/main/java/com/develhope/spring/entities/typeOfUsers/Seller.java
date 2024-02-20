package com.develhope.spring.entities.typeOfUsers;

import com.develhope.spring.configurations.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@PrimaryKeyJoinColumn(name = "sellerId")
@Table(name = "Sellers")
public class Seller extends User {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;*/

    private Role role = Role.ROLE_SELLER;
    private String phoneNumber;
}
