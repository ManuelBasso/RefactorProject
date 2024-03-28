package com.develhope.spring.rent.rentdto;

import com.develhope.spring.vehicle.Vehicle;
import com.develhope.spring.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentResponse {

    private Long rentId;

    private String startDate;
    private String endDate;
    private Double dailyCost;
    private Double totalCost;
    private Boolean isPaid;
    private Vehicle vehicle;
    private User customer;
    private User seller;

}
