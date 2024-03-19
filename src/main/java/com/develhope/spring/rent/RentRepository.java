package com.develhope.spring.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface RentRepository extends JpaRepository<RentInfo, Long> {

    void deleteByCustomer_Id(Long userId);
/*
    //OK for RENT
    @Query(value = "SELECT SUM(r.total_cost) FROM Rents r WHERE r.startDate BETWEEN :startDate AND :endDate AND r.seller_id = :sellerId")
    Double cashOutForSellerInDateRent(@Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate, @Param("sellerId") Long sellerId);
*/
}