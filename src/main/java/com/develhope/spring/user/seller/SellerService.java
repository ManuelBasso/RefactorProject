package com.develhope.spring.user.seller;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleRepository;
import com.develhope.spring.car.VehicleStatus;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.order.OrderRepository;
import com.develhope.spring.order.OrderStatus;

import com.develhope.spring.role.Role;
import com.develhope.spring.user.UserRepository;
import com.develhope.spring.user.Users;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Vehicle> getOneVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public OrderInfo getOneOrderById(Long id) {
        return orderRepository.getById(id);
    }

    public ResponseEntity<String> createOrderOfAvailableVehicle(Users seller, Long customerId, Long vehicleId, OrderInfo order) {
        try {
            Optional<Vehicle> vehicleToOrder = vehicleRepository.findById(vehicleId);
            Optional<Users> supposedCustomer = userRepository.findById(customerId);
            Boolean checkCustomer = checkRole(supposedCustomer, Role.RoleType.ROLE_CUSTOMER);

            if (supposedCustomer.isEmpty() || !checkCustomer){
                return ResponseEntity.status(HttpStatusCode.valueOf(403)).body("Invalid customer ID");
            } else if(vehicleToOrder.isPresent() && vehicleToOrder.get().getIsAvailable() == VehicleStatus.AVAILABLE) {
                OrderInfo newOrder = new OrderInfo();
                newOrder.setAdvancePayment(order.getAdvancePayment());
                newOrder.setPaidInFull(order.getPaidInFull());
                newOrder.setOrderStatus(order.getOrderStatus());
                newOrder.setCustomer(supposedCustomer.get());
                newOrder.setSeller(seller);
                newOrder.setVehicle(vehicleToOrder.get());
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

    public OrderInfo modifyOrder(Long orderId, OrderInfo newOrder) {
        OrderInfo orderToModify = orderRepository.getById(orderId);
        if (orderToModify != null) {
            orderToModify.setAdvancePayment(newOrder.getAdvancePayment());
            orderToModify.setPaidInFull(newOrder.getPaidInFull());
            orderToModify.setOrderStatus(newOrder.getOrderStatus());
            orderRepository.save(orderToModify);
            return orderToModify;
        }
        return null;
    }

    public ResponseEntity<String> getOrderStatus(Long orderId) {
        Optional<OrderInfo> checkOrder = orderRepository.findById(orderId);
        if(checkOrder.isEmpty()){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Order with ID[" +orderId +"] doesn't exist");
        }
        return ResponseEntity.ok("The status of order ID[" +orderId +"] is [" +checkOrder.get().getOrderStatus() +"]");
    }

    public ResponseEntity<String> updateOrderStatus(Long orderId, String newOrderStatus) {
        OrderInfo orderToUpdateStatus = orderRepository.getById(orderId);
        boolean checkStatus = EnumUtils.isValidEnum(OrderStatus.class, newOrderStatus);
        if(!checkStatus){
            return ResponseEntity.status(HttpStatusCode.valueOf(406)).body("[" +newOrderStatus +"] is not a valid Order status");
        }
        OrderStatus status = orderRepository.getById(orderId).getOrderStatus();
        if(orderToUpdateStatus == null){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Order with ID[" +orderId +"] doesn't exist");
        } else if (EnumUtils.isValidEnum(OrderStatus.class, newOrderStatus.toString())) {
            orderToUpdateStatus.setOrderStatus(status);
            orderRepository.save(orderToUpdateStatus);
            return ResponseEntity.ok("Status changed successfully");
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Something went wrong");
    }

    /*
     * public OrderInfo setOrderStatusToDelivered(Long orderId, Enum<OrderStatus>
     * newOrderStatus){
     * OrderInfo order = orderRepository.getById(orderId);
     * if(newOrderStatus.equals(OrderStatus.DELIVERED)) {
     * order.setOrderStatus(newOrderStatus);
     * return orderRepository.save(order);
     * }else {
     * return order;
     * }
     * }
     */

    /*
     * public List<OrderInfo> getAllOrdersByStatus(String orderStatus){ //Da capire
     * come fare loop sul repo
     * if (EnumUtils.isValidEnum(OrderStatus.class, orderStatus)) {
     * ArrayList<OrderInfo> sortedOrders = new ArrayList<>();
     * for (OrderInfo order : orderRepository) {
     * sortedOrders.add(order);
     * }
     * }
     * }
     */

    //Utilities

    public Boolean checkRole(Optional<Users> user, Role.RoleType roleUser) {
        boolean check = false;
        if (user.isPresent()) {
            for (Role role : user.get().getRole()) {
                if (role.getName().equals(roleUser)) {
                    check = true;
                }
            }
        }
        return check;
    }
}
