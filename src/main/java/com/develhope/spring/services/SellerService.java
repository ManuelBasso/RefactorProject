package com.develhope.spring.services;

import com.develhope.spring.entities.StatusOfVehicle.OrderInfo;
import com.develhope.spring.entities.StatusOfVehicle.RentInfo;
import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.repositories.OrderRepository;
import com.develhope.spring.repositories.VehicleRepository;
import com.develhope.spring.utilities.OrderStatus;
import com.develhope.spring.utilities.VehicleStatus;

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

    @Autowired
    private VehicleRepository vehicleRepository;

    public Optional<Vehicle> getOneVehicleById(Long id){
        return vehicleRepository.findById(id);
    }

    public OrderInfo getOneOrderById(Long id){
        return orderRepository.getById(id);
    }

    public void createOrderOfAvailableVehicle(Long vehicleId, OrderInfo newOrder){
        Optional<Vehicle> vehicleToOrder = vehicleRepository.findById(vehicleId);
        if (vehicleToOrder.isPresent() && vehicleToOrder.get().getIsAvailable() == VehicleStatus.AVAILABLE) {
            orderRepository.save(newOrder);
        }
    }

    public OrderInfo modifyOrder(Long orderId, OrderInfo newOrder){
        OrderInfo orderToModify = orderRepository.getById(orderId);
        if(orderToModify != null){
            orderToModify.setAdvancePayment(newOrder.getAdvancePayment());
            orderToModify.setPaidInFull(newOrder.getPaidInFull());
            orderRepository.save(orderToModify);
            return orderToModify;
        }
        return null;
    }

    public OrderStatus getOrderStatus(Long orderId){
        return orderRepository.getById(orderId).getOrderStatus();
    }

    public OrderInfo updateOrderStatus(Long orderId, OrderStatus newOrderStatus){
        OrderInfo orderToUpdateStatus = orderRepository.getById(orderId);
        if(EnumUtils.isValidEnum(OrderStatus.class, newOrderStatus.toString())){
            orderToUpdateStatus.setOrderStatus(newOrderStatus);
            orderRepository.save(orderToUpdateStatus);
        }
        return orderToUpdateStatus;
    }

    /*public OrderInfo setOrderStatusToDelivered(Long orderId, Enum<OrderStatus> newOrderStatus){
        OrderInfo order = orderRepository.getById(orderId);
        if(newOrderStatus.equals(OrderStatus.DELIVERED)) {
            order.setOrderStatus(newOrderStatus);
            return orderRepository.save(order);
        }else {
            return order;
        }
    }*/

    /*public List<OrderInfo> getAllOrdersByStatus(String orderStatus){   //Da capire come fare loop sul repo
        if (EnumUtils.isValidEnum(OrderStatus.class, orderStatus)) {
            ArrayList<OrderInfo> sortedOrders = new ArrayList<>();
            for (OrderInfo order : orderRepository) {
                sortedOrders.add(order);
            }
        }
    }*/
}
