package com.tecgames;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //tela de login
        Parent root = FXMLLoader.load(getClass().getResource("view/login-view.fxml"));
        Scene scene = new Scene(root, 1000, 600); //cena

        stage.setTitle("TecGames");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

