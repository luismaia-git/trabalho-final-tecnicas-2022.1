package com.tecgames.model;

public class Game {

    private int id;
    private String nome;
    private double preço;

    private String genero;

    private String descricao;

    private String anolançamento;

    private String requisitos;


    public Game(String nome, double preço) {
        id++;
        this.nome = nome;
        this.preço = preço;
        this.genero = "Não informado";
        this.descricao = "Não informado";
        this.anolançamento = "Não informado";
        this.requisitos = "Não informado";
    }



    public Game(String nome, double preço, String genero, String descricao, String anolançamento, String requisitos){
        id++;
        this.nome = nome;
        this.preço = preço;
        this.genero = genero;
        this.descricao = descricao;
        this.anolançamento = anolançamento;
        this.requisitos = requisitos;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNomeGame(String nome) {
        this.nome = nome;
    }

    public double getPreço() {
        return preço;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public String getGenero() {
        return genero;
    }


    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAnolançamento() {
        return anolançamento;
    }

    public void setAnolançamento(String anolançamento) {
        this.anolançamento = anolançamento;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }
}
