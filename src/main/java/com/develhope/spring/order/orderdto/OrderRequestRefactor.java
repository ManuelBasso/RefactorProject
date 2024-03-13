package com.develhope.spring.order.orderdto;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.order.OrderStatus;
import com.develhope.spring.user.Users;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestRefactor {

    private Double advancePayment;
    private Boolean paidInFull;
    private Long IdCustomer;
    private Long IdSeller;
    private Long IdVehicle;
    private OrderStatus orderStatus;
}
