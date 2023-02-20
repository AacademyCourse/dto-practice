package com.example.demo.controller;

import com.example.demo.convertor.StatusConvertor;
import com.example.demo.dto.StatusRequest;
import com.example.demo.dto.StatusResponse;
import com.example.demo.entity.Status;
import com.example.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    @Autowired
    StatusConvertor statusConvertor;

    @PostMapping
    ResponseEntity<StatusResponse> save (@RequestBody StatusRequest statusRequest){
        Status status = statusConvertor.convertToStatus(statusRequest);
        Status savedStatus = statusService.addStatus(status);
        StatusResponse statusResponse = statusConvertor.convertToStatusResponse(savedStatus);
        return ResponseEntity
                .ok()
                .body(statusResponse);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<StatusResponse> getById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(statusConvertor.convertToStatusResponse(statusService.findById(id)));
    }

    @GetMapping(path = "/all")
    ResponseEntity<Set<StatusResponse>> getAll(){
        Set<StatusResponse> statusResponses = statusService.findAll()
                .stream()
                .map(statusConvertor :: convertToStatusResponse)
                .collect(Collectors.toSet());
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(statusResponses);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteById(@PathVariable Long id){
        statusService.deleteStatus(id);
        return ResponseEntity
                .ok()
                .body(String.format("%d deleted", id));
    }
}
