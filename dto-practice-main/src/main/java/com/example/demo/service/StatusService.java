package com.example.demo.service;

import com.example.demo.entity.Status;

import java.util.Set;

public interface StatusService {
    Status addStatus (Status status);
    void deleteStatus (Long id);
    Status findById(Long id);
    Set<Status> findAll();
}
