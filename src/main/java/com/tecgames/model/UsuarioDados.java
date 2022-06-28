package com.tecgames.model;

import java.io.*;
import java.util.ArrayList;

public class UsuarioDados {

    public ArrayList<User> listar() {
        ArrayList<User> usuarios;

        usuarios = carregaArquivoUsuarios();
        return usuarios;
    }

    public boolean inserir(User usuario) throws IOException {
        ArrayList<User> usuarios;
        usuarios = carregaArquivoUsuarios();

        if (!checaUsuario(usuario, usuarios)) {
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

    //

    public boolean checaUsuario(User usuario, ArrayList usuarios) throws IOException {
        int i = 0;

        while (i < usuarios.size()) {
            User user = (User) usuarios.get(i);
            if ((user.getEmail().equals(usuario.getEmail())) || (user.getCpf().equals(usuario.getCpf()))) {
                return true;
            }
            i = i + 1;
        }
        
        return false;
    }



    public ArrayList<User> carregaArquivoUsuarios () {

        ArrayList<User> usuariosArquivo = new ArrayList<>();
        try (BufferedReader buffRead = new BufferedReader(new FileReader("dados.txt"))) {
            String linha;
            while (true) {
                linha = buffRead.readLine();
                if (linha != null) {
                    String array[] = linha.split(";");
                    User user = new User(array[0],array[1],array[2], array[3], array[4]);

                    String[] array2 = array[3].split(",");

                    ArrayList<Integer> games = new ArrayList<>();

                    for (int i = 0; i < array2.length; i++) {
                        games.add(Integer.parseInt(array2[i]));
                    }

                    user.setJogosUsuario(games);

                    usuariosArquivo.add(user);

                }
                else
                    break;
                
            }

            buffRead.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
        return usuariosArquivo;
    }


    public void escreveArquivoUsuarios(ArrayList<User> usuarios){
        try {
            File arq = new File("usuarios.txt");
            if (!arq.exists()) {
                arq.createNewFile();
            }

            FileWriter gravador = new FileWriter(arq);
            try (BufferedWriter buffer = new BufferedWriter(gravador)) {
                for (int i = 0; i < usuarios.size(); i++) {
                    User user = usuarios.get(i);
                    String idjogos = null;
                    for (int j = 0; j < user.getMeusJogos().size() - 1; j++) {
                        idjogos = idjogos + String.valueOf(user.getMeusJogos().get(j))  + ",";
                    }
                    idjogos = idjogos + String.valueOf(user.getMeusJogos().get(user.getMeusJogos().size() -1));
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