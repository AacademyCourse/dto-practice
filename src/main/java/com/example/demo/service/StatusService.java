package com.example.demo.service;

import com.example.demo.entity.Status;

import java.util.Set;

public interface StatusService {
    Status addStatus(Status status);
    void deleteStatus(Long id);
    Status findStatusByName(String statusName);
    Status findStatusById(Long id);
    Set<Status> findAll();
}
