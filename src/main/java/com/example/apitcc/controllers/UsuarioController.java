package com.example.apitcc.controllers;

import java.util.List;

import com.example.apitcc.models.Usuario;
import com.example.apitcc.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMongoRepositories
public class UsuarioController {

    private Logger logger = LoggerFactory.getLogger(UsuarioController.class);
 
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping(value = "/users")
    public List<Usuario> getAllUsers(){
        logger.info("Getting all users.");
        return usuarioRepository.findAll();
    }
    @GetMapping(value = "/{userId}")
    public Usuario getUserById(@PathVariable String userId) {
        logger.info("Getting users with ID: {}", userId);
        return usuarioRepository.findUsuarioById(userId);
    } 

    @PostMapping (value = "/create") 
    public Usuario addUsuario(@RequestBody Usuario user) { 
        logger.info ("Saving user."); 
        return usuarioRepository.save(user); 
    }
}
