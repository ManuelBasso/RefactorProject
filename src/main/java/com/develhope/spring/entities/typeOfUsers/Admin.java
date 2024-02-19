package com.develhope.spring.entities.typeOfUsers;

import com.develhope.spring.utilities.Role;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Admin extends User {

    private Role role = Role.ROLE_ADMIN;
    
}
