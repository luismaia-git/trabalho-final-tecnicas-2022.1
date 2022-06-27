package com.tecgames.controller;

import com.tecgames.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MeucarrinhoController implements Initializable {
    public Button voltar;
    public AnchorPane divcontent2;

    private User usuarioLogado;

    public User getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(User usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            displayCarrinho();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void displayCarrinho() throws IOException {
        boolean carrinho = false;

        System.out.println(getUsuarioLogado());
        if(carrinho){

        }else {
            System.out.println("opa");
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(getClass().getResource("/com/tecgames/view/components/carrinhovazio.fxml"));

            //MeucarrinhoVazioController controller = loader.getController();

            //controller.setUsuarioLogado(getUsuarioLogado());

            //divcontent2 = loader.load();



        }
    }

    @FXML
    protected void onVoltarButtonClick() throws IOException {
        //carregando estilização
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/homecliente-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização

        //+estilizações com css
        ViewScene.getStylesheets().add(getClass().getResource("/com/tecgames/view/css/homecliente.css").toExternalForm());


        HomePageClienteController Controller = loader.getController();
        Controller.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de home


        //This line gets the Stage(window) information
        Stage window = (Stage) voltar.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a home
    }




}
