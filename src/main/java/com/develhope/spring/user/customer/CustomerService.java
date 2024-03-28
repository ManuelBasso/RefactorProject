package com.develhope.spring.user.customer;

import com.develhope.spring.purchase.Purchase;
import com.develhope.spring.purchase.PurchaseModel;
import com.develhope.spring.purchase.PurchaseRepository;
import com.develhope.spring.purchase.purchasedto.CustomerPurchaseNetworkResponse;
import com.develhope.spring.purchase.purchasedto.CustomerPurchaseRequest;
import com.develhope.spring.purchase.purchasedto.CustomerPurchaseResponse;
import com.develhope.spring.purchase.purchasedto.CustomerPurchaseResponseWithoutOrder;
import com.develhope.spring.vehicle.Vehicle;
import com.develhope.spring.vehicle.VehicleModel;
import com.develhope.spring.vehicle.VehicleRepository;

import com.develhope.spring.vehicle.VehicleStatus;
import com.develhope.spring.vehicle.vehicledto.VehicleNetworkResponse;
import com.develhope.spring.vehicle.vehicledto.VehicleResponse;
import com.develhope.spring.order.Order;
import com.develhope.spring.order.OrderModel;
import com.develhope.spring.order.OrderRepository;
import com.develhope.spring.order.OrderStatus;
import com.develhope.spring.order.orderdto.CustomerOrderResponse;
import com.develhope.spring.order.orderdto.CustomerOrderNetworkResponse;
import com.develhope.spring.order.orderdto.CustomerOrderRequest;
import com.develhope.spring.rent.Rent;
import com.develhope.spring.rent.RentModel;
import com.develhope.spring.rent.RentRepository;

import com.develhope.spring.rent.rentdto.CustomerRentResponse;
import com.develhope.spring.rent.rentdto.RentNetworkResponse;
import com.develhope.spring.rent.rentdto.CustomerRentRequest;
import com.develhope.spring.role.Role;
import com.develhope.spring.role.RoleRepository;
import com.develhope.spring.user.UserModel;
import com.develhope.spring.user.User;
import com.develhope.spring.user.UserRepository;
import com.develhope.spring.user.userdto.UserNetworkResponse;
import com.develhope.spring.user.userdto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.develhope.spring.role.Role.RoleType.CUSTOMER;

