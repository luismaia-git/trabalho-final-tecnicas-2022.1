package com.tecgames.controller;

import com.tecgames.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MeucarrinhoVazioController {

    public Button loja;



    private User usuarioLogado;

    @FXML
    protected void onLojaButtonClick() throws IOException {
        //carregando estilização da loja
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/loja-view.fxml"));

        Parent lojaView = loader.load();

        Scene lojaViewScene = new Scene(lojaView); // instanciando uma nova cena com a estilização da loja

        //+estilizações com css
        lojaViewScene.getStylesheets().add(getClass().getResource("/com/tecgames/view/css/loja.css").toExternalForm());

        LojaUIController lojaController = loader.getController();

        lojaController.setUsuarioLogado(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja


        //This line gets the Stage(window) information

        Stage window = (Stage) loja.getScene().getWindow();
        window.setScene(lojaViewScene); //mudando a cena da janela para a loja

    }

    public User getUsuarioLogado() {
        return this.usuarioLogado;
    }

    public void setUsuarioLogado(User usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
