package edu.syslocacar.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dataLocacao;
    private Date dataDevolucao;
    private Float valorCaucao;
    private Float valorTotal;
    private String status;

//    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Veiculo> veiculos;
}
