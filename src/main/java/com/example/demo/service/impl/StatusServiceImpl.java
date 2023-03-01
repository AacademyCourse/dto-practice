package com.example.demo.service.impl;


import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl (StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status addStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public Status findById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException(String.format("Status %s not found", id)));
    }

    @Override
    public Set<Status> findAll() {
        return new HashSet<>(statusRepository.findAll());
    }
    public Status findByName(String status) {
        return Optional.ofNullable(statusRepository.findByStatus(status))
                .orElseThrow(() -> new RecordNotFoundException(String.format("Status %s not found", status)));
    }
}
