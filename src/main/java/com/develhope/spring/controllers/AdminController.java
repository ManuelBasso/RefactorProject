package com.develhope.spring.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.services.AdminServices;
import com.develhope.spring.utilities.VehicleStatus;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @PostMapping("/addVehicle")
    public Vehicle addAVehicle(@RequestBody Vehicle vehicle) {
        return adminServices.addVehicle(vehicle);
    }

    // modifica dei parametri di un veicolo
    @PutMapping("/{id}/modifyAVehicle")
    public Vehicle modifVehicleById(@PathVariable Long id, @RequestParam String choice, @RequestBody Vehicle vehicle) {
        return adminServices.modifyVehicle(id, choice, vehicle);
    }

    // eliminazione veicolo tramite id

    @DeleteMapping("/{id}/deleteVehicleById")
    public boolean deleteVehicleById(@PathVariable Long id) {
        return adminServices.deleteVehicle(id);
    }

    // cambio dello stato di un veicolo
    @PatchMapping("/{id}/changeStatusOfAVehicle")
    public Vehicle changeStatusOfAVehicle(@PathVariable Long id, @RequestParam VehicleStatus status) {
        return adminServices.changeStatusVehicle(id, status);
    }

}
