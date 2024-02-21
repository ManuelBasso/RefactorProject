package com.develhope.spring.entities.StatusOfVehicle;

import com.develhope.spring.entities.typeOfUsers.User;
import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.utilities.OrderStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Orders")
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToMany
    @JoinTable(
            name="ORDER_VEHICLE",
            joinColumns=@JoinColumn(name="ORDER_ID", referencedColumnName="orderId"),
            inverseJoinColumns=@JoinColumn(name="VEHICLE_ID", referencedColumnName="vehicleId"))
    private List<Vehicle> vehicles;

    private Double advancePayment;               //Anticipo

    private Boolean paidInFull;                  //Flag pagato

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;             //Stato ordine

}