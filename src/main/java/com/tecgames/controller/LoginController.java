package com.tecgames.controller;


import com.tecgames.model.User;

import com.tecgames.model.UsuarioDados;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    public Button entrar;
    public TextField emailData;
    public TextField senhaData;
    public Button criarConta;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        //user test
        UsuarioDados usuarioDAO = new UsuarioDados();

        User teste = usuarioDAO.carregaArquivoUsuarios().get(0);



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

    @FXML
    protected void onCriarContaButtonClick() throws IOException {
        //carregando estilização
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/criar-conta-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização
        //This line gets the Stage(window) information
        Stage window = (Stage) entrar.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela
    }
}