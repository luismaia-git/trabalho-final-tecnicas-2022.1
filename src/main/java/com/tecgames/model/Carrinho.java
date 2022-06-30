package com.tecgames.model;

import java.util.ArrayList;

public class Carrinho {

    int id_usuario;
    ArrayList<Integer> id_jogos;


    public Carrinho() {
    }

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
