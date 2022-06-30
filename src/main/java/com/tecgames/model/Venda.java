import java.util.*;

public class Venda {
    int id_venda;
    int id_usuario;
    ArrayList<Integer> id_jogos;
    
    public Venda(String id_venda, String id_usuario) {
        this.id_venda = Integer.parseInt(id_venda);
        this.id_usuario = Integer.parseInt(id_usuario);        
    }

    public Venda(String id_usuario) {
        this.id_usuario = Integer.parseInt(id_usuario);        
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

    public ArrayList<Integer> getIdjogos() {
        return id_jogos;
    }

    public void setIdjogos(ArrayList<Integer> id_jogos) {
        this.id_jogos = id_jogos;
    }

}
