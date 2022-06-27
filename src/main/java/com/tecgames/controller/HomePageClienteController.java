package com.tecgames.controller;

import com.tecgames.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageClienteController {

    public Label iconLetter;
    public Circle circleIcon;

    public Button logout;
    public Label title;

    private User usuarioLogado; //ainda vai ser usado

    public void initData(User usuario) {

        this.usuarioLogado = usuario;
        title.setText("Olá, "+ usuario.getNome());
        //System.out.println(this.usuarioLogado.getNome());

        char firstCaracter = usuario.getNome().charAt(0);

        iconLetter.setText(String.valueOf(firstCaracter));
        //iconLetter.setAlignment(Pos.CENTER); not working
    }

    //button logout
    @FXML
    protected void onLogoutButtonClick() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/tecgames/view/login-view.fxml"));
        Scene scene = new Scene(root, 1000, 600); //cena
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@100;400&display=swap");
        scene.getStylesheets().add(getClass().getResource("/com/tecgames/view/css/login.css").toExternalForm());

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);

    }

    //botao loja
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
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(lojaViewScene); //mudando a cena da janela para a loja

    }


    @FXML
    protected void onMeusjogosButtonClick() throws IOException {
        //carregando estilização da loja
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/meusjogos-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização da loja

        //+estilizações com css
        ViewScene.getStylesheets().add(getClass().getResource("/com/tecgames/view/css/meusjogos.css").toExternalForm());

        MeusjogosController meusjogosController = loader.getController();

        meusjogosController.setUsuarioLogado(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja


        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a loja

    }

    @FXML
    protected void onCarrinhoButtonClick() throws IOException {
        //carregando estilização da loja
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/meucarrinho-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização da loja

        //+estilizações com css
        ViewScene.getStylesheets().add(getClass().getResource("/com/tecgames/view/css/meucarrinho.css").toExternalForm());

        MeucarrinhoController Controller = loader.getController();
        System.out.println("AQUIIII " + getUsuarioLogado().getNome());
        Controller.setUsuarioLogado(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja


        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a loja

    }




    public User getUsuarioLogado() {
        return this.usuarioLogado;
    }


}
