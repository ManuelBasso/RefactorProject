package com.develhope.spring.user.admin;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleModel;
import com.develhope.spring.car.VehicleRepository;
import com.develhope.spring.car.VehicleStatus;
import com.develhope.spring.car.cardto.VehicleRequest;
import com.develhope.spring.car.cardto.VehicleResponse;
import com.develhope.spring.configurations.exception.OrderRentCreationException;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.order.OrderRepository;
import com.develhope.spring.order.OrderStatus;
import com.develhope.spring.purchase.PurchaseInfo;
import com.develhope.spring.purchase.PurchaseRepository;
import com.develhope.spring.rent.RentInfo;
import com.develhope.spring.rent.RentRepository;
import com.develhope.spring.user.Users;
import com.develhope.spring.user.UserRepository;

@Service
public class AdminServices {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    // --------------- logica dei controller per operazioni sui ordini -------------

    // tutti i veicoli
    // ok
    public ResponseEntity<List<VehicleResponse>> getVehicle() {
        List<Vehicle> response = vehicleRepository.findAll();
        List<VehicleResponse> result = new ArrayList<>();
        for (Vehicle vehicle : response) {
            VehicleModel vehicleModel = VehicleModel.mapEntityToModel(vehicle);
            result.add(VehicleModel.mapModelToResponse(vehicleModel));
        }
        return ResponseEntity.ok(result);
    }

    // aggiunzione di un veicolo
    // ok
    public ResponseEntity<VehicleResponse> addVehicle(VehicleRequest newVehicleRequest) {
        VehicleModel vehicleRequestModel = VehicleModel.mapRequestToModel(newVehicleRequest);
        Vehicle vehicleRequestEntity = VehicleModel.mapModelToEntity(vehicleRequestModel);
        Vehicle savedVehicle = vehicleRepository.saveAndFlush(vehicleRequestEntity);
        VehicleModel vehicleResponseModel = VehicleModel.mapEntityToModel(savedVehicle);
        return ResponseEntity.ok(VehicleModel.mapModelToResponse(vehicleResponseModel));
    }

