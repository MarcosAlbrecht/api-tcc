package com.example.apitcc.controllers;

import java.util.List;

import com.example.apitcc.models.Raca;
import com.example.apitcc.repository.RacaRepository;

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
public class RacaController {

    private Logger logger = LoggerFactory.getLogger(RacaController.class);

    @Autowired
    private RacaRepository racaRepository;

    @GetMapping(value = "/racas")
    public List<Raca> getAllRacas(){
        logger.info("Getting all raças.");
        return racaRepository.findAll();
    }

    @GetMapping(value = "/racasid/{racasId}")
    public Raca getRacaById(@PathVariable String racasId) {
        logger.info("Getting raças with ID: {}", racasId);
        return racaRepository.findRacaById(racasId);
    }

    @GetMapping(value = "/racasname/{racasname}")
    public List<Raca> getRacaByRaca(@PathVariable String racasname) {
        logger.info("Getting racas with Name: {}", racasname);
        return racaRepository.findRacaByRaca(racasname);
    } 

    @PostMapping (value = "/racas/create") 
    public Raca addPostAdocao(@RequestBody Raca raca) { 
        logger.info ("Saving Adoption Post.");
        logger.info(raca.getRaca());
        
        //buscar a Raça conforme ID e seta a Raca do PostAdocao
        
        return racaRepository.save(raca);
        // String teste;
        // teste = user.getChefe().getId();
        // Estado estado = estadoRepository.findEstadoById(user.getEstado().getId());
        // user.setEstado(estado);
        // return usuarioRepository.save(user); 
        //return usuarioRepository.createUser(user);
    }
    
}
