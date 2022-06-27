package com.tecgames.model;

import java.util.ArrayList;

public class User {
    private String nome;
    private String email;
    private String senha;

    private String cpf;

    private String dataNasc;

    private ArrayList<Integer> JogosUsuario;

    //2;1,2,3,4,5,6,7,7
    public User(String nome, String email, String senha, String cpf, String data){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNasc = data;
        JogosUsuario = new ArrayList<>();
    }

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
}
