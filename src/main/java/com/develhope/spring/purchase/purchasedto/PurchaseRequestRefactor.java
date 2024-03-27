package com.develhope.spring.purchase.purchasedto;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.user.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestRefactor {

    private Double totalPrice;
    private OrderInfo order;
    private Long idCustomer;
    private Long idSeller;
    private Long idVehicle;
}