package com.example.apitcc.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "raca")
public class Raca {
    @Id
    private String id;
    private String raca;

    public Raca(){

    }

    public String getRaca() {
        return this.raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
