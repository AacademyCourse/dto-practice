package com.example.demo.service.impl;

import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        Status newStatus = new Status();
        newStatus.setName(status.getName());
        return statusRepository.save(status);
    }

    @Override
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public Status findStatusByName(String statusName) {
        return statusRepository.findStatusByName(statusName);
    }

    @Override
    public Status findStatusById(Long id) {
        Status searchedStatus = new Status();
        if (statusRepository.findById(id).isPresent()) {
            searchedStatus = statusRepository.findById(id).get();
        }
        return searchedStatus;
    }

    @Override
    public Set<Status> findAll() {
        return new HashSet<>(statusRepository.findAll());
    }
}
