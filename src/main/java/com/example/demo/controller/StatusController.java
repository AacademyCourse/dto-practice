package com.example.demo.controller;

import com.example.demo.convertor.StatusConvertor;
import com.example.demo.dto.StatusRequest;
import com.example.demo.dto.StatusResponse;
import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.service.impl.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/status")
public class StatusController {

    @Autowired
    StatusServiceImpl statusServiceImpl;
    @Autowired
    StatusConvertor statusConvertor;

    @PostMapping
    ResponseEntity<StatusResponse> save (@RequestBody StatusRequest statusRequest) throws SQLIntegrityConstraintViolationException {
        Status status = statusConvertor.convertToStatus(statusRequest);
        Status savedStatus = statusServiceImpl.addStatus(status);
        StatusResponse statusResponse = statusConvertor.convertToStatusResponse(savedStatus);
        return ResponseEntity
                .ok()
                .body(statusResponse);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteById(@PathVariable Long id){
        statusServiceImpl.deleteStatus(id);
        return ResponseEntity
                .ok()
                .body(String.format("%d deleted", id));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<StatusResponse> getById(@PathVariable Long id) throws RecordNotFoundException {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(statusConvertor.convertToStatusResponse(statusServiceImpl.findById(id)));
    }

    @GetMapping(path = "/all")
    ResponseEntity<Set<StatusResponse>> getAll(){
        Set<StatusResponse> statusResponses = statusServiceImpl.findAll()
                .stream()
                .map(statusConvertor :: convertToStatusResponse)
                .collect(Collectors.toSet());
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(statusResponses);
    }

    @GetMapping(path = "/name/{statusName}")
    ResponseEntity<StatusResponse> findByName(@PathVariable String name) throws RecordNotFoundException {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(statusConvertor.convertToStatusResponse(statusServiceImpl.findByName(name)));
    }

}
