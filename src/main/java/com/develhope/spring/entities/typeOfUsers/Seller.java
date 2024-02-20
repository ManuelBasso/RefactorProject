package com.develhope.spring.entities.typeOfUsers;

import java.util.List;

import com.develhope.spring.entities.StatusOfVehicle.OrderInfo;
import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.utilities.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Seller extends User {

    private Role role = Role.ROLE_SELLER;
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "seller_vehicle", joinColumns = @JoinColumn(name = "seller_id"), inverseJoinColumns = @JoinColumn(name = "Vehicle_id"))
    private List<Vehicle> vehicle;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "seller_vehicle", joinColumns = @JoinColumn(name = "seller_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<OrderInfo> order;
}
