package com.example.apitcc.repository;

import com.example.apitcc.models.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findUsuarioById(String Id);
}
