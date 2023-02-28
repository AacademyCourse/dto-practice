package com.example.demo.service.impl;
import com.example.demo.entity.Status;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {
    @Autowired
    private final StatusRepository statusRepository;

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
    public Status findById(Long id) {
        return statusRepository.findById(id)
             .orElseThrow(() -> new RecordNotFoundException(String.format("Status with id %s not found", id)));
    }

    @Override
    public Status findByName(String status){
        return statusRepository.findByStatusName(status)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Status %s not found", status)));
    }

    @Override
    public Set<Status> findAll() {
        return new HashSet<>(statusRepository.findAll());
    }


}
