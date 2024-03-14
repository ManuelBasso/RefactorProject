package com.develhope.spring.purchase.purchasedto;

import com.develhope.spring.user.userdto.UserResponse;
import lombok.Builder;
import lombok.Data;

public class PurchaseNetworkResponse {

    @Data
    @Builder
    public static class Success extends PurchaseNetworkResponse {
        PurchaseResponse purchaseResponse;
    }

    @Data
    @Builder
    public static class Error extends PurchaseNetworkResponse {
        private int code;
        private String description;
    }
}


