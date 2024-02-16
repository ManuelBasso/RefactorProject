package com.develhope.spring.entities.typeOfUsers;

import com.develhope.spring.configurations.Role;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Seller extends User {

    private Role role = Role.ROLE_SELLER;
    private String phoneNumber;
}
