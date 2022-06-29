package com.tecgames.controller;

import com.tecgames.model.Game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


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

        imagegame.setStyle("-fx-background-image: url('/com/tecgames/view/images/games/1-2.png');");//NOT WORKING
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
