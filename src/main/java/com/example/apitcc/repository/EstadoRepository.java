package com.example.apitcc.repository;

import com.example.apitcc.models.Estado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface EstadoRepository extends MongoRepository<Estado, String> {
    Estado findEstadoById(String Id);    
}
