package com.develhope.spring.entities.StatusOfVehicle;

import com.develhope.spring.entities.vehicleTypes.VehicleStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long vehicleId;             //ID del veicolo
    private Double advancePayment;      //Anticipo
    private Boolean paidInFull;         //Flag pagato
    //private Boolean orderStatus;        //Stato ordine
    //private Boolean vehicleStatus;      //Veicolo ordinato/acquistato [noleggiato????]
    private Enum<VehicleStatus> vehicleStatus;
    private Enum<OrderStatus> orderStatus;

}
