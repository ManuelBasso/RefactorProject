package com.develhope.spring.order.orderdto;

import lombok.Builder;
import lombok.Data;

public class CustomerOrderNetworkResponse {

    @Data
    @Builder
    public static class Success extends CustomerOrderNetworkResponse {
        CustomerOrderResponse orderResponse;
    }

    @Data
    @Builder
    public static class Error extends CustomerOrderNetworkResponse {
        private int code;
        private String description;
    }
}
