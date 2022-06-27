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
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@100;400&display=swap");
        scene.getStylesheets().add(getClass().getResource("view/css/login.css").toExternalForm());

        stage.setTitle("TecGames");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

//scene.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());