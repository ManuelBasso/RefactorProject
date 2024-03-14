package com.develhope.spring.order.orderdto;

import com.develhope.spring.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderRequest {

    private Double advancePayment;
    private Boolean paidInFull;
    private Long IdVehicle;
}
