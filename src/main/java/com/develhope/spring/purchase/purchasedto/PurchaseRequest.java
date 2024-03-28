package com.develhope.spring.purchase.purchasedto;

import com.develhope.spring.vehicle.Vehicle;
import com.develhope.spring.order.Order;
import com.develhope.spring.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {
    private Double totalPrice;
    private Order order;
    private User customer;
    private Vehicle vehicle;
}
