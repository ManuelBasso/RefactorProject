package com.develhope.spring.entities.StatusOfVehicle;

import com.develhope.spring.entities.vehicleTypes.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  @ManyToOne
    //@JoinColumn(name = "vehicle_id")
    //private Vehicle vehicle;

    private OffsetDateTime startDate; // Data inizio noleggio
    private OffsetDateTime endDate;   // Data fine noleggio
    private Double dailyCost;    // Costo giornaliero noleggio
    private Double totalCost;   // Costo totale noleggio
    private Boolean isPaid;      // Flag pagato

    //TODO totalCost should be calculated by the program, not the user

    /*public RentInfo(Double totalCost) {
        this.totalCost = calculateTotalCost(dailyCost, startDate, endDate);
    }

    private Double calculateTotalCost(Double dailyCost, OffsetDateTime startDate, OffsetDateTime endDate){
        Double rentHourlyCost = dailyCost/24;
        Duration timeDifference = Duration.between(startDate, endDate);
        Long rentTimeInHours = timeDifference.toHours();  //Payment is calculated based on whole hours of rent time
        return rentHourlyCost * rentTimeInHours;
    }*/
}