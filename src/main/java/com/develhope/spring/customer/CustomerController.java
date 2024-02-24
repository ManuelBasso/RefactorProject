package com.develhope.spring.customer;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer options", description = "Here are all functions needed for our customers")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //User invece di Customer
    //tested: ok
    @PostMapping("/createUser")
    public User createCustomer(@RequestBody User user) {
        return customerService.createCustomer(user);
    }

    //Ottenere i dettagli di un veicolo specifico
    //tested: ok
    @GetMapping("/getVehicle/{idVehicle}")
    public Vehicle getVehicle(@PathVariable long idVehicle) {
        return customerService.getVehicle(idVehicle);
    }

    //tested: not ok!
    @GetMapping("/{idUser}/getOrders")
    public List<OrderInfo> getOrders(@PathVariable long idUser) {
        return customerService.getOrders(idUser);
    }

    //Cancellare un ordine
    //tested: ok
    @DeleteMapping("/{idOrder}/deleteOrder")
    public boolean deleteOrder(@PathVariable long idOrder) {
        return customerService.deleteOrder(idOrder);
    }

    //Cancellare un noleggio
    @DeleteMapping("/{idRent}/deleteRent")
    public boolean deleteRent(@PathVariable long idRent) {
        return customerService.deleteRent(idRent);
    }


    //Modificare i dati dell’utente

    @PutMapping("/update/{id}")
    public User updateCustomer(@PathVariable long id, @RequestBody User user) {
        return customerService.updateCustomer(id, user);
    }
}