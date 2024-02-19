package com.develhope.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develhope.spring.entities.vehicleTypes.Vehicle;
import com.develhope.spring.repositories.AdminRepository;
import com.develhope.spring.repositories.UserRepository;
import com.develhope.spring.repositories.VehicleRepository;
import com.develhope.spring.utilities.VehicleStatus;

@Service
public class AdminServices {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    UserRepository userRepository;

    // aggiunzione di un veicolo
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.saveAndFlush(vehicle);
    }

    // metodo per la modifica di un solo parametro del veicolo
    public Vehicle modifyVehicle(Long id, String choice, Vehicle vehicle) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            switch (choice) {
                case "brand":
                    optionalVehicle.get().setBrand(vehicle.getBrand());
                    break;
                case "model":
                    optionalVehicle.get().setModel(vehicle.getModel());
                    break;
                case "color":
                    optionalVehicle.get().setColor(vehicle.getColor());
                    break;
                case "gear":
                    optionalVehicle.get().setGearboxType(vehicle.getGearboxType());
                    break;
                case "fuel":
                    optionalVehicle.get().setFuelType(vehicle.getFuelType());
                    break;
                case "accessories":
                    optionalVehicle.get().setAccessories(vehicle.getAccessories());
                    break;
                case "displacement":
                    optionalVehicle.get().setDisplacement(vehicle.getDisplacement());
                    break;
                case "power":
                    optionalVehicle.get().setPower(vehicle.getPower());
                    break;
                case "year_registration":
                    optionalVehicle.get().setYearOfRegistration(vehicle.getYearOfRegistration());
                    break;
                case "price":
                    optionalVehicle.get().setPrice(vehicle.getPrice());
                    break;
                case "discount":
                    optionalVehicle.get().setDiscount(vehicle.getDiscount());
                    break;
                case "new/used":
                    optionalVehicle.get().setIsNew(vehicle.getIsNew());
                    break;
                case "all":
                    modifyAllPartOfVehicle(id, choice, vehicle, optionalVehicle);
                    break;
                default:

                    break;
            }
            return optionalVehicle.get();
        }
        return null;
    }

    // metodo per la modifica di tutti i parametri del veicolo
    public Optional<Vehicle> modifyAllPartOfVehicle(Long id, String choice, Vehicle vehicle,
            Optional<Vehicle> optionalVehicle) {
        Optional<Vehicle> modifyOptionaVehicle = optionalVehicle;
        modifyOptionaVehicle.get().setBrand(vehicle.getBrand());
        modifyOptionaVehicle.get().setModel(vehicle.getModel());
        modifyOptionaVehicle.get().setColor(vehicle.getColor());
        modifyOptionaVehicle.get().setGearboxType(vehicle.getGearboxType());
        modifyOptionaVehicle.get().setFuelType(vehicle.getFuelType());
        modifyOptionaVehicle.get().setAccessories(vehicle.getAccessories());
        modifyOptionaVehicle.get().setDisplacement(vehicle.getDisplacement());
        modifyOptionaVehicle.get().setPower(vehicle.getPower());
        modifyOptionaVehicle.get().setYearOfRegistration(vehicle.getYearOfRegistration());
        modifyOptionaVehicle.get().setPrice(vehicle.getPrice());
        modifyOptionaVehicle.get().setDiscount(vehicle.getDiscount());
        modifyOptionaVehicle.get().setIsNew(vehicle.getIsNew());
        return modifyOptionaVehicle;
    }

    // eliminazione di un veicolo
    public boolean deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // cambio dello Status di un veicolo
    public Vehicle changeStatusVehicle(Long id, VehicleStatus status) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle updatedVehicle = optionalVehicle.get();
            updatedVehicle.setIsAvailable(status);
            return updatedVehicle;
        }
        return null;
    }



}
