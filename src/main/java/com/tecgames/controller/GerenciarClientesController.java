package com.tecgames.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GerenciarClientesController {


    public Button logout;

    @FXML
    protected void onLogoutButtonClick() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/tecgames/view/login-view.fxml"));
        Scene scene = new Scene(root, 1000, 600); //cena

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);

    }

    @FXML
    protected void onVoltarButtonClick() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/tecgames/view/homeadmin-view.fxml"));
        Scene scene = new Scene(root, 1000, 600); //cena

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);

    }
}
