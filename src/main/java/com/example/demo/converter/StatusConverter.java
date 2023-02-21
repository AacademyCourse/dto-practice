package com.example.demo.converter;

import com.example.demo.dto.StatusRequest;
import com.example.demo.dto.StatusResponse;
import com.example.demo.entity.Status;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class StatusConverter {
    public Status convertToStatus(StatusRequest statusRequest) {
        return Status.builder()
                .name(statusRequest.getStatusName())
                .build();
    }

    public StatusResponse toStatusResponse(Status status) {
        return StatusResponse.builder()
                .statusName(status.getName())
                .build();
    }
}
