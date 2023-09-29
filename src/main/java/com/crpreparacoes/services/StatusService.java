package com.crpreparacoes.services;

import com.crpreparacoes.models.Status;
import com.crpreparacoes.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> listAllStatus() {
        return statusRepository.listAllStatus();
    }
}
