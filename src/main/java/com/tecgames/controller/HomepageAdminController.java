package com.tecgames.controller;

import com.tecgames.model.Admin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/** Class HomepageAdminController, controller for the admins homepage */
public class HomepageAdminController {

    public Label iconLetter;
    public Button logout;
    private Admin AdminLogado;


    /** Returns the logged admin */ 
    public Admin getAdminLogado() {
        return AdminLogado;
    }

    /** Sets controller initial data */
    public void initData(Admin usuario) {

        this.AdminLogado = usuario;

        char firstCaracter = usuario.getNome().charAt(0);
        iconLetter.setText(String.valueOf(firstCaracter).toUpperCase());
    }


    /** Button logout */
    @FXML
    protected void onLogoutButtonClick() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/tecgames/view/login-view.fxml"));
        Scene scene = new Scene(root, 1000, 600); //cena

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);

    }

    /** Manage games button */
    @FXML
    protected void onGerenciarJogosButtonClick() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/gerenciarjogos-view.fxml"));
        Parent page = loader.load();
        Scene scene = new Scene(page, 1000, 600); //cena

        GerenciarJogosController controller = loader.getController();
        controller.initData(getAdminLogado());
        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);
    }

    /** Manage clients button */
    @FXML
    protected void onGerenciarClientesButtonClick() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/gerenciarclientes-view.fxml"));
        Parent page = loader.load();
        Scene scene = new Scene(page, 1000, 600); //cena

        GerenciarClientesController controller = loader.getController();
        controller.initData(getAdminLogado());

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);

    }

    /** Search sales button */
    @FXML
    protected void onBuscarVendasButtonClick() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/buscarvendas-view.fxml"));
        Parent page = loader.load();
        Scene scene = new Scene(page, 1000, 600); //cena

        BuscarVendasController controller = loader.getController();
        controller.initData(getAdminLogado());

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);

    }


    /** Button to add admin */
    @FXML
    protected void onAdicionarAdminButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/criar-conta-admin-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View);

        //acess the controller and call a method (initData)
        CriarContaAdminController Controller = loader.getController();

        Controller.initData(getAdminLogado());

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(ViewScene);
    }
}