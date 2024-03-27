package com.develhope.spring.order.orderdto;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.cardto.VehicleResponse;
import com.develhope.spring.order.OrderStatus;
import com.develhope.spring.user.Users;
import com.develhope.spring.user.userdto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderResponse {
    private Long orderId;
    private Double advancePayment;
    private Boolean paidInFull;
    private UserResponse customer;
    private VehicleResponse vehicle;
    private OrderStatus orderStatus;
}
