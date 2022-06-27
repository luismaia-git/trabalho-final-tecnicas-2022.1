package com.tecgames.controller;

import com.tecgames.model.Game;
import com.tecgames.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CardGameController {


    public Label title;
    public Label preço;



    public Button vermais;
    public Pane imagegame;
    public Pane painelpreco;


    private Game jogo;



    public void initData(Game jogo) {

        this.jogo = jogo;
        title.setText(jogo.getNome());

        preço.setText(String.valueOf(jogo.getPreço()));

        imagegame.setStyle("-fx-background-image: url('/games/1-2.png');");//NOT WORKING
    }


    public Game getJogo() {
        return this.jogo;
    }

    public Button getVermais() {
        return vermais;
    }

    public Pane getPainelpreco() {
        return painelpreco;
    }

    public void setPainelpreco(Pane painelpreco) {
        this.painelpreco = painelpreco;
    }
}
