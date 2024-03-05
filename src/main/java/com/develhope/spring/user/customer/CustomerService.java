package com.develhope.spring.user.customer;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleRepository;

import com.develhope.spring.car.VehicleStatus;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.order.OrderRepository;
import com.develhope.spring.order.OrderStatus;
import com.develhope.spring.rent.RentInfo;
import com.develhope.spring.rent.RentRepository;

import com.develhope.spring.role.Role;
import com.develhope.spring.user.Users;
import com.develhope.spring.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.develhope.spring.role.Role.RoleType.ROLE_CUSTOMER;
import static com.develhope.spring.role.Role.RoleType.ROLE_SELLER;

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


    public Vehicle getVehicle(long idVehicle) {
        Optional<Vehicle> vehicleToFind = vehicleRepository.findById(idVehicle);
        return vehicleToFind.orElse(null);
    }


    public List<OrderInfo> getOrders(long id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            List<OrderInfo> myorders = orderRepository.findByCustomer_Id(id);
            return myorders;
        } else {
            return null;
        }
    }

    public boolean deleteOrder(long idOrder) {
        Optional<OrderInfo> orderToDelete = orderRepository.findById(idOrder);
        if (orderToDelete.isPresent()) {
            orderRepository.deleteById(idOrder);
            return true;
        } else {
            return false;
        }
    }


    public boolean deleteRent(long idRent) {
        Optional<RentInfo> rentToDelete = rentRepository.findById(idRent);
        if (rentToDelete.isPresent()) {
            rentRepository.deleteById(idRent);
            return true;
        } else {
            return false;
        }
    }

    public Users updateCustomer(long id, String newFirstName) {
        Optional<Users> userToUpdate = userRepository.findById(id);
        if (userToUpdate.isPresent()) {
            userToUpdate.get().setFirstName(newFirstName);
            return userRepository.save(userToUpdate.get());
        } else {
            return null;
        }
    }



    public OrderInfo createOrder(long idCustomer, long idSeller, long idVehicle, OrderInfo orderInfo) {

        Optional<Users> customer = userRepository.findById(idCustomer);
        boolean customerCheck = checkRoleUser(customer, ROLE_CUSTOMER);


        boolean vehicleCheck = false;
        Optional<Vehicle> vehicle = vehicleRepository.findById(idVehicle);
        if (vehicle.isPresent() && vehicle.get().getIsAvailable().equals(VehicleStatus.AVAILABLE)) {
            vehicleCheck = true;
        }

        Optional<Users> seller = userRepository.findById(idSeller);
        boolean sellerCheck = checkRoleUser(seller, ROLE_SELLER);

        if (vehicleCheck && sellerCheck && customerCheck) {
            OrderInfo newOrder = new OrderInfo();
            newOrder.setOrderStatus(OrderStatus.ORDERED);
            newOrder.setAdvancePayment(orderInfo.getAdvancePayment());
            newOrder.setPaidInFull(orderInfo.getPaidInFull());
            newOrder.setCustomer(customer.get());
            newOrder.setVehicle(vehicle.get());
            newOrder.setSeller(seller.get());
            orderRepository.save(newOrder);
            return newOrder;
        } else {
            return null;
        }

    }


    public RentInfo createRent(long idCustomer, long idSeller, long idVehicle, RentInfo rentInfo) {


        Optional<Users> customer = userRepository.findById(idCustomer);
        boolean customerCheck = checkRoleUser(customer, ROLE_CUSTOMER);


        boolean vehicleCheck = false;
        Optional<Vehicle> vehicle = vehicleRepository.findById(idVehicle);
        if (vehicle.isPresent() && vehicle.get().getIsAvailable().equals(VehicleStatus.AVAILABLE)) {
            vehicleCheck = true;
        }

        Optional<Users> seller = userRepository.findById(idSeller);
        boolean sellerCheck = checkRoleUser(seller, ROLE_SELLER);


        if (vehicleCheck && sellerCheck && customerCheck) {
            RentInfo newRent = new RentInfo();
            newRent.setCustomer(customer.get());
            newRent.setSeller(seller.get());
            newRent.setVehicle(vehicle.get());
            newRent.setStartDate(rentInfo.getStartDate());
            newRent.setEndDate(rentInfo.getEndDate());
            newRent.setIsPaid(rentInfo.getIsPaid());
            newRent.setDailyCost(rentInfo.getDailyCost());
            newRent.setTotalCost(rentInfo.getTotalCost());
            rentRepository.saveAndFlush(newRent);
            return newRent;
        } else {
            return null;
        }
    }


    public boolean checkRoleUser(Optional<Users> user, Role.RoleType roleUser) {
        boolean check = false;
        if (user.isPresent()) {
            for (Role role : user.get().getRole()) {
                if (role.getName().equals(roleUser)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
}

