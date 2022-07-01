package com.tecgames.controller;

import com.tecgames.model.Carrinho;
import com.tecgames.model.CarrinhoDados;
import com.tecgames.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MeuCarrinhoController implements Initializable {
    public Button voltar;
    public Pane divcontent;


    private User usuarioLogado;

    public User getUsuarioLogado() {
        return usuarioLogado;
    }
    @FXML
    public void setUsuarioLogado(User usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }


    public void initData(User usuarioLogado) throws IOException {
        this.usuarioLogado = usuarioLogado;
        displayCarrinho();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void displayCarrinho() throws IOException {
        boolean carrinho = false;
        
        CarrinhoDados carrinhoDAO = new CarrinhoDados();
        ArrayList<Carrinho> array_ca = carrinhoDAO.carregaArquivoCarrinhos();

        for(int i = 0; i < array_ca.size(); i++) {
            if(usuarioLogado.getId() == array_ca.get(i).getId()){//achei o usuario
                carrinho = true;
            }
        }


        //aqui carrega o dados do carrinho
        //tenho que saber se o usuario logado tem alguma jogo no carrinho
        if(carrinho){

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/tecgames/view/components/carrinhocheio.fxml"));


            Pane new_pane = loader.load();
            divcontent.getChildren().setAll(new_pane);

            CarrinhoCheioController controller = loader.getController();

            controller.initData(getUsuarioLogado());

        } else {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/tecgames/view/components/carrinhovazio.fxml"));


            Pane new_pane = loader.load();
            divcontent.getChildren().setAll(new_pane);

            CarrinhoVazioController controller = loader.getController();

            controller.initData(getUsuarioLogado());


        }
    }

    @FXML
    protected void onVoltarButtonClick() throws IOException {
        //carregando estilização
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/homecliente-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização


        HomepageClienteController Controller = loader.getController();
        Controller.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de home


        //This line gets the Stage(window) information
        Stage window = (Stage) voltar.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a home
    }




}
