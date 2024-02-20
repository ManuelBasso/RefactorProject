package com.develhope.spring.entities.typeOfUsers;

import java.util.List;

import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.utilities.Role;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table

public class Admin extends User {

    private Role role = Role.ROLE_ADMIN;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "administrator_vehicle", joinColumns = @JoinColumn(name = "administrator_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicle;

}
