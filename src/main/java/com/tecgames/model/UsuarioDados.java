package com.tecgames.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UsuarioDados {

    public ArrayList<User> listar() {
        ArrayList<User> usuarios;

        usuarios = carregaArquivoUsuarios();
        return usuarios;
    }

    public User buscarUsuario(int id){
        ArrayList<User> usuarios;

        usuarios = carregaArquivoUsuarios();

        int i = 0;
        while (i < usuarios.size()) {

            if ((id == usuarios.get(i).getId())) {

                return usuarios.get(i);
            }
            i = i + 1;
        }

        return null;

    }

    public boolean inserir(User usuario) throws IOException {
        ArrayList<User> usuarios;
        usuarios = carregaArquivoUsuarios();

        if (!checaUsuario(usuario, usuarios)) {
            if (usuarios.equals(null))
                usuario.setId(1);
            else {
                User ultimo = usuarios.get(usuarios.size() - 1);          // Pega o ultimo elemento do ArrayList de users
                usuario.setIdvenda(ultimo.getIdvenda() + 1);            // Pega o id do ultimo elemento, soma 1 e seta no ID do usuario que será adicionado

            }    
            usuarios.add(usuario);
            escreveArquivoUsuarios(usuarios);
            return true;
        }
        else
            return false;
        
    }

    public boolean remover(User usuario) throws IOException {
        ArrayList<User> usuarios;
        usuarios = carregaArquivoUsuarios();
        User aux = retornaUsuario(usuario, usuarios);
        if (!aux.getNome().equals(null)) { 
            usuarios.remove(usuario);   
            escreveArquivoUsuarios(usuarios);
            return true;
        }
        else
            return false;
        
    }

    public User retornaUsuario(User usuario, ArrayList<User> usuarios) {
        int i = 0;
        while (i < usuarios.size()) {
            User user = usuarios.get(i);
            if ((usuario.getNome().equals(user.getNome())))
                return user;
            
            i = i + 1;
        }
        
        return null;
    }

    public boolean alterar(ArrayList<User> usuarios){
        escreveArquivoUsuarios(usuarios);
        return true;
    }


    public boolean checaUsuario(User usuario, ArrayList<User> usuarios) {
        int i = 0;

        while (i < usuarios.size()) {
            User user = usuarios.get(i);
            if ((user.getEmail().equals(usuario.getEmail())) || (user.getCpf().equals(usuario.getCpf()))) {
                return true;
            }
            i = i + 1;
        }
        
        return false;
    }



    public ArrayList<User> carregaArquivoUsuarios () {

        ArrayList<User> usuariosArquivo = new ArrayList<>();

        try (BufferedReader buffRead = new BufferedReader(new FileReader(".\\dados/usuarios.txt"))) {
            String linha;
            while (true) {
                linha = buffRead.readLine();

                if(linha == null){
                    break;
                }else {
                    String array[] = linha.split(";");
                    User user = new User(array[0], array[1],array[2], array[3], array[4], array[5]);

                    String[] array2 = array[6].split(",");
                    ArrayList<Integer> games = new ArrayList<>();

                    for (int i = 0; i < array2.length; i++) {
                        games.add(Integer.parseInt(array2[i]));
                    }

                    user.setJogosUsuario(games);

                    usuariosArquivo.add(user);
                }
                
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return usuariosArquivo;
    }


    public void escreveArquivoUsuarios(ArrayList<User> usuarios){
        try {
            File arq = new File(".\\dados/usuarios.txt");
            if (!arq.exists()) {
                arq.createNewFile();
            }

            FileWriter gravador = new FileWriter(arq);
            try (BufferedWriter buffer = new BufferedWriter(gravador)) {
                for (int i = 0; i < usuarios.size(); i++) {
                    User user = usuarios.get(i);
                    String idjogos = "";
                    if ((user.getMeusJogos().size() > 1)) {
                        for (int j = 0; j < user.getMeusJogos().size() - 1; j++) {
                            idjogos = idjogos + String.valueOf(user.getMeusJogos().get(j)) + ",";
                        }
                        idjogos = idjogos + user.getMeusJogos().get(user.getMeusJogos().size() - 1);
                    }

                    if (user.getMeusJogos().size() == 1)
                        idjogos = idjogos + user.getMeusJogos().get(user.getMeusJogos().size() - 1);

                    String linha = user.getNome() + ";" + user.getEmail() + ";" + user.getSenha() + ";" + user.getCpf() + ";" + user.getData() + ";" + idjogos + "\n";
                    buffer.write(linha);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}