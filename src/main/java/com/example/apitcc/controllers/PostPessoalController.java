package com.example.apitcc.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.apitcc.models.ComentariosPessoal;
import com.example.apitcc.models.PostAdocao;
import com.example.apitcc.models.PostPessoal;
import com.example.apitcc.models.Usuario;
import com.example.apitcc.repository.ComentariosPessoalRepository;
import com.example.apitcc.repository.PostPessoalRepository;
import com.example.apitcc.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @Autowired
    private ComentariosPessoalRepository comentariosPessoalRepository;

    @GetMapping(value = "/postpessoal")
    public List<PostPessoal> getAllPostPessoal() {
        logger.info("Getting all PessoalPost.");
        List<PostPessoal> pp = postPessoalRepository.findAll();

        List<ComentariosPessoal> cp;

        for (PostPessoal obj : pp) {
            logger.info (obj.getId());
            cp = comentariosPessoalRepository.findComentarioPostPessoalById(obj.getId());
            logger.info (Integer.toString( cp.size()));
            if (cp.size() > 0){                
                obj.setComentariosPessoal(cp.size());  
            } 
            
        }
        return pp;
            //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
        
        //return postPessoalRepository.findAll();

    }

    @GetMapping(value = "/postpessoal/{postId}")
    public PostPessoal getPostAdocaoById(@PathVariable String postId) {
        logger.info("Getting PessoalPost with ID: {}", postId);
        return postPessoalRepository.findPostPessoalById(postId);
    }

    @PostMapping (value = "/postpessoal/create") 
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
    public PostPessoal updatePostAdocao(@PathVariable String postpessoalId, @RequestBody PostPessoal postPessoal) {
        logger.info("Updating AdoptionPost with ID: {}", postpessoalId);
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

    @PutMapping(value = "/postpessoalupdate/likeadd/{postpessoalId}/{iduser}")
    public PostPessoal updatePostAdocaoLikeAdd(@PathVariable String postpessoalId, @PathVariable String iduser) {
        logger.info("Updating adocao like with ID: {}", postpessoalId);
        
        PostPessoal pa = postPessoalRepository.findPostPessoalById(postpessoalId);
        //return postAdocaoRepository.save(postAdocao);
        if (pa != null) {       

            //buscar Ousuario conforme ID e seta o Usuario do PostAdocao  
            List<String> listaLikes = new ArrayList<String>();
            
            if (pa.getLikes() != null) {
                listaLikes = pa.getLikes();    
            }
            

            listaLikes.add(iduser);

            pa.setLikes(listaLikes);
            pa.setLiked(true);

            return postPessoalRepository.save(pa);   
        }else{
            throw new IllegalStateException("erro"+":"+"Ocorreu um erro inesperado");   
        }
        
    }

    @PutMapping(value = "/postpessoalupdate/likeremove/{postpessoalId}/{iduser}")
    public PostPessoal updatePostAdocaoLikeRemove(@PathVariable String postpessoalId, @PathVariable String iduser) {
        logger.info("Updating adocao like with ID: {}", postpessoalId);
        
        PostPessoal pa = postPessoalRepository.findPostPessoalById(postpessoalId);
        //return postAdocaoRepository.save(postAdocao);
        if (pa != null) {       

            //buscar Ousuario conforme ID e seta o Usuario do PostAdocao  
            List<String> listaLikes = pa.getLikes();

            for (String id : listaLikes) {
                if (id.equals(iduser)) {
                    listaLikes.remove(id);
                    break;
                }
            }

            pa.setLiked(false);

            pa.setLikes(listaLikes);
           

            return postPessoalRepository.save(pa);   
        }else{
            throw new IllegalStateException("erro"+":"+"Ocorreu um erro inesperado");   
        }
        
    }

    @DeleteMapping("/deletePostPessoal/{postpessoalId}")
    public PostPessoal deleteComentarioPessoal(@PathVariable String postpessoalId) {        
        PostPessoal cp = postPessoalRepository.findPostPessoalById(postpessoalId);

        if (cp != null){

            List<ComentariosPessoal> cpr = comentariosPessoalRepository.findComentarioPostPessoalById(postpessoalId);

            
            if(cpr != null){
                for (ComentariosPessoal obj : cpr) {
                    logger.info (obj.getId());
                    comentariosPessoalRepository.deletePostPessoalById(obj.getId());                             
                }

            }

            postPessoalRepository.deletePostPessoalById(postpessoalId);
        }else{
            throw new IllegalStateException("erro"+":"+"Ocorreu um erro inesperado");   
        }
        return cp;
        
        
    }

    

}
