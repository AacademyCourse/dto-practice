package com.example.demo.service.impl;

import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private final StatusRepository statusRepository;

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
        Optional<Status> status = statusRepository.findById(id);
        if(status.isPresent()){
            return status.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Set<Status> findAll() {
        return new HashSet<>(statusRepository.findAll());
    }
}
