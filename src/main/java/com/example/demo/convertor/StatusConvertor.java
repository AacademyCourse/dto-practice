package com.example.demo.convertor;

import com.example.demo.dto.StatusRequest;
import com.example.demo.dto.StatusResponse;
import com.example.demo.entity.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusConvertor {

    public Status convertToStatus(StatusRequest statusRequest){
        return Status.builder()
                .statusName(statusRequest.getStatusName())
                .build();
    }

    public StatusResponse convertToStatusResponse(Status status){
        return StatusResponse.builder()
                .id(status.getId())
                .statusName(status.getStatusName())
                .build();
    }
}
