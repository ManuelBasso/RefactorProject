package com.develhope.spring.purchase;

import com.develhope.spring.vehicle.Vehicle;
import com.develhope.spring.order.Order;
import com.develhope.spring.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "purchases")
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    private Double totalPrice;

    @OneToOne
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
