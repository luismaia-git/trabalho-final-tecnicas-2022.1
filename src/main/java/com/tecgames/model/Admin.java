package com.tecgames.model;
/**
 * Class model admin
 * */
public class Admin {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String dataNasc;

    /**
     * Constructor for class Admin
     * */
    public Admin(String id, String nome, String email, String senha, String cpf, String data){
        this.id = Integer.parseInt(id);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNasc = data;
    }

    /**
     * Constructor for class Admin
     * */
    public Admin() {}

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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



    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }


    public String getData() {
        return this.dataNasc;
    }

    public void setData(String data){
        this.dataNasc = data;
    }


}
