package com.develhope.spring.order;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.customer.Customer;
import com.develhope.spring.seller.Seller;
import com.develhope.spring.user.User;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Double advancePayment;               //Anticipo

    private Boolean paidInFull;                  //Flag pagato

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;             //Stato ordine

    @OneToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller_id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer_id;

    /*@ManyToMany
    @JoinTable(
            name = "ORDER_VEHICLE",
            joinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "VEHICLE_ID", referencedColumnName = "vehicleId"))
    private List<Vehicle> vehicles;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;*/

}