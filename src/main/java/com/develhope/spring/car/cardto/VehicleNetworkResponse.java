package com.develhope.spring.car.cardto;

import com.fasterxml.jackson.core.JsonFactory;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class VehicleNetworkResponse {



    @Data
    @Builder
    public static class Success extends VehicleNetworkResponse {
        VehicleResponse vehicleResponse;
    }

    @Data
    @Builder
    public static class SuccessList extends VehicleNetworkResponse {
        List<VehicleResponse> vehicleResponse;
    }



    @Data
    @Builder
    public static class Error extends VehicleNetworkResponse{
        private int code;
        private String description;
    }
}