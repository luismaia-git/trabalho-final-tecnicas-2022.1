package com.tecgames.controller;

import com.tecgames.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Class CarrinhoVazioController, controller for the CarrinhoVazio screen
 * */
public class CarrinhoVazioController {

    public Pane divcontent2;
    private User usuarioLogado;

    /**
     * Sets controller initial data
     * */
    public void initData(User usuarioLogado) {
        this.usuarioLogado = usuarioLogado;

    }

    private User getUsuarioLogado() {
        return this.usuarioLogado;
    }


    /**
     * Button to go to store
     * */
    @FXML
    protected void onLojaButtonClick() throws IOException {
        //carregando estilização da loja
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/loja-view.fxml"));

        Parent lojaView = loader.load();

        Scene lojaViewScene = new Scene(lojaView); // instanciando uma nova cena com a estilização da loja

        LojaController lojaController = loader.getController();

        lojaController.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja


        //This line gets the Stage(window) information
        Stage window = (Stage) divcontent2.getScene().getWindow();

        window.setScene(lojaViewScene); //mudando a cena da janela para a loja
    }
}
