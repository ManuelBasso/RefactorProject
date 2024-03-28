package com.develhope.spring.purchase.purchasedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPurchaseRequest {

    private Double totalPrice;
    private Long idOrder;
    private Long idVehicle;
}
