package com.develhope.spring.seller;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.configurations.Role;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.rent.RentInfo;
import com.develhope.spring.user.User;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Sellers")
public class Seller extends User {

    private Role role = Role.ROLE_SELLER;
    private String phoneNumber;

    @OneToMany(mappedBy = "seller_id", cascade = CascadeType.ALL)
    private List<OrderInfo> orderInfoListCustomer;

    @OneToMany(mappedBy = "seller_id", cascade = CascadeType.ALL)
    private List<RentInfo> rentInfoListCustomer;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "seller_vehicle",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "seller_order",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<OrderInfo> orders;*/
}
