package com.example.apitcc.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post_pessoal")
public class PostPessoal {
    @Id
    private String id;   
    private String descricao;
    private String foto; 
    private LocalDateTime data_criacao = LocalDateTime.now();
    private List<String> likes;    
    private Usuario usuario; 
    private Integer comentariosPessoal;
    private boolean liked = false;

    public boolean isLiked() {
        return this.liked;
    }

    public boolean getLiked() {
        return this.liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public List<String> getLikes() {
        return this.likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public Integer getComentariosPessoal() {
        return this.comentariosPessoal;
    }

    public void setComentariosPessoal(Integer comentariosPessoal) {
        this.comentariosPessoal = comentariosPessoal;
    } 

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LocalDateTime getData_criacao() {
        return this.data_criacao;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }   
    
}
