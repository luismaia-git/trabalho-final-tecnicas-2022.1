package com.tecgames.controller;

import com.tecgames.model.Game;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class CardCarrinhoController {


    public Pane cardcarrinho;
    public Pane imagemjogo;
    public Button removerjogocarrinho;
    public Label preco;
    public Label title;

    private Game jogo;


    public void initData(Game jogo) {

        this.jogo = jogo;
        title.setText(jogo.getNome());

        preco.setText(String.valueOf(jogo.getPreço()));

        //imagejogo.setStyle("-fx-background-image: url('/com/tecgames/view/images/games/1-2.png');");//NOT WORKING
    }

    public Button getRemover(){
        return removerjogocarrinho;
    }
}
