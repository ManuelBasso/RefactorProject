package com.develhope.spring.rent;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "Rents")
@AllArgsConstructor
@NoArgsConstructor
public class RentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentId;
    //not able to create table using OffsetDateTime
   // private OffsetDateTime startDate; // Data inizio noleggio
   // private OffsetDateTime endDate;   // Data fine noleggio
    private Double dailyCost;    // Costo giornaliero noleggio
    private Double totalCost;   // Costo totale noleggio
    private Boolean isPaid;      // Flag pagato

    @ManyToMany
    @JoinTable(
            name="RENT_VEHICLE",
            joinColumns=@JoinColumn(name="RENT_ID", referencedColumnName="rentId"),
            inverseJoinColumns=@JoinColumn(name="VEHICLE_ID", referencedColumnName="vehicleId"))
    private List<Vehicle> vehicles;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;


    //TODO totalCost should be calculated by the program, not the user

    /*
     * public RentInfo(Double totalCost) {
     * this.totalCost = calculateTotalCost(dailyCost, startDate, endDate);
     * }
     * 
     * private Double calculateTotalCost(Double dailyCost, OffsetDateTime startDate,
     * OffsetDateTime endDate){
     * Double rentHourlyCost = dailyCost/24;
     * Duration timeDifference = Duration.between(startDate, endDate);
     * Long rentTimeInHours = timeDifference.toHours(); //Payment is calculated
     * based on whole hours of rent time
     * return rentHourlyCost * rentTimeInHours;
     * }
     */
}