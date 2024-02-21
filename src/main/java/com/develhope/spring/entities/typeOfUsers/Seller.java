package com.develhope.spring.entities.typeOfUsers;

import com.develhope.spring.configurations.Role;
import com.develhope.spring.entities.StatusOfVehicle.OrderInfo;
import com.develhope.spring.entities.vehicleTypes.Vehicle;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Sellers")
public class Seller extends User {

    private Role role = Role.ROLE_SELLER;
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "seller_vehicle",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "seller_order",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<OrderInfo> orders;
}
