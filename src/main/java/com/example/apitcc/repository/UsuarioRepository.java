package com.example.apitcc.repository;

import java.util.List;

import com.example.apitcc.models.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    List<Usuario> findUsuarioById(String Id);

    @Query("{'nome': {$regex: ?0 }})")
    List<Usuario> findUsuarioByNome(String nome);

    

    //Usuario createUser(Usuario user);
}
