package edu.syslocacar;

import edu.syslocacar.model.services.FXMLPathProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;

public class MainApp extends Application {

    @Getter
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();

        //fxmlLoader.setRoot(scrollPaneMain);
        Parent root = fxmlLoader.load(new FileInputStream(FXMLPathProvider.getMainViewPath()));
        scene = new Scene(root, 800, 550);

        ScrollPane scrollPane = (ScrollPane) scene.getRoot();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        stage.setTitle("SYSLocaCar!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}