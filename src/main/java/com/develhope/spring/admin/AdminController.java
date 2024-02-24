package com.develhope.spring.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleStatus;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.rent.RentInfo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Admin options", description = "Here are all functions needed for our admins")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServices adminServices;

    // creazione nuovo veicolo
    //tested:ok
    @PostMapping("/addVehicle")
    public Vehicle addAVehicle(@RequestBody Vehicle vehicle) {
        return adminServices.addVehicle(vehicle);
    }

    // modifica dei parametri di un veicolo
    //tested: ok but added saveAndFlush in service to update db
    @PutMapping("/{id}/modifyAVehicle")
    public Vehicle modifVehicleById(@PathVariable Long id, @RequestParam String choice, @RequestBody Vehicle vehicle) {
        return adminServices.modifyVehicle(id, choice, vehicle);
    }

    // eliminazione veicolo tramite id
    //tested: ok
    @DeleteMapping("/{id}/deleteVehicleById")
    public boolean deleteVehicleById(@PathVariable Long id) {
        return adminServices.deleteVehicle(id);
    }

    // cambio dello stato di un veicolo
    //tested: ok but added saveAndFlush in service to update db
    @PatchMapping("/{id}/changeStatusOfAVehicle")
    public Vehicle changeStatusOfAVehicle(@PathVariable Long id, @RequestParam VehicleStatus status) {
        return adminServices.changeStatusVehicle(id, status);
    }

    // creazione nuovo ordine per un utente specifico
    @PostMapping("/{userid}/{vehicleid}/createOrderForAUser")
    public OrderInfo creatOrderForUser(@PathVariable Long user_id, @PathVariable Long vehicle_Id,
            @RequestParam boolean advance) {
        return adminServices.createOrderForAUser(user_id, vehicle_Id, advance);
    }

    // eliminazione di un ordine per un cliente tramite id

    @DeleteMapping("/{id}/deleteAOrderById")
    public boolean deleteOrderById(@PathVariable Long id) {
        return adminServices.deleteOrder(id);
    }

    // modifica di un ordine
    @PutMapping("/{id}/{userid}/modifyOrder")
    public OrderInfo modifyOrderById(@PathVariable Long id, @PathVariable Long userid,
            @RequestParam String choice, @RequestBody OrderInfo order) {
        return adminServices.modifyOrderBy(id, choice, order, userid);
    }

    // creazione nuovo noleggio per un utente specifico
    @PostMapping("/{userid}/{vehicleid}/createRentForAUser")
    public RentInfo creatRentForUser(@PathVariable Long user_id, @PathVariable Long vehicle_Id,
            @RequestParam OffsetDateTime startDate,
            @RequestParam OffsetDateTime endDate, @RequestParam Double dailyCost) {
        return adminServices.createRentForAUser(user_id, vehicle_Id, startDate, endDate, dailyCost);
    }
}
