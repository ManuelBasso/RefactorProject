package com.develhope.spring.user.customer;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleRepository;

import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.order.OrderRepository;
import com.develhope.spring.rent.RentInfo;
import com.develhope.spring.rent.RentRepository;

import com.develhope.spring.user.Users;
import com.develhope.spring.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Users createCustomer(Users user) {
        return userRepository.saveAndFlush(user);
    }

    public Vehicle getVehicle(long idVehicle) {
        Optional<Vehicle> vehicleToFind = vehicleRepository.findById(idVehicle);
        return vehicleToFind.orElse(null);
    }

    // TODO custom query order repository
    // Da testare

    public List<OrderInfo> getOrders(long id) {
        List<OrderInfo> myorders = orderRepository.findByCustomer_Id(id);
        return myorders;
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

    public Users updateCustomer(long id, Users user) {
        Optional<Users> userToUpdate = userRepository.findById(id);
        if (userToUpdate.isPresent()) {
            userToUpdate.get().setFirstName(user.getFirstName());
            userToUpdate.get().setLastName(user.getLastName());
            userToUpdate.get().setEmail(user.getEmail());
            userToUpdate.get().setPassword(user.getPassword());
            // userToUpdate.get().setPhoneNumber(user.getPhoneNumber());
            return userToUpdate.get();
        } else {
            return null;
        }
    }

}
