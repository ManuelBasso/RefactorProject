package com.develhope.spring.purchase.purchasedto;

import lombok.Builder;
import lombok.Data;

public class CustomerPurchaseNetworkResponse {

    @Data
    @Builder
    public static class Success extends CustomerPurchaseNetworkResponse {
        CustomerPurchaseResponse purchaseResponse;
    }

    @Data
    @Builder
    public static class Error extends CustomerPurchaseNetworkResponse {
        private int code;
        private String description;
    }
}
