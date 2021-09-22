package com.example.apitcc.models;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
public class Usuario {
 
    @Id
    private String id;
    private String nome;
    private int idade;
    private String telefone;
    private int ddd;
    private String cep;
    private String cidade;
    private String estado;
    private String apelido;
    private String cpf;
    private String foto;
    private Date data_cadastro;

    

    public String get_id() {
        return this.id;
    }

    public void set_id(String _id) {
        this.id = _id;
    }

    public Date getData_cadastro() {
        return this.data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getDdd() {
        return this.ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getApelido() {
        return this.apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Usuario(){
        
    }

    

}
