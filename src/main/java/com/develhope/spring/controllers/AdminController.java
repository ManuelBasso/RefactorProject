package com.develhope.spring.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.repositories.VehicleRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VehicleRepository vehicleRepository;

    // creazione nuovo veicolo
    @PostMapping("/addVehicle")
    public Vehicle addAVehicle(@RequestBody Vehicle vehicle) {

        Vehicle newVehicle = vehicleRepository.save(vehicle);
        return newVehicle;
    }

    // cambio di stato da nuovo a usato
    @PatchMapping("/updateStatusOfvehiclebyid/{id}")
    public Vehicle updateCar(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        @SuppressWarnings("null")
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        Vehicle updatedVehicle = new Vehicle();
        if (optionalVehicle.isPresent()) {
            updatedVehicle = optionalVehicle.get();
            updatedVehicle.setIsNew(vehicle.getIsNew());
        }
        return vehicleRepository.save(updatedVehicle);
    }

    // eliminazione veicolo tramite id
   
    @DeleteMapping("/deleteVehicleById/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
        }

    }

}
