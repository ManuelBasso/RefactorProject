package com.develhope.spring.user.seller;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleRepository;

import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.order.OrderRepository;
import com.develhope.spring.order.OrderStatus;
import com.develhope.spring.rent.RentInfo;
import com.develhope.spring.rent.RentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Seller options", description = "Here are all functions needed for our sellers")
@RestController
@RequestMapping("/seller")
@PreAuthorize("hasRole('ROLE_SELLER')")
public class SellerController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private RentService rentService;

    // TODO Control null cases, wrong inputs etc...
    // tested: ok
    @Operation(summary = "Retrieve a vehicle by ID.", description = "Get a Vehicle object by specifying its ID.", tags = {
            "seller", "get", "vehicle" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "No vehicle with that ID") })
    @GetMapping("/vehicleinfo/{vehicleId}")
    public Optional<Vehicle> getOneVehicle(@PathVariable Long vehicleId) {
        return sellerService.getOneVehicleById(vehicleId);
    }

    // tested: create order but without userId, should we pass idUser too????
    @Operation(summary = "Create order.", description = "Create an order for a vehicle, if it is available, by specifying order details and vehicle ID", tags = {
            "seller", "create", "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created"),
            @ApiResponse(responseCode = "400", description = "Invalid vehicle ID or order details supplied"),
            @ApiResponse(responseCode = "404", description = "No vehicle with that ID") })
    @PostMapping("/createorder/{vehicleId}")
    public ResponseEntity<String> createOrder(@PathVariable Long vehicleId, @RequestBody OrderInfo newOrder) {
        return sellerService.createOrderOfAvailableVehicle(vehicleId, newOrder);
    }

    // tested: ok
    @Operation(summary = "Delete order.", description = "Delete an order by specifying its ID", tags = { "seller",
            "delete", "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid vehicle ID or order details supplied"),
            @ApiResponse(responseCode = "404", description = "No vehicle with that ID") })
    @DeleteMapping("/deleteorder/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id); // Do vehicle properties need to be changed?
    }

    // tested: ok
    @Operation(summary = "Modify order.", description = "Modify an order by specifying its ID and updated order details", tags = {
            "seller", "modify", "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order modified"),
            @ApiResponse(responseCode = "400", description = "Invalid order ID or order details supplied"),
            @ApiResponse(responseCode = "404", description = "No order with that ID") })
    @PutMapping("/modifyorder/{idOrderToModify}")
    public @ResponseBody OrderInfo modifyOrder(@PathVariable Long idOrderToModify,
            @RequestBody OrderInfo modifiedOrder) {
        return sellerService.modifyOrder(idOrderToModify, modifiedOrder);
    }

    // tested: ok
    @Operation(summary = "Get order status.", description = "Get order status details by specifying its ID", tags = {
            "seller", "get", "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed"),
            @ApiResponse(responseCode = "400", description = "Invalid order ID supplied"),
            @ApiResponse(responseCode = "404", description = "No order with that ID") })
    @GetMapping("/orderstatus/{orderId}")
    public @ResponseBody OrderStatus getOrderStatus(@PathVariable Long orderId) {
        return sellerService.getOrderStatus(orderId);
    }

    // tested: ok
    @Operation(summary = "Update order status.", description = "Update order status by specifying its ID and the updated order status", tags = {
            "seller", "update", "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed"),
            @ApiResponse(responseCode = "400", description = "Invalid order ID or order status supplied"),
            @ApiResponse(responseCode = "404", description = "No order with that ID or invalid order status") })
    @PatchMapping("/updateorderstatus/{orderId}/{newStatus}")
    public OrderInfo updateOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatus newStatus) {
        return sellerService.updateOrderStatus(orderId, newStatus);
    }

    // TODO Verificare tutti gli ordini filtrati per uno stato

    @PostMapping("/createrentorder")
    public RentInfo createRentOrder(@RequestBody RentInfo newRentOrder) {
        return rentService.createRent(newRentOrder);
    }

    @DeleteMapping("/deleterentorder/{rentId}")
    public void deleteRentOrder(@PathVariable Long rentId) {
        rentService.deleteRent(rentId);
    }

    @PutMapping("/modifyrentorder/{orderId}")
    public RentInfo modifyRentOrder(@PathVariable Long orderId, @RequestBody RentInfo updatedRentOrder) {
        return rentService.updateRent(orderId, updatedRentOrder);
    }

    /*
     * @GetMapping("/getordersbystatus")
     * public List<OrderInfo> getOrdersByStatus(@RequestParam (required = true)
     * String status) {
     * return sellerService.getAllOrdersByStatusParams(status);
     * }
     */

    /*
     * @GetMapping("/order/{id}")
     * public @ResponseBody OrderInfo getOneOrder(@PathVariable Long id) {
     * Optional<OrderInfo> order = orderRepository.findById(id);
     * if (order.isPresent()) {
     * return order.get();
     * } else {
     * return null;
     * }
     * }
     */

    /*
     * @PutMapping("/orderstatus/{id}") //???????? check enum? service? orderstatus
     * public OrderInfo updateOrderStatus(@PathVariable Long id, @RequestParam
     * Enum<OrderStatus> newOrderStatus){
     * return sellerService.setOrderStatusToDelivered(id, newOrderStatus);
     * }
     */

    /*
     * @GetMapping("/order/get{orderstatus}") //loop?? id?? getAll ; need to use sql
     * queries to loop orderRepository
     * public @ResponseBody List<OrderInfo> getAllOrdersByOrderStatus(@PathVariable
     * String orderstatus) {
     * return sellerService.getAllOrdersByStatus(orderstatus);
     * }
     */

    /*
     * @PostMapping("/createrentorder") //???????
     * public @ResponseBody RentInfo rentOrder(@RequestBody RentInfo
     * rentInfo, @RequestParam Long vehicleId){
     * Vehicle vehicle = getOneVehicle(vehicleId);
     * if (vehicle != null && vehicle.getIsAvailable() == VehicleStatus.REANTABLE) {
     * return orderRepository.save(rentInfo);
     * }
     * return null;
     * }
     */
}
