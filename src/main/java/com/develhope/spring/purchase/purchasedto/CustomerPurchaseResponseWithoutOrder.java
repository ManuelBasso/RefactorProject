package com.develhope.spring.purchase.purchasedto;


import com.develhope.spring.user.userdto.UserResponse;
import com.develhope.spring.vehicle.vehicledto.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPurchaseResponseWithoutOrder {
    private Long purchaseId;
    private Double totalPrice;
    private UserResponse customer;
    private VehicleResponse vehicle;
}