package com.tecgames.controller;


import com.tecgames.model.Admin;
import com.tecgames.model.AdminDados;
import com.tecgames.model.User;

import com.tecgames.model.UsuarioDados;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/** Class LoginController, ontroller for user login */
public class LoginController implements Initializable{
    public Button entrar;
    public TextField emailData;
    public PasswordField senhaData;
    public Button criarConta;
    public Pane backgroundLogin;
    public Pane content;

    /** Defines the initial behavior of the controller */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //animação no background
        ScaleTransition st = new ScaleTransition(Duration.millis(15000), backgroundLogin);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(1.15);
        st.setToY(1.15);
        st.setCycleCount(100);
        st.setAutoReverse(true);
        st.play();


        //animação no login
        TranslateTransition openLogin = new TranslateTransition(new Duration(950), content);
        openLogin.setFromX(-(content.getPrefWidth()));
        openLogin.setToX(0);
        openLogin.play();


    }

    /** login button */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        //se todos os campos estao preenchidos
        if(validaCampos()){

            String email = emailData.getText();
            String senha = senhaData.getText();


            //Admin
            AdminDados adminDAO = new AdminDados();
            ArrayList<Admin> DadosArquivoAdmin = adminDAO.carregaArquivoAdmin();

            Admin admin = null;

            //verifica a conta no arquivo e faz admin receber essa conta
            int i = 0;
            while(i < DadosArquivoAdmin.size()){
                if(email.equals(DadosArquivoAdmin.get(i).getEmail())  && senha.equals(DadosArquivoAdmin.get(i).getSenha())){
                    admin = DadosArquivoAdmin.get(i);
                }
                i = i +1;
            }


            //This line gets the Stage(window) information
            Stage window = (Stage) entrar.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();

            UsuarioDados usuarioDAO = new UsuarioDados();
            User usuario = null;

            if(admin != null){
                loader.setLocation(getClass().getResource("/com/tecgames/view/homeadmin-view.fxml"));
                Parent View = loader.load();

                Scene ViewScene = new Scene(View);

                //acess the controller and call a method (initData)
                HomepageAdminController Controller = loader.getController();
                Controller.initData(admin);

                window.setScene(ViewScene);
            } else {
                //usuario
                ArrayList<User> ArquivoUsuarios = usuarioDAO.carregaArquivoUsuarios();

                int j = 0 ;
                while(j < ArquivoUsuarios.size()){
                    if(ArquivoUsuarios.get(j).getEmail().equals(email) && ArquivoUsuarios.get(j).getSenha().equals(senha)){
                        usuario = ArquivoUsuarios.get(j);
                    }
                    j = j +1;
                }

                if(usuario != null) {
                    loader.setLocation(getClass().getResource("/com/tecgames/view/homecliente-view.fxml"));
                    Parent homepageView = loader.load();

                    Scene homepageViewScene = new Scene(homepageView);

                    //acess the controller and call a method (initData)
                    HomepageClienteController homeController = loader.getController();
                    homeController.initData(usuario);

                    window.setScene(homepageViewScene);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Email ou senha invalidos!");
                    alert.show();
                }

            }
        }
        
    }

    /** create account button */
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

    /** Validates input data */
    public boolean validaCampos(){

        String errorMessage = "";

        if (emailData.getText() == null || emailData.getText().length() == 0) errorMessage += "Preencha o campo Email!\n";

        if (senhaData.getText() == null || senhaData.getText().length() == 0) errorMessage += "Preencha o campo Senha!\n";

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}