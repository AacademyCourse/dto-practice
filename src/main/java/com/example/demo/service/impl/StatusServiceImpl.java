package com.example.demo.service.impl;

import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.StatusService;
import lombok.AllArgsConstructor;
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
    public StatusServiceImpl(StatusRepository statusRepository) {
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
        return statusRepository.findById(id).orElseThrow();
    }

    @Override
    public Set<Status> findAll() {
        return new HashSet<>(statusRepository.findAll());
    }

    @Override
    public Optional<Status> findByName(String status) {
        return statusRepository.findByStatusName(status);
    }
}
