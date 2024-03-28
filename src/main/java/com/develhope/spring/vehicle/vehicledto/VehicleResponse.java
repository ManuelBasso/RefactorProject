package com.develhope.spring.vehicle.vehicledto;

import com.develhope.spring.vehicle.VehicleStatus;
import com.develhope.spring.vehicle.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponse {
    private Long vehicleId;
    private String brand;
    private String model;
    private String color;
    private String gearboxType;
    private String fuelType;
    private String accessories;

    private Integer displacement;
    private Integer power;
    private Integer yearOfRegistration;

    private Double price;
    private Double discount;
    private Boolean isNew;
    private VehicleType vehicleType;
    private VehicleStatus isAvailable;

}
