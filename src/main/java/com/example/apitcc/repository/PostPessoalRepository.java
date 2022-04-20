package com.example.apitcc.repository;

import java.util.List;

import com.example.apitcc.models.PostPessoal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PostPessoalRepository extends MongoRepository<PostPessoal, String> {
    PostPessoal findPostPessoalById(String id);
    
    @Query(value = "{'id' : ?0}", delete = true)
    void deletePostPessoalById(String id);
}
