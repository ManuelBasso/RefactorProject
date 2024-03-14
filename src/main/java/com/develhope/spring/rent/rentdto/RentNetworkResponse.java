package com.develhope.spring.rent.rentdto;

import lombok.Builder;
import lombok.Data;

public class RentNetworkResponse {

    @Data
    @Builder
    public static class Success extends RentNetworkResponse {
        CustomerRentResponse rentResponse;
    }

    @Data
    @Builder
    public static class Error extends RentNetworkResponse{
        private int code;
        private String description;
    }
}

