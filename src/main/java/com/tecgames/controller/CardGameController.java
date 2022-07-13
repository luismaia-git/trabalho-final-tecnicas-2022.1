package com.tecgames.controller;

import com.tecgames.model.Game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

/**
 * Class CardGameController, controller for the CardGame component
 * */
public class CardGameController {


    public Label title;
    public Label preço;



    public Button vermais;
    public ImageView imagegame;
    public Pane painelpreco;


    private Game jogo;


    /**
     * Sets controller initial data
     * */
    public void initData(Game jogo) {
        //setando as informações do card de jogo que é mostrado na loja e em meus jogos
        this.jogo = jogo;
        title.setText(jogo.getNome());

        preço.setText(String.valueOf(jogo.getPreço()));
        String caminho = String.format(".\\images/games/%s.jpg", jogo.getId());
        File foto = new File(caminho);
        Image imagem  = new Image(foto.getAbsolutePath() , 152, 140 , false , false);
        imagegame.setImage(imagem);
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
