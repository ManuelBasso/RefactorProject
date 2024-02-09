package com.develhope.spring.entities.typeOfUsers;

import com.develhope.spring.configurations.Role;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table
@Entity

public class Admin extends User {
    Role role = Role.ROLE_ADMIN;
    
}
