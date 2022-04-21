package com.example.apitcc.controllers;

import java.util.List;
import java.util.Optional;

import com.example.apitcc.models.ComentariosAdocao;
import com.example.apitcc.models.PostAdocao;
import com.example.apitcc.models.Usuario;
import com.example.apitcc.repository.ComentariosAdocaoRepository;
import com.example.apitcc.repository.PostAdocaoRepository;
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
public class ComentariosPostAdocaoController {
    private Logger logger = LoggerFactory.getLogger(ComentariosPostAdocaoController.class);  
    
    @Autowired
    private ComentariosAdocaoRepository comentariosAdocaoRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostAdocaoRepository postAdocaoRepository;

    @GetMapping(value = "/comentariosAdocao")
    public List<ComentariosAdocao> getAllPostAdocao(){
        logger.info("Getting all CommentsAdoptionPost.");
        return comentariosAdocaoRepository.findAll();

    }

    @GetMapping(value = "/comentariosAdocao/{idPostAdocao}")
    public List<ComentariosAdocao> getAllPostAdocao(@PathVariable String idPostAdocao){
        logger.info("Getting coments by id postAdocao. IDPostAdocao = "+idPostAdocao);
        return comentariosAdocaoRepository.findComentarioByIdPostAdocao(idPostAdocao);

    }

    @PostMapping (value = "/comentariosAdocao/create") 
    public ComentariosAdocao addComentarioAdocao(@RequestBody ComentariosAdocao comentarioAdocao) { 
        logger.info ("Saving user.");
        // String teste;
        // teste = user.getChefe().getId();
        //Estado estado = estadoRepository.findEstadoById(user.getEstado().getId());
        //user.setEstado(estado);
        //user.setPassword(encoder.encode(user.getPassword()));

        //Endereco endereco = enderecoRepository.findEnderecoById(user.getEndereco().getId());
        //user.setEndereco(endereco);
        Usuario user = usuarioRepository.findUsuarioById(comentarioAdocao.getUsuario().getId());
        comentarioAdocao.setUsuario(user);

        PostAdocao postAdocao = (PostAdocao) postAdocaoRepository.findPostAdocaoById(comentarioAdocao.getPostAdocao().getId());
        comentarioAdocao.setPostAdocao(postAdocao);

        return comentariosAdocaoRepository.save(comentarioAdocao); 
        //return usuarioRepository.createUser(user);
    }

    @PutMapping(value = "/comentarioadocaoupdate")
    public ComentariosAdocao updatePostAdocao(@RequestBody ComentariosAdocao comentarioAdocao) {
        logger.info("Updating AdoptionPost with ID: {}");
        comentarioAdocao.getId();
        ComentariosAdocao cp = comentariosAdocaoRepository.findComentarioAdocaoById(comentarioAdocao.getId());
        //return postAdocaoRepository.save(postAdocao);
        if (cp != null) {          

            //buscar Ousuario conforme ID e seta o Usuario do PostAdocao

            cp.setComentario(comentarioAdocao.getComentario());
            

            return comentariosAdocaoRepository.save(cp);   
        }else{
            throw new IllegalStateException("erro"+":"+"Ocorreu um erro inesperado");   
        }
        
    }

    @DeleteMapping("/deleteComentarioAdocao/{empId}")
    public ComentariosAdocao deleteComentarioPessoal(@PathVariable String empId) {        
        ComentariosAdocao cp = comentariosAdocaoRepository.findComentarioAdocaoById(empId);

        if (cp != null){
            comentariosAdocaoRepository.deleteComentPostAdocaoById(empId);
        }else{
            throw new IllegalStateException("erro"+":"+"Ocorreu um erro inesperado");   
        }
        return cp;
        
        
    }

    
}
