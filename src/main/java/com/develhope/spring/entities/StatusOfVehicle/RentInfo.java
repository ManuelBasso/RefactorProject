package com.develhope.spring.entities.StatusOfVehicle;

import com.develhope.spring.entities.vehicleTypes.Vehicle;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class RentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  @ManyToOne
    //@JoinColumn(name = "vehicle_id")
    //private Vehicle vehicle;

    private LocalDate startDate; // Data inizio noleggio
    private LocalDate endDate;   // Data fine noleggio
    private double dailyCost;    // Costo giornaliero noleggio
    private double totalCost;    // Costo totale noleggio
    private boolean paid;        // Flag pagato
}