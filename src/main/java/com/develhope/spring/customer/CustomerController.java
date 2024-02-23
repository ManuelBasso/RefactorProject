package com.develhope.spring.customer;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer options", description = "Here are all functions needed for our customers")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private List<Customer> listCustomers;



    @GetMapping(path = "/getAll")
    public List<Customer> getAll(){
        return listCustomers;
    }

    @PostMapping(path = "/addCustomer")
    public Boolean addCustomer(@RequestBody Customer c) {
        return listCustomers.add(c);
    }
}