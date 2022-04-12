package com.example.apitcc.repository;

import java.util.List;
import com.example.apitcc.models.ComentariosPessoal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ComentariosPessoalRepository extends MongoRepository<ComentariosPessoal, String> {
    @Query("{'postPessoal.id': ?0}")
    List<ComentariosPessoal> findComentarioPostPessoalById(String nome);
    
    
}
