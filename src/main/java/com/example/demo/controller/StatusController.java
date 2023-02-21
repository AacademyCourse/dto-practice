package com.example.demo.controller;

import com.example.demo.converter.StatusConverter;
import com.example.demo.dto.StatusRequest;
import com.example.demo.dto.StatusResponse;
import com.example.demo.entity.Status;
import com.example.demo.service.impl.StatusServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping (path = "/status")
public class StatusController {
    @Autowired
    private StatusServiceImpl statusService;
    @Autowired
    private StatusConverter statusConverter;

    @GetMapping (path = "/all")
    public ResponseEntity<Set<StatusResponse>> getAll() {
        Set<StatusResponse> statusResp = new HashSet<>();
        Set<Status> statuses = statusService.findAll();
        statuses.forEach(
                status -> statusResp.add(statusConverter.toStatusResponse(status))
        );
        return ResponseEntity.status(HttpStatus.OK).body(statusResp);
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity<StatusResponse> getStatus(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(statusConverter.toStatusResponse(statusService.findStatusById(id)));
    }

    @PostMapping
    public ResponseEntity<StatusResponse> save(@RequestBody @Valid StatusRequest status) {
        Status saved = statusService.addStatus(
                statusConverter.convertToStatus(status));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(statusConverter.toStatusResponse(saved));
    }

    @DeleteMapping (path = "/{id}/delete")
    public ResponseEntity<String> deleteById (@PathVariable Long id) {
        statusService.deleteStatus(id);
        return ResponseEntity.status(HttpStatus.OK).body("Status deleted");
    }
}
