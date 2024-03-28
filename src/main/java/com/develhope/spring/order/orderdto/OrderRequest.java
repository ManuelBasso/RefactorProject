package com.develhope.spring.order.orderdto;

import com.develhope.spring.vehicle.Vehicle;
import com.develhope.spring.order.OrderStatus;
import com.develhope.spring.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private Double advancePayment;
    private Boolean paidInFull;
    private User customer;
    private User seller;
    private Vehicle vehicle;
    private OrderStatus orderStatus;
}
