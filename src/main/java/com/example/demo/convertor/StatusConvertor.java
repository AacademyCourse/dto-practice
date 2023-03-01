package com.example.demo.convertor;

import com.example.demo.dto.StatusRequest;
import com.example.demo.dto.StatusResponse;
import com.example.demo.entity.Status;
import org.springframework.stereotype.Component;


@Component
public class StatusConvertor {

    public Status convertStatus (StatusRequest statusRequest) {
        return Status.builder()
                .status(statusRequest.getStatus())
                .build();
    }
    public StatusResponse statusResponse (Status statusName) {
        return StatusResponse.builder()
                .status(statusName.getStatus())
                .build();
    }

}
