package com.develhope.spring.controllers;

import com.develhope.spring.entities.StatusOfVehicle.OrderInfo;
import com.develhope.spring.entities.StatusOfVehicle.OrderStatus;
import com.develhope.spring.entities.StatusOfVehicle.RentInfo;
import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.entities.vehicleTypes.VehicleStatus;
import com.develhope.spring.repositories.OrderRepository;
import com.develhope.spring.repositories.VehicleRepository;
import com.develhope.spring.services.RentService;
import com.develhope.spring.services.SellerService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleBiFunction;

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

    @Autowired
    private RentService rentService;

    //TODO Control null cases, wrong inputs etc...

    @GetMapping("/vehicleinfo/{id}")
    public Optional<Vehicle> getOneVehicle(@PathVariable Long id) {
        return sellerService.getOneVehicleById(id);
    }

    @PostMapping("/createorder/{vehicleId}")
    public ResponseEntity<String> createOrder(@PathVariable Long vehicleId, @RequestBody OrderInfo newOrder) {
        return sellerService.createOrderOfAvailableVehicle(vehicleId, newOrder);
    }

    @DeleteMapping("/deleteorder/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);  //Do vehicle properties need to be changed?
    }

    @PutMapping("/modifyorder/{idOrderToModify}")
    public @ResponseBody OrderInfo modifyOrder(@PathVariable Long idOrderToModify, @RequestBody OrderInfo modifiedOrder){
        return sellerService.modifyOrder(idOrderToModify, modifiedOrder);
    }

    @GetMapping("/orderstatus/{orderId}")
    public @ResponseBody OrderStatus getOrderStatus(@PathVariable Long orderId){
        return sellerService.getOrderStatus(orderId);
    }

    @PatchMapping("/updateorderstatus/{orderId}/{newStatus}")
    public OrderInfo updateOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatus newStatus){
        return sellerService.updateOrderStatus(orderId, newStatus);
    }

    //TODO Verificare tutti gli ordini filtrati per uno stato

    @PostMapping("/createrentorder")
    public RentInfo createRentOrder(@RequestBody RentInfo newRentOrder){
        return rentService.createRent(newRentOrder);
    }

    @DeleteMapping("/deleterentorder/{rentId}")
    public void deleteRentOrder(@PathVariable Long rentId){
        rentService.deleteRent(rentId);
    }

    @PutMapping("/modifyrentorder/{orderId}")
    public RentInfo modifyRentOrder(@PathVariable Long orderId, @RequestBody RentInfo updatedRentOrder){
        return rentService.updateRent(orderId, updatedRentOrder);
    }

    /*@GetMapping("/getordersbystatus")
    public List<OrderInfo> getOrdersByStatus(@RequestParam (required = true) String status) {
        return sellerService.getAllOrdersByStatusParams(status);
    }*/






    /*@GetMapping("/order/{id}")
    public @ResponseBody OrderInfo getOneOrder(@PathVariable Long id) {
        Optional<OrderInfo> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            return null;
        }
    }*/

    /*@PutMapping("/orderstatus/{id}")     //???????? check enum? service? orderstatus
    public OrderInfo updateOrderStatus(@PathVariable Long id, @RequestParam Enum<OrderStatus> newOrderStatus){
        return sellerService.setOrderStatusToDelivered(id, newOrderStatus);
    }*/

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

