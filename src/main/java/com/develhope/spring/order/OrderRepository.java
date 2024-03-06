package com.develhope.spring.order;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderInfo, Long> {

    List<OrderInfo> findByOrderStatus(OrderStatus status);

    // Da testare
    List<OrderInfo> findByCustomer_Id(long userId);

    /*
     * @Query(value = "SELECT ORDERSTATUS FROM ORDERS o WHERE o. ")
     * List<OrderInfo> getOrdersByStatus(OrderStatus status);
     */
}
