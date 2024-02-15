package com.develhope.spring.entities.StatusOfVehicle;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table
public class RentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleId;

    private LocalDateTime rentStartTime;
    private LocalDateTime rentEndTime;
    private Double rentDailyCost;
    private Double rentTotalCost;
    private Boolean rentPaidInFull;
    
}
