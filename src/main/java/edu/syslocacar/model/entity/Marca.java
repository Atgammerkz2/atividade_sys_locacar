package edu.syslocacar.model.entity;

import java.io.Serializable;
import java.util.List;

public class Marca implements Serializable {
    private Integer id;
    private String nome;
    private List<Modelo> listModelos;

    // CONSTRUTOR

    public Marca() {
    }

    public Marca(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    // GET E SET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
