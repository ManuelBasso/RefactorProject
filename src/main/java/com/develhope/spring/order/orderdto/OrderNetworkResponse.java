package com.develhope.spring.order.orderdto;

import com.develhope.spring.user.userdto.UserNetworkResponse;
import com.develhope.spring.user.userdto.UserResponse;
import lombok.Builder;
import lombok.Data;

public class OrderNetworkResponse {

    @Data
    @Builder
    public static class Success extends OrderNetworkResponse {
        OrderResponse orderResponse;
    }

    @Data
    @Builder
    public static class Error extends OrderNetworkResponse{
        private int code;
        private String description;
    }
}
