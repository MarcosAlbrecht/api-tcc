package com.example.apitcc.repository;

import com.example.apitcc.models.PostPessoal;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostPessoalRepository extends MongoRepository<PostPessoal, String> {
    PostPessoal findPostPessoalById(String id);   
}
