package com.tecgames.controller;

import com.tecgames.model.Game;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

public class CardCarrinhoController {


    public Pane cardcarrinho;
    public ImageView imagemjogo;
    public Button removerjogocarrinho;
    public Label preco;
    public Label title;

    private Game jogo;

    public void initData(Game jogo) {

        this.jogo = jogo;
        title.setText(jogo.getNome());

        preco.setText(String.valueOf(jogo.getPreço()));

        String caminho = String.format(".\\images/games/%s.jpg", jogo.getId());
        File foto = new File(caminho);
        Image imagem  = new Image(foto.getAbsolutePath() , 122, 93 , false , false);
        imagemjogo.setImage(imagem);

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
