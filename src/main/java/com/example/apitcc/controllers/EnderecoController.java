package com.example.apitcc.controllers;

import java.util.List;

import com.example.apitcc.models.Endereco;
import com.example.apitcc.repository.EnderecoRespository;

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
public class EnderecoController {
    private Logger logger = LoggerFactory.getLogger(Endereco.class);
    
    @Autowired
    private EnderecoRespository enderecoRepository;

    @GetMapping(value = "/enderecos")
    public List<Endereco> getAllEnderecos(){
        logger.info("Getting all enderecos.");
        return enderecoRepository.findAll();
    }

    @GetMapping(value = "/enderecoid/{enderecoId}")
    public Endereco getEnderecoById(@PathVariable String enderecoId) {
        logger.info("Getting endereco with ID: {}", enderecoId);
        return enderecoRepository.findEnderecoById(enderecoId);
    }

    @PostMapping (value = "/endereco/create") 
    public Endereco addEndereco(@RequestBody Endereco endereco) { 
        logger.info ("Saving endereco.");      

        return enderecoRepository.save(endereco); 
        //return usuarioRepository.createUser(user);
    }

    @PutMapping(value = "/enderecoupdate")
    public Endereco updatePostAdocao(@RequestBody Endereco endereco) {
        logger.info("Updating endereco with ID: {}");
        endereco.getBairro();
        Endereco cp = enderecoRepository.findEnderecoById(endereco.getId());
        //return postAdocaoRepository.save(postAdocao);
        if (cp != null) {          

            cp.setComplemento(endereco.getComplemento());
            cp.setBairro(endereco.getBairro());
            cp.setLocalidade(endereco.getLocalidade());
            cp.setLogradouro(endereco.getLogradouro());
            cp.setNumero(endereco.getNumero());
            cp.setUf(endereco.getUf());         

            return enderecoRepository.save(cp);   
        }else{
            throw new IllegalStateException("erro"+":"+"Ocorreu um erro inesperado");   
        }
        
    }
}
