package com.develhope.spring.repositories;

import com.develhope.spring.entities.StatusOfVehicle.OrderInfo;
import com.develhope.spring.entities.StatusOfVehicle.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderInfo, Long> {

    List<OrderInfo> findByOrderStatus(OrderStatus status);

    /*@Query(value = "SELECT ORDERSTATUS FROM ORDERS o WHERE o. ")
    List<OrderInfo> getOrdersByStatus(OrderStatus status);*/
}
