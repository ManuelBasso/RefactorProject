package com.develhope.spring.vehicle.vehicledto;

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