package com.example.demo.service;

import com.example.demo.entity.Status;

import java.util.Optional;
import java.util.Set;

public interface StatusService {
    Status addStatus(Status status);
    void deleteStatus(Long id);
    Optional<Status> findStatusByName(String statusName);
    Status findStatusById(Long id);
    Set<Status> findAll();
}
