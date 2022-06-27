package com.tecgames.controller;


import com.tecgames.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public Button entrar;
    public TextField emailData;
    public TextField senhaData;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        //user test

        User teste = new User("Italo", emailData.getText(), senhaData.getText(), "1234", "12/08/2001");






        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/homecliente-view.fxml"));
        Parent homepageView = loader.load();


        Scene homepageViewScene = new Scene(homepageView);

        //acess the controller and call a method (initData)
        HomePageClienteController homeController = loader.getController();
        homeController.initData(teste);


        //This line gets the Stage(window) information
        Stage window = (Stage) entrar.getScene().getWindow();
        window.setScene(homepageViewScene);

    }
}