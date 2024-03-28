package com.develhope.spring.rent;

import com.develhope.spring.vehicle.Vehicle;
import com.develhope.spring.vehicle.VehicleModel;
import com.develhope.spring.vehicle.vehicledto.VehicleResponse;
import com.develhope.spring.rent.rentdto.CustomerRentResponse;
import com.develhope.spring.rent.rentdto.RentRequest;
import com.develhope.spring.rent.rentdto.RentResponse;
import com.develhope.spring.user.UserModel;
import com.develhope.spring.user.User;
import com.develhope.spring.user.userdto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentModel {

    private Long rentId;

    private String startDate;
    private String endDate;
    private Double dailyCost;
    private Double totalCost;
    private Boolean isPaid;
    private Vehicle vehicle;
    private User customer;
    private User seller;

    public static RentModel mapEntityToModel(Rent rent){
        return new RentModel(
                rent.getRentId(),
                rent.getStartDate(),
                rent.getEndDate(),
                rent.getDailyCost(),
                rent.getTotalCost(),
                rent.getIsPaid(),
                rent.getVehicle(),
                rent.getCustomer(),
                rent.getSeller()
        );
    }
    public static Rent mapModelToEntity(RentModel rentModel){
        return new Rent(
                rentModel.getRentId(),
                rentModel.getStartDate(),
                rentModel.getEndDate(),
                rentModel.getDailyCost(),
                rentModel.getTotalCost(),
                rentModel.getIsPaid(),
                rentModel.getVehicle(),
                rentModel.getCustomer(),
                rentModel.getSeller()
        );
    }
    public static RentResponse mapModelToResponse(RentModel rentModel){
        return new RentResponse(
                rentModel.getRentId(),
                rentModel.getStartDate(),
                rentModel.getEndDate(),
                rentModel.getDailyCost(),
                rentModel.getTotalCost(),
                rentModel.getIsPaid(),
                rentModel.getVehicle(),
                rentModel.getCustomer(),
                rentModel.getSeller()
        );
    }
    public static CustomerRentResponse mapModelToCustomerRentResponse(RentModel rentModel) {
        UserModel userModel = UserModel.mapEntityToModel(rentModel.getCustomer());
        UserResponse userResponse = UserModel.mapModelToResponse(userModel);

        VehicleModel vehicleModel = VehicleModel.mapEntityToModel(rentModel.getVehicle());
        VehicleResponse vehicleResponse = VehicleModel.mapModelToResponse(vehicleModel);

        return new CustomerRentResponse(
                rentModel.getRentId(),
                rentModel.getStartDate(),
                rentModel.getEndDate(),
                rentModel.getDailyCost(),
                rentModel.getTotalCost(),
                rentModel.getIsPaid(),
                vehicleResponse,
                userResponse
        );
    }


    public static RentModel mapRequestToModel(RentRequest rentRequest){
        return new RentModel(
                null,
                rentRequest.getStartDate(),
                rentRequest.getEndDate(),
                rentRequest.getDailyCost(),
                rentRequest.getTotalCost(),
                rentRequest.getIsPaid(),
                rentRequest.getVehicle(),
                rentRequest.getCustomer(),
                rentRequest.getSeller()
        );
    }


}
