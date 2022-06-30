package com.tecgames.model;

import java.io.*;
import java.util.*;

public class GameDados {

    public ArrayList<Game> listar() {
        ArrayList<Game> games;

        games = carregaArquivoGames();
        return games;
    }

    public boolean inserir(Game game) throws IOException {
        ArrayList<Game> games;
        games = carregaArquivoGames();

        if (!checaGame(game, games)) {
            Game ultimo = games.get(games.size() - 1);           // Pega o ultimo elemento do ArrayList de jogos
            game.setId(ultimo.getId() + 1);                     // Pega o id do ultimo elemento, soma 1 e seta no ID do jogo que será adicionado
            games.add(game);
            escreveArquivoGames(games);                       // Atualiza o banco de dados dos jogos
            return true;
        }
        else
            return false;
        
    }

    public boolean checaGame(Game game, ArrayList<Game> games) {
        int i = 0;
        while (i < games.size()) {
            Game g = games.get(i);
            if ((game.getNome().equals(g.getNome())))
                return true;
            
            i = i + 1;
        }
        
        return false;
    }

    public boolean remover(Game game) throws IOException {
        ArrayList<Game> games;

        games = carregaArquivoGames();
        Game aux = retornaGame(game, games);
        if (!aux.equals(null)) {
             games.remove(aux);
            escreveArquivoGames(games);
            return true;
        }
        else
            return false;
    }

    public Game retornaGame(Game game, ArrayList<Game> games) {
        int i = 0;
        while (i < games.size()) {
            Game g = games.get(i);
            if ((game.getNome().equals(g.getNome())))
                return g;
            
            i = i + 1;
        }
        
        return null;
    }

    public boolean alterar(ArrayList<Game> Games) {
        escreveArquivoGames(Games);
        return true;
    }

    public ArrayList<Game> carregaArquivoGames () {
        ArrayList<Game> gamesArquivo = new ArrayList<>();
        
        try (BufferedReader buffRead = new BufferedReader(new FileReader("src/main/java/com/tecgames/controller/dados/games.txt"))) {
            String linha;
            while (true) {
                linha = buffRead.readLine();
                if (linha != null) {
                    String array[] = linha.split(";");
                    Game game = new Game(array[0], array[1], array[2], array[3],  array[4],  array[5],  array[6]);
                    gamesArquivo.add(game);

                }else{
                    break;
                }
            }

            buffRead.close();

        }catch(Exception e){
            System.out.println(e);
        }

        return gamesArquivo;

    }

    public void escreveArquivoGames(ArrayList<Game> games){
        try {
            File arq = new File("src/main/java/com/tecgames/controller/dados/games.txt");
            if (!arq.exists()) {
                arq.createNewFile();
            }

            FileWriter gravador = new FileWriter(arq);
            try (BufferedWriter buffer = new BufferedWriter(gravador)) {
                for (int i = 0; i < games.size(); i++) {
                    Game game = games.get(i);
                    String linha = game.getId() + ";" + game.getNome() + ";" + game.getPreço() + ";" + game.getGenero() + "\n";
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
