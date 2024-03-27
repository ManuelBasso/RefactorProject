package com.develhope.spring.order;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleModel;
import com.develhope.spring.order.orderdto.CustomerOrderResponse;
import com.develhope.spring.order.orderdto.OrderRequest;
import com.develhope.spring.order.orderdto.OrderResponse;
import com.develhope.spring.user.UserModel;
import com.develhope.spring.user.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {


    private Long orderId;
    private Double advancePayment;
    private Boolean paidInFull;
    private Users customer;
    private Users seller;
    private Vehicle vehicle;
    private OrderStatus orderStatus;



    public static OrderModel mapEntityToModel(OrderInfo orderInfo) {
        return new OrderModel(
                orderInfo.getOrderId(),
                orderInfo.getAdvancePayment(),
                orderInfo.getPaidInFull(),
                orderInfo.getCustomer(),
                orderInfo.getSeller(),
                orderInfo.getVehicle(),
                orderInfo.getOrderStatus());
    }

    public static OrderInfo mapModelToEntity(OrderModel orderModel) {
        return new OrderInfo(
                orderModel.getOrderId(),
                orderModel.getAdvancePayment(),
                orderModel.getPaidInFull(),
                orderModel.getCustomer(),
                orderModel.getSeller(),
                orderModel.getVehicle(),
                orderModel.getOrderStatus());
    }

    public static OrderResponse mapModelToResponse(OrderModel orderModel) {
        return new OrderResponse(
                orderModel.getOrderId(),
                orderModel.getAdvancePayment(),
                orderModel.getPaidInFull(),
                orderModel.getCustomer(),
                orderModel.getSeller(),
                orderModel.getVehicle(),
                orderModel.getOrderStatus());
    }

    public static CustomerOrderResponse mapModelToCustomerOrderResponse(OrderModel orderModel){
        UserModel userModel = UserModel.mapEntityToModel(orderModel.getCustomer());
        VehicleModel vehicleModel = VehicleModel.mapEntityToModel(orderModel.getVehicle());


        return new CustomerOrderResponse(
                orderModel.getOrderId(),
                orderModel.getAdvancePayment(),
                orderModel.getPaidInFull(),
                UserModel.mapModelToResponse(userModel),
                VehicleModel.mapModelToResponse(vehicleModel),
                orderModel.getOrderStatus()
        );
    }

    public static OrderModel mapRequestToModel(OrderRequest orderRequest) {
        return new OrderModel(
                null,
                orderRequest.getAdvancePayment(),
                orderRequest.getPaidInFull(),
                orderRequest.getCustomer(),
                orderRequest.getSeller(),
                orderRequest.getVehicle(),
                orderRequest.getOrderStatus());
    }



}
