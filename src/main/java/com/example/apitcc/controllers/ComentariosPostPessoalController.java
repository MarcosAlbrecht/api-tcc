package com.example.apitcc.controllers;

import java.util.List;

import com.example.apitcc.models.ComentariosAdocao;
import com.example.apitcc.models.ComentariosPessoal;
import com.example.apitcc.models.PostPessoal;
import com.example.apitcc.models.Usuario;
import com.example.apitcc.repository.ComentariosPessoalRepository;
import com.example.apitcc.repository.PostAdocaoRepository;
import com.example.apitcc.repository.PostPessoalRepository;
import com.example.apitcc.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMongoRepositories
public class ComentariosPostPessoalController {
    private Logger logger = LoggerFactory.getLogger(ComentariosPostPessoalController.class);
    
    @Autowired
    private ComentariosPessoalRepository comentariosPessoalRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostPessoalRepository postPessoalRepository;

    @GetMapping(value = "/comentariosPessoal")
    public List<ComentariosPessoal> getAllCommentPessoal(){
        logger.info("Getting all CommentsAdoptionPost.");
        return comentariosPessoalRepository.findAll();

    }

    @GetMapping(value = "/comentariosPessoal/{idPostPessoal}")
    public List<ComentariosPessoal> getAllPostAdocao(@PathVariable String idPostPessoal){
        logger.info("Getting coments by id postAdocao. IDPostAdocao = "+idPostPessoal);
        return comentariosPessoalRepository.findComentarioPostPessoalById(idPostPessoal);

    }

    @PostMapping (value = "/comentariosPessoal/create") 
    public ComentariosPessoal addComentarioAdocao(@RequestBody ComentariosPessoal comentarioPessoal) { 
        logger.info ("Saving user.");
        // String teste;
        // teste = user.getChefe().getId();
        //Estado estado = estadoRepository.findEstadoById(user.getEstado().getId());
        //user.setEstado(estado);
        //user.setPassword(encoder.encode(user.getPassword()));

        //Endereco endereco = enderecoRepository.findEnderecoById(user.getEndereco().getId());
        //user.setEndereco(endereco);
        Usuario user = usuarioRepository.findUsuarioById(comentarioPessoal.getUsuario().getId());
        comentarioPessoal.setUsuario(user);

        PostPessoal postPessoal = postPessoalRepository.findPostPessoalById(comentarioPessoal.getPostPessoal().getId());
        comentarioPessoal.setPostPessoal(postPessoal);

        return comentariosPessoalRepository.save(comentarioPessoal); 
        //return usuarioRepository.createUser(user);
    }

    @PutMapping(value = "/comentariopessoalupdate")
    public ComentariosPessoal updatePostAdocao(@RequestBody ComentariosPessoal comentarioPessoal) {
        logger.info("Updating AdoptionPost with ID: {}");
        comentarioPessoal.getId();
        ComentariosPessoal cp = comentariosPessoalRepository.findComentarioPessoalById(comentarioPessoal.getId());
        //return postAdocaoRepository.save(postAdocao);
        if (cp != null) {          

            //buscar Ousuario conforme ID e seta o Usuario do PostAdocao

            cp.setComentario(comentarioPessoal.getComentario());
            

            return comentariosPessoalRepository.save(cp);   
        }else{
            throw new IllegalStateException("erro"+":"+"Ocorreu um erro inesperado");   
        }
        
    }

    @DeleteMapping("/deleteComentarioPessoal/{empId}")
    public ComentariosPessoal deleteComentarioPessoal(@PathVariable String empId) {        
        ComentariosPessoal cp = comentariosPessoalRepository.findComentarioPessoalById(empId);

        if (cp != null){
            comentariosPessoalRepository.deletePostPessoalById(empId);
        }else{
            throw new IllegalStateException("erro"+":"+"Ocorreu um erro inesperado");   
        }
        return cp;
        
        
    }

}
