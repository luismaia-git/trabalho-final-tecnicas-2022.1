package com.tecgames.model;

import java.util.*;


/**
 * Class model Venda
 * */
public class Venda {
    private int idvenda;
    private int idusuario;
    private double valortotal;
    private String data;
    private String hora;
    
    ArrayList<Integer> id_jogos;

    /**
     * Constructor for Venda
     * */
    public Venda(String id_venda, String id_usuario, String valortotal, String data, String hora) {
        this.idvenda = Integer.parseInt(id_venda);
        this.idusuario = Integer.parseInt(id_usuario);
        this.valortotal = Double.parseDouble(valortotal.replaceAll( "," , "." ));
        this.data = data;
        this.hora = hora;      
    }

    /**
     * Constructor for Venda
     * */
    public Venda(){}

    public int getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(int id_venda) {
        this.idvenda = id_venda;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int id) {
        this.idusuario = id;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public ArrayList<Integer> getIdjogos() {
        return id_jogos;
    }

    public void setIdjogos(ArrayList<Integer> id_jogos) {
        this.id_jogos = id_jogos;
    }

}
