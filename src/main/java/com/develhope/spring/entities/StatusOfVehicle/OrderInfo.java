package com.develhope.spring.entities.StatusOfVehicle;

import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.entities.vehicleTypes.VehicleStatus;
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

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle newVehicle;*/
    //private Long vehicleId;                      //ID del veicolo da ordinare

    /*@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORDERED_VEHICLE_ID")
    private Vehicle orderedVehicle;*/

    @ManyToMany
    @JoinTable(
            name="ORDER_VEHICLE",
            joinColumns=@JoinColumn(name="ORDER_ID", referencedColumnName="orderId"),
            inverseJoinColumns=@JoinColumn(name="VEHICLE_ID", referencedColumnName="vehicleId"))
    private List<Vehicle> vehicles;

    private Double advancePayment;               //Anticipo

    private Boolean paidInFull;                  //Flag pagato

    /*@Enumerated(EnumType.STRING)
    private VehicleOrderStatus vehicleOrderStatus;*/         //Veicolo ordinato/acquistato. E' necessario?

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;             //Stato ordine

}
