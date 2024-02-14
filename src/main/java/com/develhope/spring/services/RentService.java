package com.develhope.spring.services;

import com.develhope.spring.entities.StatusOfVehicle.Rent;
import com.develhope.spring.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    //NuovoNoleggio Service
    public Rent createRent(Rent rent){
        return rentRepository.save(rent);
    }

    //Tutti Noleggi Service
    public List<Rent> getAllRents(){
        return rentRepository.findAll();
    }

    //Noleggio tramite id (ricerca) Service
    public Optional<Rent> getRentByID(Long id){
        return rentRepository.findById(id);
    }

    //Update rent tramite Body Service
    public Rent updateRent(Long id,Rent rentDetails){
        Optional<Rent> rent = rentRepository.findById(id);
        if(rent.isPresent()){
            Rent updatedRent = rent.get();
            updatedRent.setStartDate(rentDetails.getStartDate());
            updatedRent.setEndDate(rentDetails.getEndDate());
            updatedRent.setDailyCost(rentDetails.getDailyCost());
            updatedRent.setTotalCost(rentDetails.getTotalCost());
            updatedRent.setPaid(rentDetails.isPaid());
            return rentRepository.save(updatedRent);
        }else{
            //Noleggio non presente
            throw new RuntimeException("Rent not found with id : " + id);
        }
    }

    //elimina noleggio su id Service
    public void deleteRent(Long id){
        rentRepository.deleteById(id);
    }
}
