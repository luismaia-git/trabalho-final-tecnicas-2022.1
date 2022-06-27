package com.tecgames.model;

import java.io.*;
import java.util.ArrayList;

public class UsuarioDados {


    // inserir(usuario) listar() remover() alterar()

    //inserir(){  carregar() e escrever()      }
    //crud nos arquivos

    public ArrayList<User> listar() {
        ArrayList<User> usuarios;

        usuarios = carregaArquivoUsuarios();
        return usuarios;
    }

    public boolean inserir(User usuario) throws IOException {
        ArrayList<User> usuarios;
        boolean b= false;
        usuarios = carregaArquivoUsuarios();

        if(!checaUsuario(usuario, usuarios)){
            usuarios.add(usuario);
            escreveArquivoUsuarios(usuarios);
            b = true;
            return b;
        }else{
            return b;
        }
    }

    public boolean remover(User usuario) throws IOException {
        ArrayList<User> usuarios;
        boolean b= false;

        usuarios = carregaArquivoUsuarios();

        if(checaUsuario(usuario, usuarios)){
            usuarios.remove(usuario);
            escreveArquivoUsuarios(usuarios);
            b = true;
            return b;
        }else{
            return b;
        }
    }

    public boolean alterar(ArrayList<User> usuarios){
        escreveArquivoUsuarios(usuarios);
        return true;
    }

    //

    public boolean checaUsuario(User usuario, ArrayList usuarios) throws IOException {
        boolean aux = true;
        int i = 0;

        while (i < usuarios.size()) {
            User user = (User) usuarios.get(i);
            if ((user.getEmail().equals(usuario.getEmail())) || (user.getCpf().equals(usuario.getCpf()))) {
                return aux;
            }
            i = i + 1;
        }
        aux = false;
        return aux;
    }



    public ArrayList<User> carregaArquivoUsuarios () {

        ArrayList<User> usuariosArquivo = new ArrayList<>();
        try (BufferedReader buffRead = new BufferedReader(new FileReader("dados.txt"))) {
            String linha;
            while (true) {
                linha = buffRead.readLine();
                if (linha != null) {
                    String array[] = new String[4];
                    array = linha.split(";");
                    User user = new User(array[0],array[1],array[2], array[3], array[4]);

                    String[] array2 = array[3].split(",");

                    ArrayList<Integer> games = new ArrayList<>();

                    for (int i = 0; i < array2.length; i++) {
                        games.add(Integer.parseInt(array2[i]));
                    }

                    user.setJogosUsuario(games);

                    usuariosArquivo.add(user);

                }else{
                    break;
                }
            }

            buffRead.close();
            return usuariosArquivo;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
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


