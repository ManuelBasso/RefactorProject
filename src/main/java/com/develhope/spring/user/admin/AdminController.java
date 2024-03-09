package com.develhope.spring.user.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleStatus;
import com.develhope.spring.car.cardto.VehicleRequest;
import com.develhope.spring.car.cardto.VehicleResponse;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.order.orderdto.OrderRequest;
import com.develhope.spring.order.orderdto.OrderResponse;
import com.develhope.spring.rent.RentInfo;
import com.develhope.spring.rent.rentdto.RentRequest;
import com.develhope.spring.rent.rentdto.RentResponse;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    @GetMapping("/admin/gettAllVehicle")
    public ResponseEntity<List<VehicleResponse>> getVehicle() {
        return adminServices.getVehicle();
    }

    // creazione nuovo veicolo
    // funziona
    @PostMapping("/admin/addVehicle")
    public ResponseEntity<VehicleResponse> addAVehicle(@RequestBody VehicleRequest newVehicleRequest) {
        return adminServices.addVehicle(newVehicleRequest);
    }

    // modifica dei parametri di un veicolo
    // funziona
    @PutMapping("/admin/{id}/modifyAVehicle")
    public ResponseEntity<VehicleResponse> modifVehicleById(@PathVariable Long id, @RequestParam String choice,
            @RequestBody VehicleRequest vehicleRequest) {
        return adminServices.modifyVehicle(id, choice, vehicleRequest);
    }

    // eliminazione veicolo tramite id
    @DeleteMapping("/admin/{id}/deleteVehicleById")
    public ResponseEntity<Void> deleteVehicleById(@PathVariable Long id) {
        return adminServices.deleteVehicle(id);
    }

    // cambio dello stato di un veicolo
    // funziona
    @PatchMapping("/admin/{id}/changeStatusOfAVehicle")
    public ResponseEntity<VehicleResponse> changeStatusOfAVehicle(@PathVariable Long id,
            @RequestParam VehicleStatus status) {
        return adminServices.changeStatusVehicle(id, status);
    }

    // --------------- controller per operazioni sui ordini -------------

    // ottenere tutti gli ordini
    // funziona
    @GetMapping("/admin/getAllOrder")
    public ResponseEntity<List<OrderResponse>> getOrder() {
        return adminServices.getOrder();
    }

    // creazione nuovo ordine per un utente specifico
    // funziona
    @PostMapping("/admin/{id}/createOrderForAUser")
    public ResponseEntity<OrderResponse> creatOrderForUser(@PathVariable Long id, @RequestParam Long vehicle_Id,
            @RequestParam boolean advance) {
        return adminServices.createOrderForAUser(id, vehicle_Id, advance);
    }

    // eliminazione di un ordine per un cliente tramite id
    // funziona
    @DeleteMapping("/admin/{id}/deleteAOrderById")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        return adminServices.deleteOrder(id);
    }

    // modifica di un ordine
    // funziona
    @PutMapping("/admin/{id}/modifyOrder")
    public ResponseEntity<OrderResponse> modifyOrderById(@PathVariable Long id, Long customerId,
            Long sellerId, Long vehicleId,
            @RequestParam String choice, @RequestBody OrderRequest order) {
        return adminServices.modifyOrderBy(id, choice, order, customerId, sellerId, vehicleId);
    }

    // --------------- controller per operazioni sui noleggi -------------

    // ottenere tutti gli noleggio
    // funziona
    @GetMapping("/admin/getAllRent")
    public ResponseEntity<List<RentResponse>> getRent() {
        return adminServices.getallRent();
    }

    // creazione nuovo noleggio per un utente specifico
    @PostMapping("/admin/{id}/createRentForAUser")
    public ResponseEntity<RentResponse> creatRentForUser(@PathVariable Long id, @RequestParam Long vehicle_Id,
            @RequestParam String startDate,
            @RequestParam String endDate, @RequestParam Double dailyCost) {
        return adminServices.createRentForAUser(id, vehicle_Id, startDate, endDate, dailyCost);
    }

    // eliminazione di un noleggio per un cliente tramite id
    // funziona ma prima bisogna svuotare i parametri del noleggio in questione
    // come la delete del veicolo
    @DeleteMapping("/admin/{id}/deleteRentById")
    public ResponseEntity<Void> deleteRentById(@PathVariable Long id) {
        return adminServices.deleteRent(id);
    }

    // modifica di un noleggio
    @PutMapping("/admin/{id}/modifyRent")
    public ResponseEntity<RentResponse> modifyRentById(@PathVariable Long id,
            @RequestParam String choice, @RequestBody RentRequest rentRequest, Long customerId,
            Long vehicleId, Long sellerId) {
        return adminServices.modifyRentById(id, choice, rentRequest, customerId, vehicleId, sellerId);
    }

    // --------------- controller per operazioni sugli acquisti -------------

    @GetMapping("/admin/getAllPurchase")
    public ResponseEntity<Object> getPurchase() {
        return adminServices.getPurchase();
    }

    @PostMapping("/admin/{id}/createPurchaseForAUser")
    public ResponseEntity<Object> createPurchaseForUser(@PathVariable Long id, @RequestParam Long vehicle_Id) {
        return adminServices.createPurchaseForAUser(id, vehicle_Id);
    }

    @DeleteMapping("/admin/{id}/deletePurchaseById")
    public boolean deletePurchaseById(@PathVariable Long id) {
        return adminServices.deletePurchase(id);
    }
    // devo chiedere ad antonio cosa devo modificare in un acquisto

    // @PutMapping("/admin/{id}/modifyPurchase")
    // public ResponseEntity<Object> modifyPurchaseById(@PathVariable Long id,
    // @RequestParam Long userId,
    // @RequestParam String choice, @RequestBody PurchaseInfo purchase) {
    // return adminServices.modifyPurchaseById(id, userId, choice, purchase);
    // }
}