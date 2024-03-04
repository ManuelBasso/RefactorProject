package com.develhope.spring.user.customer;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleRepository;

import com.develhope.spring.car.VehicleStatus;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.order.OrderRepository;
import com.develhope.spring.rent.RentInfo;
import com.develhope.spring.rent.RentRepository;

import com.develhope.spring.role.Role;
import com.develhope.spring.user.Users;
import com.develhope.spring.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    public OrderInfo createOrder(long idCustomer, long idVehicle, long idSeller, OrderInfo orderInfo) {
        boolean customerCheck = false;
        boolean sellerCheck = false;
        boolean vehicleCheck=false;

        Optional<Users> customer = userRepository.findById(idCustomer);
        if (customer.isPresent()) {
            for (Role role : customer.get().getRole()) {
                if (role.getName().equals(ROLE_CUSTOMER)) {
                    customerCheck = true;
                    break;
                }
            }
        }

        Optional<Vehicle> vehicle = vehicleRepository.findById(idVehicle);
        if (vehicle.isPresent() && vehicle.get().getIsAvailable().equals(VehicleStatus.AVAILABLE)) {
            vehicleCheck = true;
        }


        Optional<Users> seller = userRepository.findById(idSeller);
        if (seller.isPresent()) {
            for (Role role : seller.get().getRole()) {
                if (role.getName().equals(ROLE_SELLER)) {
                    sellerCheck = true;
                    break;
                }
            }
        }

            if (vehicleCheck  && sellerCheck && customerCheck) {
                OrderInfo newOrder = new OrderInfo();
                newOrder.setOrderStatus(orderInfo.getOrderStatus());
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
    }

