package com.develhope.spring.rent.rentdto;

import com.develhope.spring.order.orderdto.OrderNetworkResponse;
import com.develhope.spring.order.orderdto.OrderResponse;
import lombok.Builder;
import lombok.Data;

public class RentNetworkResponse {

    @Data
    @Builder
    public static class Success extends RentNetworkResponse {
        RentResponse rentResponse;
    }

    @Data
    @Builder
    public static class Error extends RentNetworkResponse{
        private int code;
        private String description;
    }
}

