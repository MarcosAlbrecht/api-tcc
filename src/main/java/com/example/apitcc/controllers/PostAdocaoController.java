package com.example.apitcc.controllers;
import java.util.List;

import com.example.apitcc.models.PostAdocao;
import com.example.apitcc.models.Raca;
import com.example.apitcc.models.Usuario;
import com.example.apitcc.repository.PostAdocaoRepository;
import com.example.apitcc.repository.RacaRepository;
import com.example.apitcc.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMongoRepositories
public class PostAdocaoController {
    private Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    
    @Autowired
    private PostAdocaoRepository postAdocaoRepository;

    @Autowired
    private RacaRepository racaRespository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/postadocao")
    public List<PostAdocao> getAllPostAdocao(){
        logger.info("Getting all AdoptionPost.");
        return postAdocaoRepository.findAll();


        // List<PostAdocao> l = repository.findAll();
        
        // if (l.isEmpty())
        //     return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        
        // return new ResponseEntity<>(repository.findAll(), HttpStatus.FOUND);
    }
    @GetMapping(value = "/postadocaoid/{postId}")
    public List<PostAdocao> getPostAdocaoById(@PathVariable String postId) {
        logger.info("Getting AdoptionPost with ID: {}", postId);
        return postAdocaoRepository.findPostAdocaoById(postId);
    }

    @PutMapping(value = "/postadocaoupdate/{postadocaoId}")
    public PostAdocao updatePostAdocao(@PathVariable String postadocaoId, @RequestBody PostAdocao postAdocao) {
        logger.info("Updating AdoptionPost with ID: {}", postadocaoId);
        return postAdocaoRepository.save(postAdocao);
    }

    @PostMapping (value = "/postadocao/create") 
    public PostAdocao addPostAdocao(@RequestBody PostAdocao postadocao) { 
        logger.info ("Saving Adoption Post.");
        logger.info(postadocao.getDescricao());
        
        postadocao.getRaca();
        String nome = postadocao.getNome();
        //buscar a Raça conforme ID e seta a Raca do PostAdocao
        Raca raca = racaRespository.findRacaById(postadocao.getRaca().getId());
        postadocao.setRaca(raca);

        //buscar Ousuario conforme ID e seta o Usuario do PostAdocao
        Usuario user = usuarioRepository.findUsuarioById(postadocao.getUsuario().getId());
        postadocao.setUsuario(user);

        return postAdocaoRepository.save(postadocao);
        // String teste;
        // teste = user.getChefe().getId();
        // Estado estado = estadoRepository.findEstadoById(user.getEstado().getId());
        // user.setEstado(estado);
        // return usuarioRepository.save(user); 
        //return usuarioRepository.createUser(user);
    }
}
