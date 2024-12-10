package edu.syslocacar.controller;

import edu.syslocacar.model.services.FXMLPathProvider;
import edu.syslocacar.model.services.VeiculoServices;
import edu.syslocacar.utils.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    ScrollPane scrollPaneMain;
    @FXML
    VBox vBoxMain;
    @FXML
    MenuBar menuBarMain;
    @FXML
    Menu menuCadastro;
    @FXML
    Menu menuRelatorios;
    @FXML
    Menu menuHelp;
    @FXML
    MenuItem menuItemVeiculos;
    @FXML
    MenuItem menuItemLocacao;
    @FXML
    MenuItem menuItemManutencao;
    @FXML
    MenuItem menuItemRelatorioVeiculos;
    @FXML
    MenuItem menuItemAbout;

    //******************************************************************************************************************
    // INITIALIZER
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    //******************************************************************************************************************
    // TRATAMENTO DE EVENTOS
    @FXML
    public void onCadastroVeiculoAction(){
        loadView(FXMLPathProvider.getVeiculoView());
    }

    @FXML
    public void onCadastroLocacaoAction(){
        loadView(FXMLPathProvider.getLocacaoViewPath());
    }

    @FXML
    public void onCadastroManutencaoAction(){
        System.out.println("menu manutenção");
    }
    @FXML
    public void onRelatorioVeiculosAction(){
        System.out.println("menu relatorio veiculo");
    }
    @FXML
    public void onHelpAboutAction(){
        Alerta.exibirAlerta("SYSLocaCar","About","SYSLocaCar V 1.0", Alert.AlertType.INFORMATION);
    }
    //******************************************************************************************************************
    // CARREGAMENTO DA VIEW
    public void loadView(String pathFXML){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            VBox vBox = fxmlLoader.load(new FileInputStream(pathFXML));

            vBoxMain.getChildren().clear();
            vBoxMain.getChildren().add(menuBarMain);
            vBoxMain.getChildren().addAll(vBox.getChildren());

//            VeiculoController controller = fxmlLoader.getController();
//            controller.setVeiculoServices(new VeiculoServices());
//            controller.updateTableView();
//            controller.tbVeiculo.prefHeightProperty().bind(vBoxMain.heightProperty());

        } catch (RuntimeException | IOException e) {
            Alerta.exibirAlerta("Error","Erro ao carregar a view",e.getMessage(), Alert.AlertType.ERROR);
        }
    }


}
