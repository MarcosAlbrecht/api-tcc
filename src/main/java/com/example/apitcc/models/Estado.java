package com.example.apitcc.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "estado")
public class Estado {
    @Id
    private String id;
    private String dsc_estado;
    private String uf;   

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDsc_estado() {
        return this.dsc_estado;
    }

    public void setDsc_estado(String dsc_estado) {
        this.dsc_estado = dsc_estado;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
