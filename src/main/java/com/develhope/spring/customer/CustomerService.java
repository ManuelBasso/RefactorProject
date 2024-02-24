package com.develhope.spring.customer;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleRepository;

import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.order.OrderRepository;
import com.develhope.spring.rent.RentInfo;
import com.develhope.spring.rent.RentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RentRepository rentRepository;


    public Customer createCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    public Vehicle getVehicle(long idVehicle) {
        Optional<Vehicle> vehicleToFind = vehicleRepository.findById(idVehicle);
        return vehicleToFind.orElse(null);
    }

    //TODO custom query order repository
    //Da testare

    public List<OrderInfo> getOrders(long id) {
        List<OrderInfo> myorders = orderRepository.findByUserId(id);
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

    public Customer updateCustomer(long id, Customer customer) {
        Optional<Customer> customerToUpdate = customerRepository.findById(id);
        if (customerToUpdate.isPresent()) {
            customerToUpdate.get().setFirstName(customer.getFirstName());
            customerToUpdate.get().setLastName(customer.getLastName());
            customerToUpdate.get().setEmail(customer.getEmail());
            customerToUpdate.get().setPassword(customer.getPassword());
            customerToUpdate.get().setPhoneNumber(customer.getPhoneNumber());
            return customerToUpdate.get();
        } else {
            return null;
        }
    }


}

