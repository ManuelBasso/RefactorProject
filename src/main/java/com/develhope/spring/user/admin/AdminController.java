package com.develhope.spring.user.admin;

import com.develhope.spring.car.VehicleStatus;
import com.develhope.spring.car.cardto.VehicleRequest;
import com.develhope.spring.car.cardto.VehicleResponse;
import com.develhope.spring.order.orderdto.OrderRequest;
import com.develhope.spring.order.orderdto.OrderResponse;
import com.develhope.spring.purchase.purchasedto.PurchaseResponse;
import com.develhope.spring.rent.rentdto.RentRequest;
import com.develhope.spring.rent.rentdto.RentResponse;
import com.develhope.spring.user.userdto.UserRequest;
import com.develhope.spring.user.userdto.UserResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@Tag(name = "Admin options", description = "Here are all functions needed for our admins")
@RestController
public class AdminController {

    @Autowired
    private AdminServices adminServices;

    // --------------- controller per operazioni sui veicoli -------------

    // vedere quali veicolo esistono , accessibile a tutti i tipi di utente

    @GetMapping("/admin/gettAllVehicle")
    public ResponseEntity<?> getVehicle() {
        List<VehicleResponse> vehicleList = adminServices.getVehicle();
        if (vehicleList == null || vehicleList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle list is null or empty");
        }
        return ResponseEntity.ok(vehicleList);
    }

    // creazione nuovo veicolo
    @PostMapping("/admin/addVehicle")
    public ResponseEntity<VehicleResponse> addAVehicle(@RequestBody VehicleRequest newVehicleRequest) {
        return ResponseEntity.ok(adminServices.addVehicle(newVehicleRequest));
    }

    // modifica dei parametri di un veicolo
    @PutMapping("/admin/{id}/modifyAVehicle")
    public ResponseEntity<?> modifVehicleById(@PathVariable Long id, @RequestParam String choice,
                                              @RequestBody VehicleRequest vehicleRequest) {
        VehicleResponse modifiedVehicle = adminServices.modifyVehicle(id, choice, vehicleRequest);
        if (modifiedVehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not Found");
        } else {
            return ResponseEntity.ok(modifiedVehicle);
        }
    }

    // eliminazione veicolo tramite id
    @DeleteMapping("/admin/{id}/deleteVehicleById")
    public ResponseEntity<?> deleteVehicleById(@PathVariable Long id) {
        boolean vehicleFoundAndDeleted = adminServices.deleteVehicle(id);
        if (vehicleFoundAndDeleted) {
            return ResponseEntity.ok(vehicleFoundAndDeleted);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not Found");
        }
    }

    // cambio dello stato di un veicolo
    @PatchMapping("/admin/{id}/changeStatusOfAVehicle")
    public ResponseEntity<?> changeStatusOfAVehicle(@PathVariable Long id,
                                                    @RequestParam VehicleStatus status) {
        VehicleResponse modifiedVehicle = adminServices.changeStatusVehicle(id, status);
        if (modifiedVehicle != null) {
            return ResponseEntity.ok(modifiedVehicle);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not Found");
        }

    }

    // --------------- controller per operazioni sui ordini -------------

    // ottenere tutti gli ordini
    @GetMapping("/admin/getAllOrder")
    public ResponseEntity<?> getOrder() {
        List<OrderResponse> orderList = adminServices.getOrder();
        if (orderList == null || orderList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order List is null or empty");
        } else {
            return ResponseEntity.ok(orderList);
        }
    }

    // creazione nuovo ordine per un utente specifico
    @PostMapping("/admin/{id}/createOrderForAUser")
    public ResponseEntity<OrderResponse> creatOrderForUser(@PathVariable Long id, @RequestParam Long vehicle_Id, @RequestParam Long seller_id,
                                                           @RequestParam boolean advance) {
        OrderResponse newOrder = adminServices.createOrderForAUser(id, vehicle_Id, seller_id, advance);
        return ResponseEntity.ok(newOrder);
    }

