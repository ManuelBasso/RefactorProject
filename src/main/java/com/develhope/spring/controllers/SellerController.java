package com.develhope.spring.controllers;

import com.develhope.spring.entities.StatusOfVehicle.OrderInfo;
import com.develhope.spring.entities.StatusOfVehicle.OrderStatus;
import com.develhope.spring.entities.StatusOfVehicle.RentInfo;
import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.entities.vehicleTypes.VehicleStatus;
import com.develhope.spring.repositories.OrderRepository;
import com.develhope.spring.repositories.VehicleRepository;
import com.develhope.spring.services.SellerService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SellerService sellerService;

    @GetMapping("/vehicleinfo/{id}")
    public @ResponseBody Vehicle getOneVehicle(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            return vehicle.get();
        } else {
            return null;
        }
    }

    @PostMapping("/createorder") //Is @ReqBody for Vehicle actually needed?
    public @ResponseBody OrderInfo createOrder(@RequestParam Long vehicleId, @RequestBody OrderInfo order, @RequestBody Vehicle vehicle) {

        if (vehicleId == vehicle.getId() && vehicle.getIsAvailable() == VehicleStatus.ORDERABLE) {
            return orderRepository.save(order);
        }
        return null;
    }

    @GetMapping("/order/{id}")
    public @ResponseBody OrderInfo getOneOrder(@PathVariable Long id) {
        Optional<OrderInfo> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            return null;
        }
    }

    @PutMapping("/order/{id}")
    public @ResponseBody OrderInfo updateOrder(@PathVariable Long id, @RequestBody OrderInfo order){
        order.setId(id);
        return orderRepository.save(order);
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);  //Do vehicle properties need to be changed?
    }

    @GetMapping("/orderstatus/{id}")
    public @ResponseBody Enum<OrderStatus> getOrderStatus(@PathVariable Long id){
        OrderInfo order = getOneOrder(id);
        return order.getOrderStatus();
    }

    @PutMapping("/orderstatus/{id}")     //???????? check enum? service? orderstatus
    public OrderInfo updateOrderStatus(@PathVariable Long id, @RequestParam Enum<OrderStatus> newOrderStatus){
        return sellerService.setOrderStatusToDelivered(id, newOrderStatus);
    }

    /*@GetMapping("/order/get{orderstatus}") //loop?? id?? getAll ; need to use sql queries to loop orderRepository
    public @ResponseBody List<OrderInfo> getAllOrdersByOrderStatus(@PathVariable String orderstatus) {
        return sellerService.getAllOrdersByStatus(orderstatus);
    }*/

    /*@PostMapping("/createrentorder") //???????
    public @ResponseBody RentInfo rentOrder(@RequestBody RentInfo rentInfo, @RequestParam Long vehicleId){
        Vehicle vehicle = getOneVehicle(vehicleId);
        if (vehicle != null && vehicle.getIsAvailable() == VehicleStatus.REANTABLE) {
            return orderRepository.save(rentInfo);
        }
        return null;
    }*/
}

