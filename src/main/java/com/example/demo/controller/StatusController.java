package com.example.demo.controller;

import com.example.demo.convertor.StatusConvertor;
import com.example.demo.dto.CurrencyResponse;
import com.example.demo.dto.StatusRequest;
import com.example.demo.dto.StatusResponse;
import com.example.demo.entity.Currency;
import com.example.demo.entity.Status;
import com.example.demo.service.StatusService;
import com.example.demo.service.impl.StatusServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(path = "/status")
public class StatusController {
    @Autowired
    StatusServiceImpl statusService;
    @Autowired
    StatusConvertor statusConvertor;

    @PostMapping
    ResponseEntity<StatusResponse> save(@RequestBody @Valid StatusRequest statusRequest){
        Status savedStatus = statusService.addStatus(
                statusConvertor.convertToStatus(statusRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(statusConvertor.toStatusResponse(savedStatus));
    }

    @GetMapping(path = "/all")
    ResponseEntity<Set<StatusResponse>> getAll() {
        Set<StatusResponse> currResp = new HashSet<>();
        Set<Status> statuses = statusService.findAll();

        for (Status status : statuses) {
            currResp.add(statusConvertor.toStatusResponse(status));
        }
        return ResponseEntity.status(HttpStatus.OK).body(currResp);
    }
    @GetMapping(path="/{id}")
    ResponseEntity<StatusResponse> getStatus(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(statusConvertor.toStatusResponse(statusService.findById(id)));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        statusService.deleteStatus(id);
        return ResponseEntity.status(HttpStatus.OK).body("Status is deleted");
    }
}
