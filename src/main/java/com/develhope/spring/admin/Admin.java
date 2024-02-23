package com.develhope.spring.admin;

import java.util.List;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.configurations.Role;

import com.develhope.spring.user.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "Admins")
public class Admin extends User {

    @SuppressWarnings("unused")
    private Role role = Role.ROLE_ADMIN;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "administrator_vehicle", joinColumns = @JoinColumn(name = "administrator_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicle;
}
