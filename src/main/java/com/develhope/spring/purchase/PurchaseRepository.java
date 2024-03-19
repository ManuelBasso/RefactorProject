package com.develhope.spring.purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;


@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseInfo, Long> {

    List<PurchaseInfo> findPurchaseBySellerId(Long sellerId);

    @Query(value = "SELECT * FROM purchases p WHERE purchase_date BETWEEN :startDate AND :endDate AND seller_id = :sellerId", nativeQuery = true)
    List<PurchaseInfo> findPurchasesBetweenDates(@Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate, @Param("sellerId") Long sellerId);

    @Query(value = "SELECT SUM(p.total_price) FROM purchases p WHERE p.purchase_date BETWEEN :startDate AND :endDate AND p.seller_id = :sellerId", nativeQuery = true)
    Double cashOutForSellerInDate(@Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate, @Param("sellerId") Long sellerId);

}
