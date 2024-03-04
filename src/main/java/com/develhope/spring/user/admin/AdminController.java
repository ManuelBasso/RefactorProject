package com.develhope.spring.user.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleStatus;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.rent.RentInfo;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Admin options", description = "Here are all functions needed for our admins")
@RestController
public class AdminController {

    @Autowired
    private AdminServices adminServices;

    // --------------- controller per operazioni sui veicoli -------------

    // vedere quali veicolo esistono , accessibile a tutti i tipi di utente
    // funziona
    @GetMapping("/public/gettAllVehicle")
    public ResponseEntity<Object> getVehicle() {
        return adminServices.getVehicle();
    }

    // creazione nuovo veicolo
    // funziona
    @PostMapping("/admin/addVehicle")
    public ResponseEntity<Object> addAVehicle(@RequestBody Vehicle newVehicle) {
        return adminServices.addVehicle(newVehicle);
    }

    // modifica dei parametri di un veicolo
    // funziona
    @PutMapping("/admin/{id}/modifyAVehicle")
    public ResponseEntity<Object> modifVehicleById(@PathVariable Long id, @RequestParam String choice,
            @RequestBody Vehicle vehicle) {
        return adminServices.modifyVehicle(id, choice, vehicle);
    }

    // eliminazione veicolo tramite id
    // funziona ma prima bisogna svuotare i parametri del veicolo in questione
    // con i seguenti comandi
    // DELETE FROM orders WHERE vehicle_id = :vehicleId;
    // DELETE FROM rents WHERE vehicle_id = :vehicleId;
    @DeleteMapping("/admin/{id}/deleteVehicleById")
    public boolean deleteVehicleById(@PathVariable Long id) {
        return adminServices.deleteVehicle(id);
    }

    // cambio dello stato di un veicolo
    // funziona
    @PatchMapping("/admin/{id}/changeStatusOfAVehicle")
    public ResponseEntity<Object> changeStatusOfAVehicle(@PathVariable Long id, @RequestParam VehicleStatus status) {
        return adminServices.changeStatusVehicle(id, status);
    }

    // --------------- controller per operazioni sui ordini -------------

    // ottenere tutti gli ordini
    // funziona
    @GetMapping("/admin/getAllOrder")
    public ResponseEntity<Object> getOrder() {
        return adminServices.getOrder();
    }

    // creazione nuovo ordine per un utente specifico
    // funziona
    @PostMapping("/admin/{id}/createOrderForAUser")
    public ResponseEntity<Object> creatOrderForUser(@PathVariable Long id, @RequestParam Long vehicle_Id,
            @RequestParam boolean advance) {
        return adminServices.createOrderForAUser(id, vehicle_Id, advance);
    }

    // eliminazione di un ordine per un cliente tramite id
    // funziona
    @DeleteMapping("/admin/{id}/deleteAOrderById")
    public boolean deleteOrderById(@PathVariable Long id) {
        return adminServices.deleteOrder(id);
    }

    // modifica di un ordine
    // funziona
    @PutMapping("/admin/{id}/modifyOrder")
    public ResponseEntity<Object> modifyOrderById(@PathVariable Long id, @RequestParam Long userid,
            @RequestParam String choice, @RequestBody OrderInfo order) {
        return adminServices.modifyOrderBy(id, choice, order, userid);
    }

    // --------------- controller per operazioni sui noleggi -------------

    // ottenere tutti gli noleggio
    // funziona
    @GetMapping("/admin/getAllRent")
    public ResponseEntity<Object> getRent() {
        return adminServices.getallRent();
    }

    // creazione nuovo noleggio per un utente specifico
    @PostMapping("/admin/{id}/createRentForAUser")
    public ResponseEntity<Object> creatRentForUser(@PathVariable Long id, @RequestParam Long vehicle_Id,
            @RequestParam String startDate,
            @RequestParam String endDate, @RequestParam Double dailyCost) {
        return adminServices.createRentForAUser(id, vehicle_Id, startDate, endDate, dailyCost);
    }

    // eliminazione di un noleggio per un cliente tramite id
    // funziona ma prima bisogna svuotare i parametri del noleggio in questione
    // come la delete del veicolo
    @DeleteMapping("/admin/{id}/deleteRentById")
    public boolean deleteRentById(@PathVariable Long id) {
        return adminServices.deleteRent(id);
    }

    // modifica di un noleggio
    @PutMapping("/admin/{id}/modifyRent")
    public ResponseEntity<Object> modifyRentById(@PathVariable Long id,
            @RequestParam String choice, @RequestBody RentInfo rent) {
        return adminServices.modifyRentById(id, choice, rent);
    }
}