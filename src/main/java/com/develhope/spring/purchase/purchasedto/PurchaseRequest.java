package com.develhope.spring.purchase.purchasedto;

import com.develhope.spring.car.Vehicle;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.user.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {
    private Double totalPrice;
    private OrderInfo order;
    private Users customer;
    private Vehicle vehicle;
    private Users seller;

    //aggiungiamo qui l'attributo in questione
    //questa dovrà essere String...quindi attenzione a purchaseModel

    private String purchaseDate;
}
