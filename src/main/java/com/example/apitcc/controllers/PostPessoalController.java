package com.example.apitcc.controllers;

import java.util.List;

import com.example.apitcc.models.PostAdocao;
import com.example.apitcc.models.PostPessoal;
import com.example.apitcc.models.Usuario;
import com.example.apitcc.repository.PostPessoalRepository;
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
public class PostPessoalController {
    private Logger logger = LoggerFactory.getLogger(PostPessoalController.class);   
    
    @Autowired
    private PostPessoalRepository postPessoalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/postpessoal")
    public List<PostPessoal> getAllPostPessoal(){
        logger.info("Getting all PessoalPost.");
        return postPessoalRepository.findAll();

    }

    @GetMapping(value = "/postapessoal/{postId}")
    public PostPessoal getPostAdocaoById(@PathVariable String postId) {
        logger.info("Getting PessoalPost with ID: {}", postId);
        return postPessoalRepository.findPostPessoalById(postId);
    }

    @PostMapping (value = "/postapessoal/create") 
    public PostPessoal addPostAdocao(@RequestBody PostPessoal postPessoal) { 
        logger.info ("Saving Pessoal Post.");
        logger.info(postPessoal.getId());
               
        //String nome = postadocao.getNome();

        //buscar Ousuario conforme ID e seta o Usuario do PostAdocao
        Usuario user = usuarioRepository.findUsuarioById(postPessoal.getUsuario().getId());
        postPessoal.setUsuario(user);

        return postPessoalRepository.save(postPessoal);
      
    }

    @PutMapping(value = "/postpessoalupdate/{postpessoalId}")
    public PostPessoal updatePostAdocao(@PathVariable String postadocaoId, @RequestBody PostPessoal postPessoal) {
        logger.info("Updating AdoptionPost with ID: {}", postadocaoId);
        postPessoal.getId();
        PostPessoal pa = postPessoalRepository.findPostPessoalById(postPessoal.getId());
        //return postAdocaoRepository.save(postAdocao);
        if (pa != null) {       

            //buscar Ousuario conforme ID e seta o Usuario do PostAdocao
            Usuario user = usuarioRepository.findUsuarioById(postPessoal.getUsuario().getId());
            postPessoal.setUsuario(user);    


            pa.setDescricao(postPessoal.getDescricao());
            pa.setFoto(postPessoal.getFoto());
            pa.setLikes(postPessoal.getLikes());
            pa.setUsuario(user);
           

            return postPessoalRepository.save(pa);   
        }else{
            throw new IllegalStateException("erro"+":"+"Ocorreu um erro inesperado");   
        }
        
    }

}
