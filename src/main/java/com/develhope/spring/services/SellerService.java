package com.develhope.spring.services;

import com.develhope.spring.entities.StatusOfVehicle.OrderInfo;
import com.develhope.spring.entities.StatusOfVehicle.OrderStatus;
import com.develhope.spring.entities.StatusOfVehicle.RentInfo;
import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.entities.vehicleTypes.VehicleStatus;
import com.develhope.spring.repositories.OrderRepository;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderInfo setOrderStatusToDelivered(Long orderId, Enum<OrderStatus> newOrderStatus){
        OrderInfo order = orderRepository.getById(orderId);
        if(newOrderStatus.equals(OrderStatus.DELIVERED)) {
            order.setOrderStatus(newOrderStatus);
            return orderRepository.save(order);
        }else {
            return order;
        }
    }

    /*public List<OrderInfo> getAllOrdersByStatus(String orderStatus){   //Da capire come fare loop sul repo
        if (EnumUtils.isValidEnum(OrderStatus.class, orderStatus)) {
            ArrayList<OrderInfo> sortedOrders = new ArrayList<>();
            for (OrderInfo order : orderRepository) {
                sortedOrders.add(order);
            }
        }
    }*/
}
