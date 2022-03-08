package com.example.apitcc.repository;

import com.example.apitcc.models.Endereco;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface EnderecoRespository extends MongoRepository<Endereco, String>{
    Endereco findEnderecoById(String Id);
    
}
