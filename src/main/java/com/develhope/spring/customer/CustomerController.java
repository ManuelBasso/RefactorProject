package com.develhope.spring.customer;
import com.develhope.spring.car.Vehicle;
import com.develhope.spring.order.OrderInfo;
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


    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    //Ottenere i dettagli di un veicolo specifico
    @GetMapping("/getVehicle/{idVehicle}")
    public Vehicle getVehicle(@PathVariable long idVehicle) {
        return customerService.getVehicle(idVehicle);
    }


    @GetMapping("/{idUser}/getOrders")
    public List<OrderInfo> getOrders(@PathVariable long idUser){
        return customerService.getOrders(idUser);
    }

    //Cancellare un ordine
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
    public Customer updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }
}