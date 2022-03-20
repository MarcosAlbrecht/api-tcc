package com.example.apitcc.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comentarioPostAdocao")
public class ComentariosAdocao {
    @Id
    private String id;
    private String comentario;
    private LocalDateTime dataComentario = LocalDateTime.now();
    @DBRef
    private Usuario usuario;
    @DBRef
    private PostAdocao postAdocao;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataComentario() {
        return this.dataComentario;
    }

    public void setDataComentario(LocalDateTime dataComentario) {
        this.dataComentario = dataComentario;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PostAdocao getPostAdocao() {
        return this.postAdocao;
    }

    public void setPostAdocao(PostAdocao postAdocao) {
        this.postAdocao = postAdocao;
    }
    
}
