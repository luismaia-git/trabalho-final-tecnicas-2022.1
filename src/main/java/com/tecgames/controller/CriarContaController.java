package com.tecgames.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CriarContaController {
    public Button criar;
    public Button cancelar;


    @FXML
    protected void onCancelarButtonClick() throws IOException {
        //carregando estilização
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/login-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização
        //This line gets the Stage(window) information
        Stage window = (Stage) criar.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela
    }
}
