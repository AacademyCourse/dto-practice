package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;
import java.util.Set;

public interface StatusService {
    Status addStatus (Status status) throws SQLIntegrityConstraintViolationException;
    void deleteStatus(Long id);
    Status findById(Long id) throws RecordNotFoundException;
    Status findByName(String status) throws RecordNotFoundException;
    Set<Status> findAll();

}

