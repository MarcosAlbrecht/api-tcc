package com.example.apitcc.controllers;

import com.example.apitcc.models.Estado;
import com.example.apitcc.repository.EstadoRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableMongoRepositories
public class EstadoController {
    private Logger logger = LoggerFactory.getLogger(Estado.class);
    
    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping(value = "/states")
    public List<Estado> getAllStates(){
        logger.info("Getting all users.");
        return estadoRepository.findAll();
    }

    @GetMapping(value = "/statesid/{statesId}")
    public Estado getEstateById(@PathVariable String estadoId) {
        logger.info("Getting users with ID: {}", estadoId);
        return estadoRepository.findEstadoById(estadoId);
    }
}
