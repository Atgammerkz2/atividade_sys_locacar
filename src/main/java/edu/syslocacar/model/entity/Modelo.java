package edu.syslocacar.model.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.List;

public class Modelo implements Serializable {
    private Integer id;
    private String nome;
    private Marca mara;
    private List<Veiculo> veiculoList;
    //CONSTRUTOR

    public Modelo() {
    }

    public Modelo(Integer id, String nome) {
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

    public Marca getMara() {
        return mara;
    }

    public void setMara(Marca mara) {
        this.mara = mara;
    }
}
