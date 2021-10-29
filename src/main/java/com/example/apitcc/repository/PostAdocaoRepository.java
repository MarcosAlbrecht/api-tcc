package com.example.apitcc.repository;

import java.util.List;

import com.example.apitcc.models.PostAdocao;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostAdocaoRepository extends MongoRepository<PostAdocao, String> {
    List<PostAdocao> findPostAdocaoById(String id);    
}
