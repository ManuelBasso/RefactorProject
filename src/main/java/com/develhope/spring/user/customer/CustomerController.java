package com.develhope.spring.user.customer;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.rent.RentInfo;
import com.develhope.spring.user.Users;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer options", description = "Here are all functions needed for our customers")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    //Creare un ordine a partire da un veicolo contrassegnato come ordinabile
    //ok
    @PostMapping("/createOrder/{idSeller}/{idVehicle}")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal Users user, @PathVariable long idSeller, @PathVariable long idVehicle, @RequestBody OrderInfo orderInfo) {
        return customerService.createOrder(user, idSeller, idVehicle, orderInfo);
    }

    //Creare un acquisto a partire da un veicolo contrassegnato come acquistabile


    //Vedere i propri ordini
    //okkk
    @GetMapping("/{idUser}/getOrders")
    public List<OrderInfo> getOrders(@PathVariable long idUser) {
        return customerService.getOrders(idUser);
    }

    //Ottenere i dettagli di un veicolo specifico
    //tested: ok
    @GetMapping("/getVehicle/{idVehicle}")
    public Vehicle getVehicle(@PathVariable long idVehicle) {
        return customerService.getVehicle(idVehicle);
    }


    //Cancellare un ordine
    //tested: ok
    @DeleteMapping("/{idOrder}/deleteOrder")
    public boolean deleteOrder(@PathVariable long idOrder) {
        return customerService.deleteOrder(idOrder);
    }

    //Creare un noleggio
    @PostMapping("/createRent/{idSeller}/{idVehicle}")
    public ResponseEntity<?> createRent(@AuthenticationPrincipal Users customer, @PathVariable long idSeller, @PathVariable long idVehicle, @RequestBody RentInfo rentInfo) {
        return customerService.createRent(customer, idSeller, idVehicle, rentInfo);
    }

    //Cancellare un noleggio
    @DeleteMapping("/{idRent}/deleteRent")
    public boolean deleteRent(@PathVariable long idRent) {
        return customerService.deleteRent(idRent);
    }


    //Modificare i dati dell’utente

    @PutMapping("/update/{id}")
    public Users updateCustomer(@PathVariable long id, @RequestParam String firstName) {
        return customerService.updateCustomer(id, firstName);
    }
}