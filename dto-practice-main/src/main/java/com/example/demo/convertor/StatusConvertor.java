package com.example.demo.convertor;

import com.example.demo.dto.StatusRequest;
import com.example.demo.dto.StatusResponse;
import com.example.demo.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class StatusConvertor {

    public Status convertToStatus (StatusRequest statusRequest){
        return Status.builder()
                .statusName(statusRequest.getStatusName())
                .build();
    }

    public StatusResponse toStatusResponse(Status status){
        return StatusResponse.builder()
                .id(status.getId())
                .statusName(status.getStatusName())
                .build();
    }

}
