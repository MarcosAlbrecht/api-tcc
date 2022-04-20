package com.example.apitcc.repository;

import java.util.List;

import com.example.apitcc.models.ComentariosAdocao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface ComentariosAdocaoRepository extends MongoRepository<ComentariosAdocao, String> {
    @Query("{'postAdocao.id': ?0}")
    List<ComentariosAdocao> findComentarioByIdPostAdocao(String nome);

    ComentariosAdocao findComentarioAdocaoById(String id);

    @Query(value = "{'id' : ?0}", delete = true)
    void deleteComentPostAdocaoById(String id);
    
    
}
