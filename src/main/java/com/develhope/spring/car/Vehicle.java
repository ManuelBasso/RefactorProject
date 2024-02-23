package com.develhope.spring.car;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Vehicles")
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    /*
     * @OneToOne(mappedBy = "newVehicle")
     * private OrderInfo orderInfo;
     */

    private String brand; // Marca
    private String model; // Modello
    private String color; // Colore
    private String gearboxType; // Tipo Cambio
    private String fuelType; // Alimentazione
    private String accessories; // Eventuali accessori a corredo

    private Integer displacement; // Cilindrata
    private Integer power; // Potenza
    private Integer yearOfRegistration; // Anno immatricolazione

    private Double price; // Prezzo
    private Double discount; // Eventuale sconto sul prezzo di listino

    private Boolean isNew; // Flag che identifica se il veicolo è nuovo o usato

    @Enumerated(EnumType.STRING)
    private VehicleStatus isAvailable; // Flag che identifica se il veicolo è ordinabile, acquistabile o non più
                                       // disponibile

    /*
     * @OneToMany(mappedBy = "rentedVehicle")
     * private List<RentInfo> rentInfos;
     * 
     * @OneToMany(mappedBy = "orderedVehicle")
     * private List<OrderInfo> orderInfos;
     */
}

/*
 * I veicoli possono essere di diversi tipi: auto, moto, scooter, furgoni. Per
 * ogni veicolo avremo una serie di attributi:
 * 
 * Marca
 * Modello
 * Cilindrata
 * Colore
 * Potenza
 * Tipo Cambio
 * Anno immatricolazione
 * Alimentazione
 * Prezzo
 * Eventuale sconto sul prezzo di listino
 * Eventuali accessori a corredo
 * Flag che identifichi se il veicolo è nuovo o usato
 * Flag che identifichi se il veicolo è ordinabile, acquistabile o non più
 * disponibile
 * 
 */