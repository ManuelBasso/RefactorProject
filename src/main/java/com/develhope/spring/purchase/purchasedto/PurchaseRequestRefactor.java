package com.develhope.spring.purchase.purchasedto;

import com.develhope.spring.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestRefactor {

    private Double totalPrice;
    private Long order;
    private Long idCustomer;
    private Long idSeller;
    private Long idVehicle;
}