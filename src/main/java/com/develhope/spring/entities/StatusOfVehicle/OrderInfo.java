package com.develhope.spring.entities.StatusOfVehicle;

import com.develhope.spring.entities.typeOfUsers.User;
import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.utilities.OrderStatus;
import com.develhope.spring.utilities.VehicleOrderStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // private Long vehicleId; //ID del veicolo da ordinare

    private Double advancePayment; // Anticipo

    private Boolean paidInFull; // Flag pagato

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private VehicleOrderStatus vehicleOrderStatus; // Veicolo ordinato/acquistato

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // Stato ordine

}