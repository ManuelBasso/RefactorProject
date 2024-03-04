package com.develhope.spring.user.admin;

import java.time.Duration;
import java.time.OffsetDateTime;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.car.VehicleRepository;
import com.develhope.spring.car.VehicleStatus;

import com.develhope.spring.configurations.exception.OrderRentCreationException;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.order.OrderRepository;
import com.develhope.spring.order.OrderStatus;
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

    // --------------- logica dei controller per operazioni sui ordini -------------

    // tutti i veicoli
    // ok
    public ResponseEntity<Object> getVehicle() {
        return ResponseEntity.ok(vehicleRepository.findAll());
    }

    // aggiunzione di un veicolo
    // ok
    public ResponseEntity<Object> addVehicle(Vehicle newVehicle) {
        Vehicle addVehicle = new Vehicle();
        addVehicle.setAccessories(newVehicle.getAccessories());
        addVehicle.setBrand(newVehicle.getBrand());
        addVehicle.setColor(newVehicle.getColor());
        addVehicle.setDiscount(newVehicle.getDiscount());
        addVehicle.setDisplacement(newVehicle.getDisplacement());
        addVehicle.setFuelType(newVehicle.getFuelType());
        addVehicle.setGearboxType(newVehicle.getGearboxType());
        addVehicle.setIsAvailable(newVehicle.getIsAvailable());
        addVehicle.setIsNew(newVehicle.getIsNew());
        addVehicle.setModel(newVehicle.getModel());
        addVehicle.setPower(newVehicle.getPower());
        addVehicle.setPrice(newVehicle.getPrice());
        addVehicle.setYearOfRegistration(newVehicle.getYearOfRegistration());

        return ResponseEntity.ok(vehicleRepository.save(addVehicle));
    }

    // metodo per la modifica di un solo parametro del veicolo
    // ok
    public ResponseEntity<Object> modifyVehicle(Long id, String choice, Vehicle vehicle) {
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
            return ResponseEntity.ok(vehicleRepository.saveAndFlush(optionalVehicle.get()));
        }
        return null;
    }

    // metodo per la modifica di tutti i parametri del veicolo
    // ok
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

}
