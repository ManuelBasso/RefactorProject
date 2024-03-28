package com.develhope.spring.order;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.develhope.spring.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderStatus(OrderStatus status);

    // Da testare
    List<Order> findByCustomer_Id(long userId);


    Optional<Order> findByVehicleAndOrderStatus(Vehicle vehicle, OrderStatus completed);

    void deleteByCustomer_Id(Long userId);


    /*
     * @Query(value = "SELECT ORDERSTATUS FROM ORDERS o WHERE o. ")
     * List<OrderInfo> getOrdersByStatus(OrderStatus status);
     */
}
