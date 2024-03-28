package com.develhope.spring.purchase;

import com.develhope.spring.order.OrderModel;
import com.develhope.spring.purchase.purchasedto.CustomerPurchaseResponse;
import com.develhope.spring.purchase.purchasedto.CustomerPurchaseResponseWithoutOrder;
import com.develhope.spring.user.UserModel;
import com.develhope.spring.vehicle.Vehicle;
import com.develhope.spring.order.Order;
import com.develhope.spring.purchase.purchasedto.PurchaseRequest;
import com.develhope.spring.purchase.purchasedto.PurchaseResponse;
import com.develhope.spring.user.User;
import com.develhope.spring.vehicle.VehicleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseModel {

    private Long purchaseId;
    private Double totalPrice;
    private Order order;
    private User customer;
    private Vehicle vehicle;

    public static PurchaseModel mapEntityToModel(Purchase purchase){
        return new PurchaseModel(
                purchase.getPurchaseId(),
                purchase.getTotalPrice(),
                purchase.getOrder(),
                purchase.getCustomer(),
                purchase.getVehicle()
        );
    }
    public static Purchase mapModelToEntity(PurchaseModel purchaseModel){
        return new Purchase(
                purchaseModel.getPurchaseId(),
                purchaseModel.getTotalPrice(),
                purchaseModel.getOrder(),
                purchaseModel.getCustomer(),
                purchaseModel.getVehicle()
        );
    }
    public static PurchaseResponse mapModelToResponse(PurchaseModel purchaseModel){
        return new PurchaseResponse(
                purchaseModel.getPurchaseId(),
                purchaseModel.getTotalPrice(),
                purchaseModel.getOrder(),
                purchaseModel.getCustomer(),
                purchaseModel.getVehicle()
        );
    }
    public static PurchaseModel mapRequestToModel(PurchaseRequest purchaseRequest){
        return new PurchaseModel(
                null,
                purchaseRequest.getTotalPrice(),
                purchaseRequest.getOrder(),
                purchaseRequest.getCustomer(),
                purchaseRequest.getVehicle()
        );
    }

    public static CustomerPurchaseResponse mapModelToCustomerResponse(PurchaseModel purchaseModel){
        UserModel userModel = UserModel.mapEntityToModel(purchaseModel.getCustomer());
        VehicleModel vehicleModel = VehicleModel.mapEntityToModel(purchaseModel.getVehicle());
        OrderModel orderModel = OrderModel.mapEntityToModel(purchaseModel.getOrder());

        return new CustomerPurchaseResponse(
                purchaseModel.getPurchaseId(),
                purchaseModel.getTotalPrice(),
                OrderModel.mapModelToResponse(orderModel),
                UserModel.mapModelToResponse(userModel),
                VehicleModel.mapModelToResponse(vehicleModel)
        );
    }

    public static CustomerPurchaseResponse mapModelToCustomerResponseOrderNull(PurchaseModel purchaseModel){
        UserModel userModel = UserModel.mapEntityToModel(purchaseModel.getCustomer());
        VehicleModel vehicleModel = VehicleModel.mapEntityToModel(purchaseModel.getVehicle());

        return new CustomerPurchaseResponse(
                purchaseModel.getPurchaseId(),
                purchaseModel.getTotalPrice(),
                null,
                UserModel.mapModelToResponse(userModel),
                VehicleModel.mapModelToResponse(vehicleModel)
        );
    }
}
