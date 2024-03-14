package com.develhope.spring.user.customer;

import com.develhope.spring.car.cardto.VehicleNetworkResponse;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.order.orderdto.OrderNetworkResponse;
import com.develhope.spring.order.orderdto.OrderRequestRefactor;
import com.develhope.spring.rent.RentInfo;
import com.develhope.spring.rent.rentdto.RentNetworkResponse;
import com.develhope.spring.rent.rentdto.RentRequest;
import com.develhope.spring.rent.rentdto.RentRequestRefactor;
import com.develhope.spring.user.Users;
import com.develhope.spring.user.userdto.UserNetworkResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Customer options", description = "Here are all functions needed for our customers")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    //Creare un ordine a partire da un veicolo contrassegnato come ordinabile
    //ok
    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal Users customer, @RequestBody OrderRequestRefactor orderRequest) {
        OrderNetworkResponse response = customerService.createOrder(customer, orderRequest);

        if (response instanceof OrderNetworkResponse.Success) {
            return ResponseEntity.ok(((OrderNetworkResponse.Success) response).getOrderResponse());

        } else {

            int code = ((OrderNetworkResponse.Error) response).getCode();
            String description = ((OrderNetworkResponse.Error) response).getDescription();
            return ResponseEntity.status(code).body(description);
        }
    }

    //Creare un acquisto a partire da un veicolo contrassegnato come acquistabile


    //Vedere i propri ordini
    //okkk
    @GetMapping("/getOrders")
    public ResponseEntity<?> getOrders(@AuthenticationPrincipal Users customer) {
        return customerService.getOrders(customer);
    }

    //Ottenere i dettagli di un veicolo specifico
    //tested: ok
    @GetMapping("/getVehicle/{idVehicle}")
    public ResponseEntity<?> getVehicle(@AuthenticationPrincipal Users customer, @PathVariable long idVehicle) {
        VehicleNetworkResponse response = customerService.getVehicle(idVehicle);

        if (response instanceof VehicleNetworkResponse.Success) {
            return ResponseEntity.ok(((VehicleNetworkResponse.Success) response).getVehicleResponse());

        } else {

            int code = ((VehicleNetworkResponse.Error) response).getCode();
            String description = ((VehicleNetworkResponse.Error) response).getDescription();
            return ResponseEntity.status(code).body(description);
        }
    }


    //Cancellare un suo ordine
    //tested: ok
    @DeleteMapping("/deleteOrder/{idOrder}")
    public ResponseEntity<?> deleteOrder(@AuthenticationPrincipal Users customer, @PathVariable long idOrder) {
        return customerService.deleteOrder(customer, idOrder);
    }

    //Creare un noleggio
    //tested: ok
    @PostMapping("/createRent")
    public ResponseEntity<?> createRent(@AuthenticationPrincipal Users customer, @RequestBody RentRequestRefactor rentRequest) {
        RentNetworkResponse response = customerService.createRent(customer, rentRequest);
        if (response instanceof RentNetworkResponse.Success) {
            return ResponseEntity.ok(((RentNetworkResponse.Success) response).getRentResponse());

        } else {

            int code = ((RentNetworkResponse.Error) response).getCode();
            String description = ((RentNetworkResponse.Error) response).getDescription();
            return ResponseEntity.status(code).body(description);
        }
    }

    //Cancellare un noleggio
    //tested: ok
    @DeleteMapping("/deleteRent/{idRent}")
    public ResponseEntity<?> deleteRent(@AuthenticationPrincipal Users customer, @PathVariable long idRent) {
        return customerService.deleteRent(customer, idRent);
    }


    //Modificare i dati dell’utente

    @PutMapping("/updateFirstName")
    public ResponseEntity<?> updateCustomerName(@AuthenticationPrincipal Users customer, @RequestParam String firstName) {
        UserNetworkResponse response = customerService.updateCustomerName(customer, firstName);

        if (response instanceof UserNetworkResponse.Success) {
            return ResponseEntity.ok(((UserNetworkResponse.Success) response).getUserResponse());
        } else {
            int code = ((UserNetworkResponse.Error) response).getCode();
            String description = ((UserNetworkResponse.Error) response).getDescription();
            return ResponseEntity.status(code).body(description);
        }
    }

    @PutMapping("/updateLastName")
    public ResponseEntity<?> updateLastName(@AuthenticationPrincipal Users customer, @RequestParam String lastName) {
        UserNetworkResponse response = customerService.updateLastName(customer, lastName);

        if (response instanceof UserNetworkResponse.Success) {
            return ResponseEntity.ok(((UserNetworkResponse.Success) response).getUserResponse());
        } else {
            int code = ((UserNetworkResponse.Error) response).getCode();
            String description = ((UserNetworkResponse.Error) response).getDescription();
            return ResponseEntity.status(code).body(description);
        }
    }

    @PutMapping("/updateEmail")
    public ResponseEntity<?> updateEmail(@AuthenticationPrincipal Users customer, @RequestParam String email) {
        UserNetworkResponse response = customerService.updateEmail(customer, email);

        if (response instanceof UserNetworkResponse.Success) {
            return ResponseEntity.ok(((UserNetworkResponse.Success) response).getUserResponse());
        } else {
            int code = ((UserNetworkResponse.Error) response).getCode();
            String description = ((UserNetworkResponse.Error) response).getDescription();
            return ResponseEntity.status(code).body(description);
        }
    }

    //TODO validation using email
    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@AuthenticationPrincipal Users customer, @RequestParam String password) {
        UserNetworkResponse response = customerService.updatePassword(customer, password);

        if (response instanceof UserNetworkResponse.Success) {
            return ResponseEntity.ok(((UserNetworkResponse.Success) response).getUserResponse());
        } else {
            int code = ((UserNetworkResponse.Error) response).getCode();
            String description = ((UserNetworkResponse.Error) response).getDescription();
            return ResponseEntity.status(code).body(description);
        }
    }

    @PutMapping("/updateAll")
    public ResponseEntity<?> updateAll(@AuthenticationPrincipal Users customer, @RequestBody Users newCustomer) {
        UserNetworkResponse response = customerService.updateAll(customer, newCustomer);

        if (response instanceof UserNetworkResponse.Success) {
            return ResponseEntity.ok(((UserNetworkResponse.Success) response).getUserResponse());
        } else {
            int code = ((UserNetworkResponse.Error) response).getCode();
            String description = ((UserNetworkResponse.Error) response).getDescription();
            return ResponseEntity.status(code).body(description);
        }

    }

    /*
    @PutMapping("/updatePassword")
    public ResponseEntity<Users> updatePassword(@AuthenticationPrincipal Users customer, @RequestParam String password) {
        return customerService.updatePassword(customer, password);
    }
     */

    //Cancellare la propria utenza
    //TODO doesn't work
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal Users customer) {
        return customerService.delete(customer);
    }

    //Ricercare un veicolo secondo diversi criteri (prezzo, colore, marca, modello, ecc)
    @GetMapping("/findVehicleByAttribute")
    public ResponseEntity<?> getVehiclesByAttribute(@AuthenticationPrincipal Users customer, @RequestParam String attributeChoice, @RequestParam String attributeValue) {
        VehicleNetworkResponse response = customerService.getVehicleByAttribute(attributeChoice, attributeValue);

        if (response instanceof VehicleNetworkResponse.SuccessList) {
            return ResponseEntity.ok(((VehicleNetworkResponse.SuccessList) response).getVehicleResponse());

        } else {

            int code = ((VehicleNetworkResponse.Error) response).getCode();
            String description = ((VehicleNetworkResponse.Error) response).getDescription();
            return ResponseEntity.status(code).body(description);
        }
    }

}