    // metodo per la modifica di un solo parametro del veicolo
    // ok
    public ResponseEntity<VehicleResponse> modifyVehicle(Long id, String choice, VehicleRequest vehicleRequest) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicleRequestEntity = optionalVehicle.get();
            switch (choice) {
                case "brand":
                    vehicleRequestEntity.setBrand(vehicleRequest.getBrand());
                    break;
                case "model":
                    vehicleRequestEntity.setModel(vehicleRequest.getModel());
                    break;
                case "color":
                    vehicleRequestEntity.setColor(vehicleRequest.getColor());
                    break;
                case "gear":
                    vehicleRequestEntity.setGearboxType(vehicleRequest.getGearboxType());
                    break;
                case "fuel":
                    vehicleRequestEntity.setFuelType(vehicleRequest.getFuelType());
                    break;
                case "accessories":
                    vehicleRequestEntity.setAccessories(vehicleRequest.getAccessories());
                    break;
                case "displacement":
                    vehicleRequestEntity.setDisplacement(vehicleRequest.getDisplacement());
                    break;
                case "power":
                    vehicleRequestEntity.setPower(vehicleRequest.getPower());
                    break;
                case "year_registration":
                    vehicleRequestEntity.setYearOfRegistration(vehicleRequest.getYearOfRegistration());
                    break;
                case "price":
                    vehicleRequestEntity.setPrice(vehicleRequest.getPrice());
                    break;
                case "discount":
                    vehicleRequestEntity.setDiscount(vehicleRequest.getDiscount());
                    break;
                case "new/used":
                    vehicleRequestEntity.setIsNew(vehicleRequest.getIsNew());
                    break;
                case "all":
                    modifyAllPartOfVehicle(vehicleRequestEntity, vehicleRequest);
                    break;
                default:

                    break;
            }
            Vehicle modifiedVehicle = vehicleRepository.saveAndFlush(vehicleRequestEntity);
            VehicleModel modifiedVehicleModel = VehicleModel.mapEntityToModel(modifiedVehicle);
            VehicleResponse vehicleResponse = VehicleModel.mapModelToResponse(modifiedVehicleModel);
            return ResponseEntity.ok(vehicleResponse);
        }
        return null;
    }

    // metodo per la modifica di tutti i parametri del veicolo
    // ok
    private void modifyAllPartOfVehicle(Vehicle vehicleRequestEntity, VehicleRequest vehicleRequest) {
        vehicleRequestEntity.setBrand(vehicleRequest.getBrand());
        vehicleRequestEntity.setModel(vehicleRequest.getModel());
        vehicleRequestEntity.setColor(vehicleRequest.getColor());
        vehicleRequestEntity.setGearboxType(vehicleRequest.getGearboxType());
        vehicleRequestEntity.setFuelType(vehicleRequest.getFuelType());
        vehicleRequestEntity.setAccessories(vehicleRequest.getAccessories());
        vehicleRequestEntity.setDisplacement(vehicleRequest.getDisplacement());
        vehicleRequestEntity.setPower(vehicleRequest.getPower());
        vehicleRequestEntity.setYearOfRegistration(vehicleRequest.getYearOfRegistration());
        vehicleRequestEntity.setPrice(vehicleRequest.getPrice());
        vehicleRequestEntity.setDiscount(vehicleRequest.getDiscount());
        vehicleRequestEntity.setIsNew(vehicleRequest.getIsNew());
    }

    // eliminazione di un veicolo
    // ok
    public boolean deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // cambio dello Status di un veicolo
    // ok
    public ResponseEntity<Object> changeStatusVehicle(Long id, VehicleStatus status) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle updatedVehicle = optionalVehicle.get();
            updatedVehicle.setIsAvailable(status);
            return ResponseEntity.ok(vehicleRepository.saveAndFlush(updatedVehicle));
        }
        return null;
    }

    // --------------- logica dei controller per operazioni sui ordini -------------

    // ottenere tutti gli ordini
    // ok
    public ResponseEntity<Object> getOrder() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    // creazione ordine per un utente tramite id
    // ok
    public ResponseEntity<Object> createOrderForAUser(Long user_id, Long vehicle_id, boolean advance)
            throws OrderRentCreationException {
        Optional<Users> user = userRepository.findById(user_id);
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicle_id);
        if (!user.isPresent() || !vehicle.isPresent()) {
            throw new OrderRentCreationException("Invalid user or vehicle ID");
        }
        if (vehicle.get().getIsAvailable() != VehicleStatus.AVAILABLE) {
            throw new OrderRentCreationException("Vehicle is not available for order");
        }
        OrderInfo newOrder = new OrderInfo();
        newOrder.setVehicle(vehicle.get());
        // se l'acquirente paga un anticipo si chiama il metodo getAdvancePaymentAmount
        // altrimenti si restituisce null
        // su advance payment
        newOrder.setAdvancePayment(advance ? getAdvancePaymentAmount(vehicle_id) : null);
        newOrder.setPaidInFull(false);
        newOrder.setCustomer(user.get());
        newOrder.setOrderStatus(OrderStatus.INCOMPLETE);
        try {
            vehicle.get().setIsAvailable(VehicleStatus.ORDERED);
            vehicleRepository.save(vehicle.get());
            return ResponseEntity.ok(orderRepository.save(newOrder));
        } catch (Exception e) {
            throw new OrderRentCreationException("Failed to create order");
        }
    }

    // calcola un aticipo di base che è yguale al trenta percento del costo
    // dell'auto
    private Double getAdvancePaymentAmount(Long vehicle_id) {
        Vehicle vehicle = vehicleRepository.getReferenceById(vehicle_id);
        double totalAmount = vehicle.getPrice() * 0.3;
        return totalAmount;
    }

    // eliminazione di un ordine per un cliente tramite id
    // ok
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // modifica di un ordine
    // ok
    public ResponseEntity<Object> modifyOrderBy(Long id, String choice, OrderInfo order, Long userId) {
        Optional<OrderInfo> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            switch (choice) {
                case "paidInFull":
                    optionalOrder.get().setPaidInFull(order.getPaidInFull());
                    break;
                case "orderStatus":
                    optionalOrder.get().setOrderStatus(order.getOrderStatus());
                    break;
                case "user":
                    Users user = userRepository.getReferenceById(userId);
                    optionalOrder.get().setCustomer(user);
                    break;
                case "all":
                    modifyAllPartOfOrder(id, choice, order, userId, optionalOrder);
                    break;
                default:

                    break;
            }
            return ResponseEntity.ok(orderRepository.saveAndFlush(optionalOrder.get()));
        }
        return null;
    }

    // modifica di tutti i parametri un ordine
    private Optional<OrderInfo> modifyAllPartOfOrder(Long id, String choice, OrderInfo order, Long userId,
            Optional<OrderInfo> optionalOrder) {
        Optional<OrderInfo> modifyOrder = optionalOrder;
        optionalOrder.get().setPaidInFull(order.getPaidInFull());
        optionalOrder.get().setOrderStatus(order.getOrderStatus());
        Users user = userRepository.getReferenceById(userId);
        optionalOrder.get().setCustomer(user);

        return modifyOrder;
    }

    // --------------- logica dei controller per operazioni sui noleggi
    // -------------

    // ottenere tutti i noleggi
    public ResponseEntity<Object> getallRent() {
        return ResponseEntity.ok(rentRepository.findAll());
    }

    // creazione noleggio per un utente tramite id
    public ResponseEntity<Object> createRentForAUser(Long user_id, Long vehicle_id, String startDate, String endDate,
            Double dailyCost) throws OrderRentCreationException {
        Optional<Users> user = userRepository.findById(user_id);
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicle_id);
        if (!user.isPresent() || !vehicle.isPresent()) {
            throw new OrderRentCreationException("Invalid user or vehicle ID");
        }
        if (vehicle.get().getIsAvailable() != VehicleStatus.AVAILABLE) {
            throw new OrderRentCreationException("Vehicle is not available for rent");
        }
        RentInfo newRent = new RentInfo();
        newRent.setVehicle(vehicle.get());
        newRent.setCustomer(user.get());
        newRent.setStartDate(startDate);
        newRent.setEndDate(endDate);
        // il costo giornaliero deve essere implementato con una logica simile
        // all'anticipo
        // quindi è da cambiare
        newRent.setDailyCost(dailyCost);
        newRent.setTotalCost(calculateTotalCost(startDate, endDate, dailyCost));
        newRent.setIsPaid(false);
        try {

            vehicle.get().setIsAvailable(VehicleStatus.NOT_AVAILABLE);
            vehicleRepository.save(vehicle.get());
            return ResponseEntity.ok(rentRepository.save(newRent));
        } catch (Exception e) {
            throw new OrderRentCreationException("Failed to create rent ");
        }
    }

    // calcolo totale del noleggio
    private Double calculateTotalCost(String startDate, String endDate, Double dailyCost) {
        OffsetDateTime rentStartDate = OffsetDateTime.parse(startDate);
        OffsetDateTime rentEndDate = OffsetDateTime.parse(endDate);

        Duration duration = Duration.between(rentStartDate, rentEndDate);
        long rentalDays = duration.toDays();

        double totalCost = rentalDays * dailyCost;
        return totalCost;
    }

    // eliminazione di un noleggio per un cliente tramite id
    // ok
    public boolean deleteRent(Long id) {
        if (rentRepository.existsById(id)) {
            rentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // modifica di un noleggio
    // ok
    public ResponseEntity<Object> modifyRentById(Long id, String choice, RentInfo rent) {
        Optional<RentInfo> optionalRent = rentRepository.findById(id);
        if (optionalRent.isPresent()) {
            switch (choice) {
                case "dailyCost":
                    optionalRent.get().setDailyCost(rent.getDailyCost());
                    break;
                case "startDate":
                    optionalRent.get().setStartDate(rent.getStartDate());
                    break;
                case "endDate":
                    optionalRent.get().setEndDate(rent.getEndDate());
                    break;
                case "all":
                    modifyAllPartOfRent(id, choice, rent, optionalRent);
                    break;
                default:

                    break;
            }
            return ResponseEntity.ok(rentRepository.saveAndFlush(optionalRent.get()));
        }
        return null;
    }

    // modifica di tutti i parametri di un noleggio
    private Optional<RentInfo> modifyAllPartOfRent(Long id, String choice, RentInfo rent,
            Optional<RentInfo> optionalRent) {
        Optional<RentInfo> modifyRent = optionalRent;
        optionalRent.get().setDailyCost(rent.getDailyCost());
        optionalRent.get().setStartDate(rent.getStartDate());
        optionalRent.get().setEndDate(rent.getEndDate());
        return modifyRent;
    }

    // --------------- logica dei controller per operazioni sugli acquisti
    // -------------

    // ottieni tutti gli acquisti
    public ResponseEntity<Object> getPurchase() {
        return ResponseEntity.ok(purchaseRepository.findAll());
    }

    // crea un acquisto per un utente
    public ResponseEntity<Object> createPurchaseForAUser(Long id, Long vehicle_Id) {
        Optional<Users> user = userRepository.findById(id);
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicle_Id);
        if (!user.isPresent() || !vehicle.isPresent()) {
            throw new OrderRentCreationException("Invalid user or vehicle ID");
        }
        if (vehicle.get().getIsAvailable() != VehicleStatus.AVAILABLE
                && vehicle.get().getIsAvailable() != VehicleStatus.ORDERED) {
            throw new OrderRentCreationException("Vehicle is not available for purchase or is already bought");
        }

        Optional<OrderInfo> order = orderRepository.findByVehicleAndOrderStatus(vehicle.get(),
                OrderStatus.COMPLETED);
        if (order.isPresent() && order.get().getCustomer().equals(user.get())) {
            return ResponseEntity.ok(purchaseIfOrderExist(vehicle, order));
        } else {
            return ResponseEntity.ok(purchaseWithoutOrder(user, vehicle));
        }

    }

    private Object purchaseWithoutOrder(Optional<Users> user, Optional<Vehicle> vehicle) {
        PurchaseInfo newPurchase2 = new PurchaseInfo();
        newPurchase2.setCustomer(user.get());
        newPurchase2.setVehicle(vehicle.get());
        newPurchase2.setTotalPrice(vehicle.get().getPrice());
        try {
            vehicle.get().setIsAvailable(VehicleStatus.BOUGHT);
            vehicleRepository.save(vehicle.get());
            return ResponseEntity.ok(purchaseRepository.save(newPurchase2));
        } catch (Exception e) {
            throw new OrderRentCreationException("Failed to create purchase ");
        }
    }

    public PurchaseInfo purchaseIfOrderExist(Optional<Vehicle> vehicle, Optional<OrderInfo> order) {
        PurchaseInfo newPurchase = new PurchaseInfo();
        newPurchase.setCustomer(order.get().getCustomer());
        newPurchase.setVehicle(order.get().getVehicle());
        newPurchase.setTotalPrice(vehicle.get().getPrice() - order.get().getAdvancePayment());
        newPurchase.setOrder(order.get());
        try {
            vehicle.get().setIsAvailable(VehicleStatus.BOUGHT);
            vehicleRepository.save(vehicle.get());
            return purchaseRepository.save(newPurchase);
        } catch (Exception e) {
            throw new OrderRentCreationException("Failed to create purchase with order");
        }
    }

    // elimina un acquisto per un utente
    public boolean deletePurchase(Long id) {
        if (purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // modifica un acquisto per un utente
    // public ResponseEntity<Object> modifyPurchaseById(Long id, Long userId, String
    // choice, PurchaseInfo purchase) {
    // switch (choice) {
    // case "totalPrice":

    // break;
    // case "customer":

    // break;

    // case "vehicle":

    // break;

    // default:
    // break;
    // }
    // }

}
