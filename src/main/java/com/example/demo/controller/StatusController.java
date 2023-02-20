package com.example.demo.controller;

import com.example.demo.entity.Status;
import com.example.demo.service.StatusService;
import com.example.demo.service.impl.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/status")
public class StatusController {
    @Autowired
    StatusServiceImpl statusService;

    @GetMapping(path = "/{id}")
    Status getStatus(@PathVariable Long id){
        return statusService.findById(id);
    }

    @PostMapping
    Status create(@RequestBody Status status){
        return  statusService.addStatus(status);
    }

    @GetMapping
    Set<Status> getAll(){
        return statusService.findAll();
    }
    @DeleteMapping(path = "/{id}")
    String delete(@PathVariable Long id){
        statusService.deleteStatus(id);
        return "Deleted";
    }
}
