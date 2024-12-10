package edu.syslocacar.controller;

import edu.syslocacar.model.entity.Locacao;
import edu.syslocacar.model.services.LocacaoServices;
import edu.syslocacar.model.services.VeiculoServices;
import javafx.fxml.Initializable;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class LocacaoModalController implements Initializable {

    @Setter
    private Locacao locacao;
    @Setter
    protected VeiculoServices veiculoServices;
    @Setter
    protected LocacaoServices locacaoServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
