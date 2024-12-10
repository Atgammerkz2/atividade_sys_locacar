package edu.syslocacar.controller;

import edu.syslocacar.MainApp;
import edu.syslocacar.model.entity.Locacao;
import edu.syslocacar.model.entity.Veiculo;
import edu.syslocacar.model.services.FXMLPathProvider;
import edu.syslocacar.model.services.LocacaoServices;
import edu.syslocacar.model.services.VeiculoServices;
import edu.syslocacar.utils.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LocacaoController implements Initializable {
    @FXML
    TableView<Locacao> tbLocacao;
    @FXML
    TableColumn<Veiculo, Integer> columnId;
    @FXML
    TableColumn <Veiculo, String> columnDataLocacao;
    @FXML
    TableColumn <Veiculo, String> columnDataDevolucao;
    @FXML
    TableColumn <Veiculo, Float> columnValorCaucao;
    @FXML
    TableColumn <Veiculo, Float> columnValorTotal;
    @FXML
    TableColumn <Veiculo, String> columnStatus;
    @FXML
    TableColumn <Veiculo, List<Integer>> columnVeiculos;
    @FXML
    Button btnNovo;

    protected Locacao locacao;
    @Setter
    protected VeiculoServices veiculoServices;
    @Setter
    protected LocacaoServices locacaoServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.veiculoServices = new VeiculoServices();
        this.locacaoServices = new LocacaoServices();
        iniciarGUI();
        updateTableView();
    }

    public void iniciarGUI(){

        // Vincula as celulas de cada coluna com os campos da classe model
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDataLocacao.setCellValueFactory(new PropertyValueFactory<>("dataLocacao"));
        columnDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        columnValorCaucao.setCellValueFactory(new PropertyValueFactory<>("valorCaucao"));
        columnValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnVeiculos.setCellValueFactory(new PropertyValueFactory<>("id_veiculos"));

        tbLocacao.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                this.locacao = tbLocacao.getSelectionModel().getSelectedItem();
                if(this.locacao != null) {
                    modalView("Locacao", FXMLPathProvider.getLocacaoModalViewPath());
                    updateTableView();
                }
            }
        });

    }

    // MODAL VIEW
    public void modalView(String title, String pathFXML){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(new FileInputStream(pathFXML));
            LocacaoModalController controller = fxmlLoader.getController();
            controller.setVeiculoServices(this.veiculoServices);
            controller.setLocacaoServices(this.locacaoServices);
            controller.setLocacao(this.locacao);
            controller.updateTableView();
            Stage modal = new Stage();
            modal.setTitle(title);
            modal.setScene(new Scene(root));
            modal.initModality(Modality.WINDOW_MODAL);
            modal.initOwner(MainApp.getScene().getWindow());
            modal.showAndWait();

        } catch (RuntimeException | IOException e) {
            Alerta.exibirAlerta("Error","Erro ao carregar a view",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // UPDATE TABLE
    public void updateTableView(){
        if (veiculoServices == null || locacaoServices == null){
            throw  new IllegalStateException("Services não instanciados: nullpoint exception");
        }

        List<Locacao> dados = locacaoServices.getAllLocacoes();
        ObservableList<Locacao> observableList = FXCollections.observableList(dados);
        tbLocacao.setItems(observableList);
    }

    @FXML
    public void onBtnNovoAction(){
        this.locacao = null;
        modalView("Cadastrar Locacao", "C:\\Users\\Michael\\Documents\\Faculdade\\POO3\\java-syslocacar\\src\\main\\java\\edu\\syslocacar\\view\\LocacaoModalView.fxml");
        updateTableView();
    }
}
