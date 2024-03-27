package com.develhope.spring.user.userdto;

import lombok.Builder;
import lombok.Data;

public class UserNetworkResponse {

    @Data
    @Builder
    public static class Success extends UserNetworkResponse{
        UserResponse userResponse;
    }
    @Data
    @Builder
    public static class Error extends UserNetworkResponse{
        private int code;
        private String description;
    }
}

