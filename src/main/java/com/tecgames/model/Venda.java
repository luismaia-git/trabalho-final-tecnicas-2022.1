package com.tecgames.model;

import java.util.*;

public class Venda {
    private int id_venda;
    private int id_usuario;
    private double valortotal;
    private String data;
    private String hora;
    
    ArrayList<Integer> id_jogos;
    
    public Venda(String id_venda, String id_usuario, String valortotal, String data, String hora) {
        this.id_venda = Integer.parseInt(id_venda);
        this.id_usuario = Integer.parseInt(id_usuario);
        this.valortotal = Double.parseDouble(valortotal.replaceAll( "," , "." ));
        this.data = data;
        this.hora = hora;      
    }

    public Venda(String id_usuario, String valortotal, String data, String hora) {
        this.id_usuario = Integer.parseInt(id_usuario);
        this.valortotal = Double.parseDouble(valortotal.replaceAll( "," , "." ));
        this.data = data;
        this.hora = hora;        
    }

    public int getIdvenda() {
        return id_venda;
    }

    public void setIdvenda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getIdusuario() {
        return id_usuario;
    }

    public void setIdusuario(int id) {
        this.id_usuario = id;
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