@Service
public class CustomerService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public VehicleNetworkResponse getVehicle(long idVehicle) {
        Optional<Vehicle> vehicleToFind = vehicleRepository.findById(idVehicle);
        if (vehicleToFind.isEmpty()) {
            return VehicleNetworkResponse.Error.builder().code(400).description("Wrong idVehicle").build();
        } else {
            VehicleModel vehicleModel = VehicleModel.mapEntityToModel(vehicleToFind.get());
            VehicleResponse vehicleResponse = VehicleModel.mapModelToResponse(vehicleModel);
            return VehicleNetworkResponse.Success.builder().vehicleResponse(vehicleResponse).build();
        }
    }


    public ResponseEntity<?> getOrders(User customer) {
        List<Order> myorders = orderRepository.findByCustomer_Id(customer.getId());
        if (myorders.isEmpty()) {
            return new ResponseEntity<>("this customer doesn't have any orders", HttpStatus.OK);
        }
        return ResponseEntity.ok(myorders);

    }

    public ResponseEntity<?> deleteOrder(User customer, long idOrder) {
        Optional<Order> orderToDelete = orderRepository.findById(idOrder);
        if (orderToDelete.isPresent() && orderToDelete.get().getCustomer().equals(customer)) {
            orderRepository.deleteById(idOrder);
            return ResponseEntity.ok(true);
        } else {
            return new ResponseEntity<>("Error, Wrong order", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<?> deleteRent(User customer, long idRent) {
        Optional<Rent> rentToDelete = rentRepository.findById(idRent);
        if (rentToDelete.isPresent() && rentToDelete.get().getCustomer().equals(customer)) {
            rentRepository.deleteById(idRent);
            return ResponseEntity.ok(true);
        } else {
            return new ResponseEntity<>("Error, Wrong rent", HttpStatus.BAD_REQUEST);
        }
    }

    public UserNetworkResponse updateCustomerName(User customer, String newFirstName) {
        if (newFirstName == null || newFirstName.isEmpty()) {
            return UserNetworkResponse.Error.builder().code(400).description("null value").build();
        } else {
            customer.setFirstName(newFirstName);
            userRepository.saveAndFlush(customer);
            UserModel userModel = UserModel.mapEntityToModel(customer);
            UserResponse userResponse = UserModel.mapModelToResponse(userModel);
            UserNetworkResponse response = UserNetworkResponse.Success.builder().userResponse(userResponse).build();
            return response;
        }

    }

    public UserNetworkResponse updateLastName(User customer, String newLastName) {
        if (newLastName == null || newLastName.isEmpty()) {
            return UserNetworkResponse.Error.builder().code(400).description("null value").build();
        } else {
            customer.setLastName(newLastName);
            userRepository.saveAndFlush(customer);
            UserModel userModel = UserModel.mapEntityToModel(customer);
            UserResponse userResponse = UserModel.mapModelToResponse(userModel);
            UserNetworkResponse response = UserNetworkResponse.Success.builder().userResponse(userResponse).build();
            return response;
        }
    }

    public UserNetworkResponse updateEmail(User customer, String newEmail) {
        if (newEmail == null || newEmail.isEmpty()) {
            return UserNetworkResponse.Error.builder().code(400).description("null value").build();
        } else {
            customer.setLastName(newEmail);
            userRepository.saveAndFlush(customer);
            UserModel userModel = UserModel.mapEntityToModel(customer);
            UserResponse userResponse = UserModel.mapModelToResponse(userModel);
            UserNetworkResponse response = UserNetworkResponse.Success.builder().userResponse(userResponse).build();
            return response;
        }
    }

    public UserNetworkResponse updatePassword(User customer, String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            return UserNetworkResponse.Error.builder().code(400).description("null value").build();
        } else {
            customer.setPassword(passwordEncoder.encode(newPassword));
            userRepository.saveAndFlush(customer);
            UserModel userModel = UserModel.mapEntityToModel(customer);
            UserResponse userResponse = UserModel.mapModelToResponse(userModel);
            UserNetworkResponse response = UserNetworkResponse.Success.builder().userResponse(userResponse).build();
            return response;
        }
    }

    public UserNetworkResponse updateAll(User customer, User newCustomer) {
        if (newCustomer == null || newCustomer.getFirstName().isEmpty() || newCustomer.getLastName().isEmpty() || newCustomer.getEmail().isEmpty()) {
            return UserNetworkResponse.Error.builder().code(400).description("null value").build();
        } else {
            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            customer.setEmail(newCustomer.getEmail());
            //customer.setPassword(newCustomer.getPassword());
            userRepository.saveAndFlush(customer);
            UserModel userModel = UserModel.mapEntityToModel(customer);
            UserResponse userResponse = UserModel.mapModelToResponse(userModel);
            UserNetworkResponse response = UserNetworkResponse.Success.builder().userResponse(userResponse).build();
            return response;
        }
    }



    public CustomerOrderNetworkResponse createOrder(User customer, CustomerOrderRequest orderRequestRefactor) {
        System.out.println(orderRequestRefactor);
        Boolean customerCheck = checkRoleUser(customer, CUSTOMER);
        if (!customerCheck) {
            return CustomerOrderNetworkResponse.Error.builder().code(600).description("This user doesn't exist or is not a customer").build();
        }

        Optional<Vehicle> vehicle = vehicleRepository.findById(orderRequestRefactor.getIdVehicle());
        boolean vehicleCheck = checkVehicle(vehicle, VehicleStatus.NOT_AVAILABLE);
        if (!vehicleCheck) {
            return CustomerOrderNetworkResponse.Error.builder().code(601).description("You can't order this vehicle because the status of the vehicle is: " + vehicle.get().getIsAvailable()).build();
        }


        OrderModel newOrderModel = new OrderModel();
        newOrderModel.setOrderStatus(OrderStatus.ORDERED);
        newOrderModel.setAdvancePayment(orderRequestRefactor.getAdvancePayment());
        newOrderModel.setPaidInFull(orderRequestRefactor.getPaidInFull());
        newOrderModel.setCustomer(customer);
        newOrderModel.setVehicle(vehicle.get());
        newOrderModel.setSeller(null);

        Order orderEntity = OrderModel.mapModelToEntity(newOrderModel);
        orderRepository.saveAndFlush(orderEntity);
        OrderModel orderModel = OrderModel.mapEntityToModel(orderEntity);
        CustomerOrderResponse orderResponse = OrderModel.mapModelToCustomerOrderResponse(orderModel);
        CustomerOrderNetworkResponse response = CustomerOrderNetworkResponse.Success.builder().orderResponse(orderResponse).build();

        vehicle.get().setIsAvailable(VehicleStatus.ORDERED);
        vehicleRepository.saveAndFlush(vehicle.get());

        return response;

    }


    public RentNetworkResponse createRent(User customer, CustomerRentRequest rentRequest) {
        boolean customerCheck = checkRoleUser(customer, CUSTOMER);
        if (!customerCheck) {
            return RentNetworkResponse.Error.builder().code(600).description("This user is not a Customer").build();
        }

        Optional<Vehicle> vehicle = vehicleRepository.findById(rentRequest.getIdVehicle());
        boolean vehicleCheck = checkVehicle(vehicle, VehicleStatus.AVAILABLE);
        if (!vehicleCheck) {
            return RentNetworkResponse.Error.builder().code(600).description("You can't rent this vehicle because the status of the vehicle is: " + vehicle.get().getIsAvailable()).build();
        }

        RentModel newModelRent = new RentModel(null,
                rentRequest.getStartDate(),
                rentRequest.getEndDate(),
                rentRequest.getDailyCost(),
                rentRequest.getTotalCost(),
                rentRequest.getIsPaid(),
                vehicle.get(),
                customer,
                null);

        Rent rentEntity = RentModel.mapModelToEntity(newModelRent);
        rentRepository.saveAndFlush(rentEntity);
        RentModel rentModel = RentModel.mapEntityToModel(rentEntity);
        CustomerRentResponse rentResponse = RentModel.mapModelToCustomerRentResponse(rentModel);
        return RentNetworkResponse.Success.builder().rentResponse(rentResponse).build();

    }

    public CustomerPurchaseNetworkResponse createPurchase(User customer, CustomerPurchaseRequest purchaseRequest) {
        if (purchaseRequest.getIdOrder() == null){
            return CreatePurchaseWithoutOrder(customer, purchaseRequest);
        }

        Optional<Order> orderInfo = orderRepository.findById(purchaseRequest.getIdOrder());

        if (orderInfo.isEmpty()) {
            return CustomerPurchaseNetworkResponse.Error.builder().code(515).description("This order doesn't exist").build();
        } else {
            return CreatePurchaseFromOrder(customer, purchaseRequest);
        }
    }
    private CustomerPurchaseNetworkResponse CreatePurchaseFromOrder(User customer, CustomerPurchaseRequest purchaseRequest) {
        Boolean customerCheck = checkRoleUser(customer, CUSTOMER);
        if (!customerCheck) {
            return CustomerPurchaseNetworkResponse.Error.builder().code(600).description("This user doesn't exist or is not a customer").build();
        }

        Optional<Vehicle> vehicle = vehicleRepository.findById(purchaseRequest.getIdVehicle());
        boolean vehicleCheck = checkVehicle(vehicle, VehicleStatus.AVAILABLE);
        if (!vehicleCheck) {
            return CustomerPurchaseNetworkResponse.Error.builder().code(601).description("You can't order this vehicle because the status of the vehicle is: " + vehicle.get().getIsAvailable()).build();
        }

        Optional<Order> orderInfo = orderRepository.findById(purchaseRequest.getIdOrder());


        PurchaseModel purchaseModel = new PurchaseModel();
        purchaseModel.setTotalPrice(purchaseRequest.getTotalPrice() - orderInfo.get().getAdvancePayment() );
        purchaseModel.setOrder(orderInfo.get());
        purchaseModel.setCustomer(customer);
        purchaseModel.setVehicle(vehicle.get());

        Purchase purchaseEntity = PurchaseModel.mapModelToEntity(purchaseModel);
        purchaseRepository.saveAndFlush(purchaseEntity);
        purchaseModel = PurchaseModel.mapEntityToModel(purchaseEntity);

        CustomerPurchaseResponse purchaseResponse = PurchaseModel.mapModelToCustomerResponse(purchaseModel);

        vehicle.get().setIsAvailable(VehicleStatus.BOUGHT);
        vehicleRepository.saveAndFlush(vehicle.get());

        //orderRepository.deleteById(purchaseRequest.getIdOrder());

        return CustomerPurchaseNetworkResponse.Success.builder().purchaseResponse(purchaseResponse).build();

    }


    private CustomerPurchaseNetworkResponse CreatePurchaseWithoutOrder(User customer, CustomerPurchaseRequest purchaseRequest) {
        Boolean customerCheck = checkRoleUser(customer, CUSTOMER);
        if (!customerCheck) {
            return CustomerPurchaseNetworkResponse.Error.builder().code(600).description("This user doesn't exist or is not a customer").build();
        }

        Optional<Vehicle> vehicle = vehicleRepository.findById(purchaseRequest.getIdVehicle());
        boolean vehicleCheck = checkVehicle(vehicle, VehicleStatus.AVAILABLE);
        if (!vehicleCheck) {
            return CustomerPurchaseNetworkResponse.Error.builder().code(601).description("You can't order this vehicle because the status of the vehicle is: " + vehicle.get().getIsAvailable()).build();
        }

        PurchaseModel purchaseModel = new PurchaseModel();
        purchaseModel.setTotalPrice(purchaseRequest.getTotalPrice());
        purchaseModel.setCustomer(customer);
        purchaseModel.setVehicle(vehicle.get());

        Purchase purchaseEntity = new Purchase();
        purchaseEntity.setTotalPrice(purchaseModel.getTotalPrice());
        purchaseEntity.setCustomer(purchaseModel.getCustomer());
        purchaseEntity.setVehicle(purchaseModel.getVehicle());

        purchaseRepository.saveAndFlush(purchaseEntity);
        purchaseModel = PurchaseModel.mapEntityToModel(purchaseEntity);
        CustomerPurchaseResponse purchaseResponse = PurchaseModel.mapModelToCustomerResponseOrderNull(purchaseModel);

        vehicle.get().setIsAvailable(VehicleStatus.BOUGHT);
        vehicleRepository.saveAndFlush(vehicle.get());

        return CustomerPurchaseNetworkResponse.Success.builder().purchaseResponse(purchaseResponse).build();


    }



    public ResponseEntity<?> delete(User customer) {
        orderRepository.deleteByCustomer_Id(customer.getId());
        rentRepository.deleteByCustomer_Id(customer.getId());
        roleRepository.deleteById(customer.getId());
        userRepository.deleteById(customer.getId());
        return ResponseEntity.ok(true);
    }

    public VehicleNetworkResponse getVehicleByAttribute(String attributeChoice, String attributeValue) {
        if (attributeChoice.isEmpty() || attributeValue.isEmpty()) {
            return VehicleNetworkResponse.Error.builder().code(400).description("attributes can't be empty").build();
        } else {

            switch (attributeChoice) {
                case "brand":
                    List<Vehicle> vehicleEntities = vehicleRepository.findByBrand(attributeValue);
                    List<VehicleModel> vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    List<VehicleResponse> vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();

                case "model":
                    vehicleEntities = vehicleRepository.findByModel(attributeValue);
                    vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();

                case "color":
                    vehicleEntities = vehicleRepository.findByColor(attributeValue);
                    vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();

                case "gearboxType":
                    vehicleEntities = vehicleRepository.findByGearboxType(attributeValue);
                    vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();
                case "fuelType":
                    vehicleEntities = vehicleRepository.findByFuelType(attributeValue);
                    vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();
                case "accessories":
                    vehicleEntities = vehicleRepository.findByAccessories(attributeValue);
                    vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();
                case "displacement":
                    vehicleEntities = vehicleRepository.findByDisplacement(Integer.valueOf(attributeValue));
                    vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();
                case "power":
                    vehicleEntities = vehicleRepository.findByPower(Integer.valueOf(attributeValue));
                    vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();
                case "yearOfRegistration":
                    vehicleEntities = vehicleRepository.findByYearOfRegistration(Integer.valueOf(attributeValue));
                    vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();
                case "price":
                    vehicleEntities = vehicleRepository.findByPrice(Double.valueOf(attributeValue));
                    vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();
                case "discount":
                    vehicleEntities = vehicleRepository.findByDiscount(Double.valueOf(attributeValue));
                    vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();
                case "isNew":
                    vehicleEntities = vehicleRepository.findByIsNew(Boolean.valueOf(attributeValue));
                    vehicleModels = VehicleModel.mapEntitiesToModels(vehicleEntities);
                    vehicleResponses = VehicleModel.mapModelsToResponses(vehicleModels);
                    return VehicleNetworkResponse.SuccessList.builder().vehicleResponse(vehicleResponses).build();
                default:
                    return null;
            }
        }
    }


    //Utilities

    public Boolean checkRoleUser(Optional<User> user, Role.RoleType roleUser) {
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

    public Boolean checkRoleUser(User user, Role.RoleType roleUser) {
        boolean check = false;
        if (user != null) {
            for (Role role : user.getRole()) {
                if (role.getName().equals(roleUser)) {
                    check = true;
                }
            }
        }
        return check;
    }

    public Boolean checkVehicle(Optional<Vehicle> vehicle, VehicleStatus vehicleStatus) {
        boolean vehicleCheck = false;
        if (vehicle.isPresent() && vehicle.get().getIsAvailable().equals(vehicleStatus)) {
            vehicleCheck = true;
        }
        return vehicleCheck;
    }




}

