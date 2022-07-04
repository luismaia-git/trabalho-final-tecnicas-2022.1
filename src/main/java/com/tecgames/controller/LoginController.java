package com.tecgames.controller;


import com.tecgames.model.User;

import com.tecgames.model.UsuarioDados;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    public Button entrar;
    public TextField emailData;
    public TextField senhaData;
    public Button criarConta;
    public Pane backgroundLogin;
    public Pane content;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //animação no background
        ScaleTransition st = new ScaleTransition(Duration.millis(20000), backgroundLogin);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(1.1);
        st.setToY(1.1);
        st.setCycleCount(100);
        st.setAutoReverse(true);
        st.play();


        //animação no login
        TranslateTransition openLogin = new TranslateTransition(new Duration(950), content);
        openLogin.setFromX(-(content.getPrefWidth()));
        openLogin.setToX(0);
        openLogin.play();




    }


    @FXML
    protected void onLoginButtonClick() throws IOException {

        boolean is_admin = true;
        //user test
        UsuarioDados usuarioDAO = new UsuarioDados();

        //User teste = usuarioDAO.carregaArquivoUsuarios().get(0);

        User teste_admin = new User("1", "Administrador1", "admin1@email.com", "123", "777", "12/07/2001");

        //This line gets the Stage(window) information
        Stage window = (Stage) entrar.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();

        if(is_admin){
            loader.setLocation(getClass().getResource("/com/tecgames/view/homeadmin-view.fxml"));
            Parent View = loader.load();


            Scene ViewScene = new Scene(View);

            //acess the controller and call a method (initData)
            HomepageAdminController Controller = loader.getController();
            Controller.initData(teste_admin );

            window.setScene(ViewScene);
        }else {

            loader.setLocation(getClass().getResource("/com/tecgames/view/homecliente-view.fxml"));
            Parent homepageView = loader.load();

            Scene homepageViewScene = new Scene(homepageView);

            //acess the controller and call a method (initData)
            HomepageClienteController homeController = loader.getController();
            //homeController.initData(teste);

            window.setScene(homepageViewScene);
        }



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