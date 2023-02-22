package com.example.demo.service.impl;

import com.example.demo.dto.StatusResponse;
import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
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
    public Status addStatus(Status status) throws SQLIntegrityConstraintViolationException {
           return statusRepository.save(status);
    }


    @Override
    public void deleteStatus(Long id) throws RecordNotFoundException{
        statusRepository.deleteById(id);
    }

    @Override
    public Status findById(Long id) throws RecordNotFoundException {
        Optional<Status> status = statusRepository.findById(id);
        if(status.isPresent()){
            return status.get();
        } else {
            throw new RecordNotFoundException(String.format("Status with id %s not found", id));
        }
    }

    @Override
    public Set<Status> findAll() {
        return new HashSet<>(statusRepository.findAll());
    }
}
