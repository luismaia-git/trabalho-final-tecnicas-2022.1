package com.tecgames.model;

import java.util.ArrayList;

/**
 * Class model Carrinho
 * */
public class Carrinho {

    private int id_usuario;
    private ArrayList<Integer> id_jogos;

    /**
     * Constructor for class Carrinho
     * */
    public Carrinho() {
    }

    /**
     * Constructor for class Carrinho
     * */
    public Carrinho(String id_usuario) {
        this.id_usuario = Integer.parseInt(id_usuario);
    }

    public int getId() {
        return id_usuario;
    }

    public void setId(int id) {
        this.id_usuario = id;
    }


    public ArrayList<Integer> getIdjogos() {
        return id_jogos;
    }

    public void setIdjogos(ArrayList<Integer> id_jogos) {
        this.id_jogos = id_jogos;
    }
}
