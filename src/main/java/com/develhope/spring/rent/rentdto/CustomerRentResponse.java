package com.develhope.spring.rent.rentdto;

import com.develhope.spring.vehicle.vehicledto.VehicleResponse;
import com.develhope.spring.user.userdto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRentResponse {

    private Long rentId;

    private String startDate;
    private String endDate;
    private Double dailyCost;
    private Double totalCost;
    private Boolean isPaid;
    private VehicleResponse vehicle;
    private UserResponse customer;

}
