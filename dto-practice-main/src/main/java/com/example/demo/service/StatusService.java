package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;

import java.util.Optional;
import java.util.Set;

public interface StatusService {
    Status addStatus (Status status);
    void deleteStatus (Long id);
    Status findById(Long id);
    Set<Status> findAll();
    Status findByStatusName(String status) throws RecordNotFoundException;
}