    // eliminazione di un ordine per un cliente tramite id
    @DeleteMapping("/admin/{id}/deleteAOrderById")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
        boolean deletedOrder = adminServices.deleteOrder(id);
        if (deletedOrder) {
            return ResponseEntity.ok(deletedOrder);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("order not Found");
        }
    }

    // modifica di un ordine
    @PutMapping("/admin/{id}/modifyOrder")
    public ResponseEntity<?> modifyOrderById(@PathVariable Long id, Long customerId,
                                             Long sellerId, Long vehicleId,
                                             @RequestParam String choice, @RequestBody OrderRequest order) {
        OrderResponse modifiedOrder = adminServices.modifyOrderBy(id, choice, order, customerId, sellerId, vehicleId);
        if (modifiedOrder == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("order not Found or invalid user or vehicle id");
        } else {
            return ResponseEntity.ok(modifiedOrder);
        }
    }

    // --------------- controller per operazioni sui noleggi -------------

    // ottenere tutti i noleggi
    @GetMapping("/admin/getAllRent")
    public ResponseEntity<?> getRent() {
        List<RentResponse> rentList = adminServices.getallRent();
        if (rentList != null && !rentList.isEmpty()) {
            return ResponseEntity.ok(rentList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("rent list is null or empty");
        }
    }

    // creazione nuovo noleggio per un utente specifico
    @PostMapping("/admin/{id}/createRentForAUser")
    public ResponseEntity<?> creatRentForUser(@PathVariable Long id, @RequestParam Long vehicle_Id,
                                              @RequestParam String startDate,
                                              @RequestParam String endDate, @RequestParam Double dailyCost) {
        RentResponse newRent = adminServices.createRentForAUser(id, vehicle_Id, startDate, endDate, dailyCost);
        return ResponseEntity.ok(newRent);
    }

    // eliminazione di un noleggio per un cliente tramite id
    // funziona ma prima bisogna svuotare i parametri del noleggio in questione
    // come la delete del veicolo
    @DeleteMapping("/admin/{id}/deleteRentById")
    public ResponseEntity<?> deleteRentById(@PathVariable Long id) {
        boolean rentIsDeleted = adminServices.deleteRent(id);
        if (rentIsDeleted) {
            return ResponseEntity.ok(rentIsDeleted);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("rent not found");
        }
    }

    // modifica di un noleggio
    @PutMapping("/admin/{id}/modifyRent")
    public ResponseEntity<?> modifyRentById(@PathVariable Long id,
                                            @RequestParam String choice, @RequestBody RentRequest rentRequest, Long customerId,
                                            Long vehicleId, Long sellerId) {
        RentResponse modifiedRent = adminServices.modifyRentById(id, choice, rentRequest, customerId, vehicleId,
                sellerId);
        if (modifiedRent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rent not found or invalid user or vehicle id");
        } else {
            return ResponseEntity.ok(modifiedRent);
        }
    }

    // --------------- controller per operazioni sugli acquisti -------------

    @GetMapping("/admin/getAllPurchase")
    public ResponseEntity<?> getPurchase() {
        List<PurchaseResponse> purchaseList = adminServices.getPurchase();
        if (purchaseList == null || purchaseList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("purchase list is null or empty");
        } else {
            return ResponseEntity.ok(purchaseList);
        }
    }

    @PostMapping("/admin/{id}/createPurchaseForAUser")
    public ResponseEntity<PurchaseResponse> createPurchaseForUser(@PathVariable Long id,
                                                                  @RequestParam Long vehicle_Id, @RequestParam Long seller_Id) {
        PurchaseResponse newPurchase = adminServices.createPurchaseForAUser(id, vehicle_Id, seller_Id);
        return ResponseEntity.ok(newPurchase);
    }

    @DeleteMapping("/admin/{id}/deletePurchaseById")
    public ResponseEntity<?> deletePurchaseById(@PathVariable Long id) {
        boolean purchaseIsDeleted = adminServices.deletePurchase(id);
        if (purchaseIsDeleted) {
            return ResponseEntity.ok(purchaseIsDeleted);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("purchase not Found");
        }
    }
    // devo chiedere ad antonio cosa devo modificare in un acquisto

    // @PutMapping("/admin/{id}/modifyPurchase")
    // public ResponseEntity<Object> modifyPurchaseById(@PathVariable Long id,
    // @RequestParam Long userId,
    // @RequestParam String choice, @RequestBody PurchaseInfo purchase) {
    // return adminServices.modifyPurchaseById(id, userId, choice, purchase);
    // }

    //Rotta Vendite di un particolare Seller
    @GetMapping("/admin/getPurchaseBySellerId")
    public ResponseEntity<?> getPurchaseBySeller(@RequestParam Long seller_Id, @RequestParam OffsetDateTime startDate, @RequestParam OffsetDateTime endDate) {
        List<PurchaseResponse> orderList = adminServices.getPurchaseBySellerInDate(startDate, endDate, seller_Id);
        if (orderList == null || orderList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The seller has no sales or the sales list is empty.");
        }
        return ResponseEntity.ok(orderList);
    }


        //Rotta Vendite di un particolare Seller (Incasso)
        @GetMapping("/admin/getCashOutBySellerId")
        public ResponseEntity<?> getCashOutBySeller(@RequestParam Long sellerId, @RequestParam OffsetDateTime startDate, @RequestParam OffsetDateTime endDate) {
            Double cashOut = adminServices.getPurchaseBySellerInDateCashOut(startDate, endDate, sellerId);

            if (cashOut == null || cashOut == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cash-out for the seller in the specified period.");
            }
            return ResponseEntity.ok("Total cash-out for the seller: " + cashOut + " euro.");
        }

/*
    //Rotta CashoutTotale by seller_id
    @GetMapping("/admin/getCashOutBySellerId")
    public ResponseEntity<?> getCashOutBySeller(@RequestParam Long sellerId, @RequestParam OffsetDateTime startDate, @RequestParam OffsetDateTime endDate) {
        Double cashOut = adminServices.getCashOutBySellerInDate(startDate, endDate, sellerId);

        if (cashOut == null || cashOut == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cash-out for the seller in the specified period.");
        }
        return ResponseEntity.ok("Total cash-out for the seller: " + cashOut + " euro.");
    }

 */
    //Rotta per eliminare user
    @DeleteMapping("/admin/deleteUsers/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            adminServices.deleteUserById(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user.");
        }
    }

    //Rotta per modifica di un utente

    @PutMapping("/admin/{id}/modifyUser")
    public ResponseEntity<?> modifyUsersById(@PathVariable Long user_id, @RequestParam String choice, @RequestBody UserRequest userRequest) {

        UserResponse modifiedUser = adminServices.modifyUserById(user_id, userRequest, choice);
        if (modifiedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not Found or invalid id");
        } else {
            return ResponseEntity.ok(modifiedUser);
        }
    }

    //Cancellare un venditore
    @DeleteMapping("/admin/deleteSeller/{sellerId}")
    public ResponseEntity<?> deleteSeller(@PathVariable Long sellerId) {
        try {
            adminServices.deleteSellerById(sellerId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user.");
        }
    }


    //Modificare un venditore
    @PutMapping("/admin/{id}/modifySeller")
    public ResponseEntity<?> modifySellerById(@PathVariable Long seller_id, @RequestParam String choice, @RequestBody UserRequest userRequest) {

        UserResponse modifiedSeller = adminServices.modifySellerById(seller_id, userRequest, choice);
        if (modifiedSeller == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("seller not Found or invalid id");
        } else {
            return ResponseEntity.ok(modifiedSeller);
        }
    }

}