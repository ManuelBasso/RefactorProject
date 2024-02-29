package com.develhope.spring.customer;

import com.develhope.spring.configurations.Role;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.rent.RentInfo;
import com.develhope.spring.user.User;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
//@PrimaryKeyJoinColumn(name = "customerId")
//@Table(name = "Customers")
@Getter
@Setter
public class Customer extends User {

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_CUSTOMER;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer_id", cascade = CascadeType.ALL)
    private List<OrderInfo> orderInfoListCustomer;

    @OneToMany(mappedBy = "customer_id", cascade = CascadeType.ALL)
    private List<RentInfo> rentInfoListCustomer;

}
