package com.example.apitcc.repository;

import java.util.List;

import com.example.apitcc.models.Raca;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RacaRepository extends MongoRepository<Raca, String> {
    Raca findRacaById(String id);
    @Query("{'raca': {$regex: ?0 }})")
    List<Raca> findRacaByRaca(String raca);   
}
