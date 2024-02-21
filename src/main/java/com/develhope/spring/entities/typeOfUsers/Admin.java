package com.develhope.spring.entities.typeOfUsers;

import com.develhope.spring.configurations.Role;

import com.develhope.spring.entities.vehicleTypes.Vehicle;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "adminId")
@Table(name = "Admins")
public class Admin extends User {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;*/

    private Role role = Role.ROLE_ADMIN;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "administrator_vehicle", joinColumns = @JoinColumn(name = "administrator_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicle;

    
}
