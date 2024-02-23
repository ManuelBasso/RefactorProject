package com.develhope.spring.seller;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleRepository;
import com.develhope.spring.car.VehicleStatus;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.order.OrderRepository;
import com.develhope.spring.order.OrderStatus;
import com.develhope.spring.rent.RentInfo;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<String> createOrderOfAvailableVehicle(Long vehicleId, OrderInfo order){
        try {
            Optional<Vehicle> vehicleToOrder = vehicleRepository.findById(vehicleId);
            if (vehicleToOrder.isPresent() && vehicleToOrder.get().getIsAvailable() == VehicleStatus.AVAILABLE) {
                OrderInfo newOrder = new OrderInfo();
                newOrder.setAdvancePayment(order.getAdvancePayment());
                newOrder.setPaidInFull(order.getPaidInFull());
                newOrder.setOrderStatus(order.getOrderStatus());
                vehicleToOrder.get().setIsAvailable(VehicleStatus.NOT_AVAILABLE);
                orderRepository.save(newOrder);
                return ResponseEntity.ok("Order placed successfully");
            } else if (vehicleToOrder.get().getIsAvailable() != VehicleStatus.AVAILABLE) {
                return ResponseEntity.status(HttpStatusCode.valueOf(406)).body("This vehicle is not available");
            } else if (vehicleToOrder.isEmpty()) {
                return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("There's no vehicle with that ID");
            }
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Something went wrong in the function body");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Something went wrong. Exception launched.");
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
