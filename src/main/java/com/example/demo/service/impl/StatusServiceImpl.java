package com.example.demo.service.impl;
import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.HashSet;

import java.util.Set;

@Service
@Qualifier("statusServiceImpl")
public class StatusServiceImpl implements StatusService {
    @Autowired
    private final StatusRepository statusRepository;
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status addStatus(Status status){
        //the method throws SQLIntegrityConstraintViolationException, which is handled in ApplicationExceptionHandler.
        //not needed try-catch or any throw here: the get request works without them and response with the message
        return statusRepository.save(status);
    }

    @Override
    public void deleteStatus(Long id) {
        //the method throws EmptyResultDataAccessException, which is handled in ApplicationExceptionHandler.
        //not needed try-catch or any throw here: the delete request works without them and  response with the message
            statusRepository.deleteById(id);
    }

    @Override
    public Status findById(Long id) throws RecordNotFoundException {
        return statusRepository.findById(id)
             .orElseThrow(() -> new RecordNotFoundException(String.format("Status with id %s not found", id)));

    }

    @Override
    public Status findByName(String status) throws RecordNotFoundException {
        return statusRepository.findByName(status)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Status %s not found", status)));
    }

    @Override
    public Set<Status> findAll() {
        return new HashSet<>(statusRepository.findAll());
    }

}
