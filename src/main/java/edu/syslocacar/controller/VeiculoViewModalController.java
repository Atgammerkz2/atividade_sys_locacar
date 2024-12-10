package edu.syslocacar.controller;

import edu.syslocacar.model.entity.Veiculo;
import edu.syslocacar.model.services.VeiculoServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class VeiculoViewModalController extends VeiculoController {
    @FXML
    TextField txtMarca;
    @FXML
    TextField txtModelo;
    @FXML
    TextField txtPlaca;
    @FXML
    TextField txtStatus;
    @FXML
    Button btnSalvar;
    @FXML
    Button btnExcluir;
    @FXML
    Button btnCancelar;

    @Setter
    private VeiculoServices veiculoServices;

    private Veiculo veiculo;
    //*****************************************************************************************************************
    // INICIALIZAÇÃO
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    //*****************************************************************************************************************
    // TRATAMENTO DE EVENTOS
    @FXML
    public void btnSalvarOnAction() {
        try {
            if (veiculo == null) {
                this.veiculo = new Veiculo();
                lerCampos();
                veiculoServices.addVeiculo(veiculo);
                fecharModal();
            } else {
                lerCampos();
                veiculoServices.updateVeiculo(veiculo);
                fecharModal();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void btnExcluirOnAction() {
        lerCampos();
        veiculoServices.removeVeiculo(veiculo.getId());
        fecharModal();
    }

    @FXML
    public void btnCancelarOnAction() {
        fecharModal();
    }

    //*****************************************************************************************************************
    // METODOS COMPLEMENTARES
    public void fecharModal() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        if (veiculo != null) {
            preencherCampos();
        }
    }

    private void preencherCampos() {
        if (veiculo != null) {
            txtMarca.setText(veiculo.getMarca());
            txtModelo.setText(veiculo.getModelo());
            txtPlaca.setText(veiculo.getPlaca());
            txtStatus.setText(veiculo.getStatus());
        }
    }

    private void lerCampos() {
        this.veiculo.setMarca(txtMarca.getText());
        this.veiculo.setModelo(txtModelo.getText());
        this.veiculo.setPlaca(txtPlaca.getText());
        this.veiculo.setStatus(txtStatus.getText());
    }

}

