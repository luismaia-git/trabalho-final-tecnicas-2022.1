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

    }

    public Button getRemover(){
        return removerjogocarrinho;
    }

    public Game getJogo() {
        return jogo;
    }

    public void setJogo(Game jogo) {
        this.jogo = jogo;
    }
}
