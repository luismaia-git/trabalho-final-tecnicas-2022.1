package com.tecgames.controller;

import com.tecgames.model.Game;
import com.tecgames.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameInfoController {

    public HBox divheader;
    public Label nomejogo;
    public Label descricaojogo;
    public Pane imagem;
    public Label genero;
    public Label anolancamento;
    public Label requisitos;
    public Label preco;
    public Button adicionarcarrinho;

    private User usuarioLogado;
    private Game jogoEscolhido;
    private int valueTela;// se 1 entao eu entrei na tela de gameinfo apartir da tela de loja, se 2 entao foi da tela meus jogos

    boolean have_game;
    public void initData(Game jogo, User usuario, int valor) {
        this.usuarioLogado = usuario;
        this.jogoEscolhido = jogo;
        this.valueTela = valor;

        nomejogo.setText(jogo.getNome());
        descricaojogo.setText(jogo.getDescricao());
        genero.setText(jogo.getGenero());
        anolancamento.setText(jogo.getAnolançamento());
        requisitos.setText(jogo.getRequisitos());
        preco.setText(String.valueOf(jogo.getPreço()));

        have_game = false;

        if(have_game) {
            adicionarcarrinho.setText("Ja comprado");
            adicionarcarrinho.setOnAction( e -> {
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Informação");
                dialogoInfo.setHeaderText("Você ja tem esse jogo!");
                dialogoInfo.setContentText("Clique em ok");
                dialogoInfo.showAndWait();
            });
        } else {
            adicionarcarrinho.setOnAction( e -> {
                //adiciono no carrinho
            });
        }

    }



    @FXML
    protected void onVoltarButtonClick() throws IOException {

        //carregando estilização
        FXMLLoader loader = new FXMLLoader();

        //This line gets the Stage(window) information
        Stage window = (Stage) divheader.getScene().getWindow();

        if(this.valueTela == 1) {
            loader.setLocation(getClass().getResource("/com/tecgames/view/loja-view.fxml"));

            Parent View = loader.load();

            Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização

            //+estilizações com css
            ViewScene.getStylesheets().add(getClass().getResource("/com/tecgames/view/css/loja.css").toExternalForm());

            LojaUIController lojaController = loader.getController();
            lojaController.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja

            window.setScene(ViewScene); //mudando a cena da janela para a loja
        } else {
            loader.setLocation(getClass().getResource("/com/tecgames/view/meusjogos-view.fxml"));

            Parent View = loader.load();

            Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização

            //+estilizações com css
            ViewScene.getStylesheets().add(getClass().getResource("/com/tecgames/view/css/meusjogos.css").toExternalForm());

            MeusjogosController meusjogosController = loader.getController();
            meusjogosController.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja

            window.setScene(ViewScene); //mudando a cena da janela para a loja
        }


    }

    public User getUsuarioLogado() {
        return this.usuarioLogado;
    }


    @FXML
    protected void onAdicionarButtonClick() throws IOException {

    }



}
