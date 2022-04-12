package com.example.apitcc.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post_adocao")
public class PostAdocao {
    @Id
    private String id;
    private String nome;   
    private String descricao;
    private Integer idade_pet;
    private String porte;
    private String pelagem;
    private String latitude;
    private String longitude;
    private LocalDateTime data_criacao = LocalDateTime.now();
    private String ativo;
    private String foto_dog;
    private List<String> fotos;
    private Usuario usuario;
    private Raca raca;


    public String getFoto_dog() {
        return this.foto_dog;
    }

    public void setFoto_dog(String foto_dog) {
        this.foto_dog = foto_dog;
    }
    

    public Raca getRaca() {
        return this.raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Integer getIdade_pet() {
        return this.idade_pet;
    }

    public void setIdade_pet(Integer idade_pet) {
        this.idade_pet = idade_pet;
    }

    public String getPorte() {
        return this.porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getPelagem() {
        return this.pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getData_criacao() {
        return this.data_criacao;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getAtivo() {
        return this.ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public List<String> getFotos() {
        return this.fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    

        
  
}
