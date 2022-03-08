package com.example.apitcc.controllers;

import java.util.List;

import com.example.apitcc.models.Endereco;
import com.example.apitcc.repository.EnderecoRespository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMongoRepositories
public class EnderecoController {
    private Logger logger = LoggerFactory.getLogger(Endereco.class);
    
    @Autowired
    private EnderecoRespository enderecoRepository;

    @GetMapping(value = "/enderecos")
    public List<Endereco> getAllEnderecos(){
        logger.info("Getting all enderecos.");
        return enderecoRepository.findAll();
    }

    @GetMapping(value = "/enderecoid/{enderecoId}")
    public Endereco getEnderecoById(@PathVariable String enderecoId) {
        logger.info("Getting users with ID: {}", enderecoId);
        return enderecoRepository.findEnderecoById(enderecoId);
    }
}
