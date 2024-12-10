package edu.syslocacar.controller;

import edu.syslocacar.MainApp;
import edu.syslocacar.model.entity.Veiculo;
import edu.syslocacar.model.services.FXMLPathProvider;
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
//******************************************************************************************************************
public class VeiculoController implements Initializable {
    @FXML
    VBox vBoxVeiculo;
    @FXML
    Label lbVeiculo;
    @FXML
    TableView<Veiculo> tbVeiculo;
    @FXML
    TableColumn<Veiculo, Integer> columnIdVeiculo;
    @FXML
    TableColumn <Veiculo, String> columnMarcaVeiculo;
    @FXML
    TableColumn <Veiculo, String> columnModeloVeiculo;
    @FXML
    TableColumn <Veiculo, String> columnPlacaVeiculo;
    @FXML
    TableColumn <Veiculo, String> columnStatusVeiculo;
    @FXML
    Button btnNovo;

    protected Veiculo veiculo;
    private String pathVeiculo;
    @Setter
    protected VeiculoServices veiculoServices;

    //******************************************************************************************************************
    //INITIALIZER

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.veiculoServices = new VeiculoServices();
        this.updateTableView();
        this.pathVeiculo = FXMLPathProvider.getVeiculoViewModalPath();
        iniciarGUI();
    }
    public void iniciarGUI(){

        // Vincula as celulas de cada coluna com os campos da classe model
        columnIdVeiculo.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMarcaVeiculo.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnModeloVeiculo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        columnPlacaVeiculo.setCellValueFactory(new PropertyValueFactory<>("placa"));
        columnStatusVeiculo.setCellValueFactory(new PropertyValueFactory<>("status"));

        tbVeiculo.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                this.veiculo = tbVeiculo.getSelectionModel().getSelectedItem();
                if(this.veiculo != null) {
                    System.out.println("Duplo clique em: " + this.veiculo.getPlaca());
                    modalView("Veiculo",pathVeiculo);
                    updateTableView();
                }
            }
        });

    }
    //******************************************************************************************************************
    // MODAL VIEW
    public void modalView(String title, String pathFXML){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(new FileInputStream(pathFXML));
            VeiculoViewModalController controller = fxmlLoader.getController();
            controller.setVeiculoServices(new VeiculoServices());
            controller.setVeiculo(this.veiculo);
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
    //******************************************************************************************************************
    // UPDATE TABLE
    public void updateTableView(){
        if (veiculoServices == null){
            throw  new IllegalStateException("Sercice VeiculoService não instanciado: nullpoint exception");
        }

        List<Veiculo> lista = veiculoServices.getAllVeiculos();
        lista.forEach( (obj) -> System.out.println(obj.getId() +", "+ obj.getMarca()+", "+obj.getModelo() + ", " + obj.getPlaca() + ", "+obj.getStatus()));
        ObservableList<Veiculo> observableList = FXCollections.observableList(lista);
        tbVeiculo.setItems(observableList);
    }

    //******************************************************************************************************************
    // TRATAMENTO DE EVENTOS
    @FXML
    public void onBtnNovoAction(){
        this.veiculo = null;
        modalView("Manter Veiculos",pathVeiculo);
        updateTableView();
    }

}

