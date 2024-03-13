package com.develhope.spring.rent.rentdto;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.user.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentRequestRefactor {

    private String startDate;
    private String endDate;
    private Double dailyCost;
    private Double totalCost;
    private Boolean isPaid;
    private Long idVehicle;
    private Long idCustomer;
    private Long idSeller;
}
