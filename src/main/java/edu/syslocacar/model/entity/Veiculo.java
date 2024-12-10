package edu.syslocacar.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String marca;
    @Setter
    private String modelo;
    @Setter
    private String placa;
    @Setter
    private String status;
//    @Getter
//    @Setter
//    @ManyToOne
//    @JoinColumn(name = "locacao_id")
//    private Locacao locacao;

    // CONSTRUTOR
    public Veiculo() {
    }

    public Veiculo( String marca, String modelo, String placa, String status) {

        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.status = status;
    }
    // GET E SET

    public Long getId() {
        return id;
    }

     public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getStatus() {
        return status;
    }

}
