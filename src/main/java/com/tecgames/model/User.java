package com.tecgames.model;

import java.util.ArrayList;

/**
 * Class model User
 * */
public class User {
    private int id;
    private String nome;
    private String email;
    private String senha;

    private String cpf;

    private String dataNasc;

    private ArrayList<Integer> JogosUsuario;

    /**
     * Constructor for User
     * */
    public User(String id, String nome, String email, String senha, String cpf, String data){
        this.id = Integer.parseInt(id);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNasc = data;
        JogosUsuario = new ArrayList<>();
    }

    /**
     * Constructor for User
     * */
    public User(){}

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<Integer> getJogosUsuario() {
        return JogosUsuario;
    }

    public void setJogosUsuario(ArrayList<Integer> jogosUsuario) {
        JogosUsuario = jogosUsuario;
    }


    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public ArrayList<Integer> getMeusJogos() {
        return this.JogosUsuario;
    }

    public String getData() {
        return this.dataNasc;
    }

    public void setData(String data){
        this.dataNasc = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
