package edu.syslocacar.controller;

import edu.syslocacar.model.entity.Locacao;
import edu.syslocacar.model.entity.Veiculo;
import edu.syslocacar.model.services.FXMLPathProvider;
import edu.syslocacar.model.services.LocacaoServices;
import edu.syslocacar.model.services.VeiculoServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class LocacaoModalController implements Initializable {

    @FXML
    private DatePicker dtLocacao;
    @FXML
    private  DatePicker dtDevolucao;
    @FXML
    private TextField txtValorCaucao;
    @FXML
    private TextField txtValorTotal;
    @FXML
    private TextField txtStatus;
    @FXML
    private TableView<Veiculo> tbVeiculos;
    @FXML
    private TableColumn<Veiculo, String> columnId;
    @FXML
    private TableColumn<Veiculo, String> columnMarca;
    @FXML
    private TableColumn<Veiculo, String> columnModelo;
    @FXML
    private TableColumn<Veiculo, String> columnPlaca;
    @FXML
    private TableColumn<Veiculo, String> columnStatus;
    @FXML
    Button btnSalvar;
    @FXML
    Button btnExcluir;
    @FXML
    Button btnCancelar;

    private Locacao locacao;
    @Setter
    protected VeiculoServices veiculoServices;
    @Setter
    protected LocacaoServices locacaoServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarGUI();
        tbVeiculos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void iniciarGUI() {
        // Vincula as celulas de cada coluna com os campos da classe model
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        columnPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void updateTableView(){
        if (veiculoServices == null){
            throw  new IllegalStateException("Sercice VeiculoService não instanciado: nullpoint exception");
        }

        List<Veiculo> lista = veiculoServices.getAllVeiculos();
        ObservableList<Veiculo> observableList = FXCollections.observableList(lista);
        tbVeiculos.setItems(observableList);
    }

    @FXML
    public void btnSalvarOnAction() {
        try {
            if (this.locacao == null) {
                this.locacao = new Locacao();
                lerCampos();
                locacaoServices.addLocacao(locacao);
                fecharModal();
            } else {
                lerCampos();
                locacaoServices.updateLocacao(locacao);
                fecharModal();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void btnExcluirOnAction() {
        locacaoServices.removeLocacao(locacao.getId());
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

    private void preencherCampos() {
        if (locacao != null) {
            dtLocacao.setValue(getLocalDate(locacao.getDataLocacao()));
            dtDevolucao.setValue(getLocalDate(locacao.getDataDevolucao()));
            txtValorCaucao.setText(locacao.getValorCaucao().toString());
            txtValorTotal.setText(locacao.getValorTotal().toString());
            txtStatus.setText(locacao.getStatus());
            this.locacao.getVeiculos().stream().forEach(v -> {
               tbVeiculos.getSelectionModel().select(v);
            });
        }
    }

    private LocalDate getLocalDate(Date input) {
        return input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Date getDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
        if (locacao != null) {
            preencherCampos();
        }
    }

    private void lerCampos() {
        this.locacao.setDataLocacao(getDate(dtLocacao.getValue()));
        this.locacao.setDataDevolucao(getDate(dtDevolucao.getValue()));
        this.locacao.setValorCaucao(Float.parseFloat(txtValorCaucao.getText()));
        this.locacao.setValorTotal(Float.parseFloat(txtValorTotal.getText()));
        this.locacao.setStatus(txtStatus.getText());

        List<Veiculo> veiculosSelecionados = tbVeiculos.getSelectionModel().getSelectedItems();
        this.locacao.setVeiculos(veiculosSelecionados);
    }
}
