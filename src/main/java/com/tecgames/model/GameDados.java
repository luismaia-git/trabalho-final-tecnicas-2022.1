package com.tecgames.model;

import java.io.*;
import java.util.*;


/**
 * Class model GameDados, contains the "connections" to the "database" games
 * */
public class GameDados {

    /**
     * Constructor for class GameDados
     * */
    public GameDados(){}

    public ArrayList<Game> listar() {
        ArrayList<Game> games;

        games = carregaArquivoGames();
        return games;
    }

    /**
     * inserts into "db' games
     * */
    public boolean inserir(Game game) throws IOException {
        ArrayList<Game> games;
        games = carregaArquivoGames();

            if (!checaGame(game, games)) {
                games.add(game);
                escreveArquivoGames(games);   // Atualiza o banco de dados dos jogos
                return true;
            }
            else
                return false;


    }

    /**
     * checks on "database" if Game exists
     * */
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


    /**
     * removes Game in "db' games
     * */
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


    /**
     * return Game if Game exists in "db' games
     * */
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

    /**
     * searches Game by id in "db' games
     * */
    public Game buscarGame(int id) {
        int i = 0;
        ArrayList<Game> games = carregaArquivoGames();
        while (i < games.size()) {
            if (games.get(i).getId() == id)
                return games.get(i);
            i = i + 1;
        }
        return null;
    }

    /**
     * updates Game in "db' games
     * */
    public boolean alterar(Game game) {
        ArrayList<Game> games = carregaArquivoGames();
        int i = 0;
        while(true){
            if(games.get(i).getId() == game.getId()) {
                games.set(i, game);
                escreveArquivoGames(games);
                return true;
            }
            i++;
        }
    }


    /**
     * loads the contents of the games database
     * */
    public ArrayList<Game> carregaArquivoGames () {
        ArrayList<Game> gamesArquivo = new ArrayList<>();
        
        try (BufferedReader buffRead = new BufferedReader(new FileReader(".\\dados/games.txt"))) {
            String linha;
            while (true) {
                linha = buffRead.readLine();
                if (linha != null) {
                    String array[] = linha.split(";");
                    Game game = new Game(array[0], array[1], array[2], array[3],  array[4],  array[5],  array[6], array[7]);
                    gamesArquivo.add(game);

                }else{
                    break;
                }
            }


        }catch(Exception e){
            System.out.println(e);
        }

        return gamesArquivo;

    }

    /**
     * writes in database of games
     * */
    public void escreveArquivoGames(ArrayList<Game> games){
        try {
            File arq = new File(".\\dados/games.txt");
            if (!arq.exists()) {
                arq.createNewFile();
            }

            FileWriter gravador = new FileWriter(arq);
            try (BufferedWriter buffer = new BufferedWriter(gravador)) {
                for (int i = 0; i < games.size(); i++) {
                    Game game = games.get(i);
                    String linha = game.getId() + ";" + game.getNome() + ";" + game.getPreço() + ";" + game.getGenero() + ";" + game.getDescricao() + ";" + game.getAnolançamento() + ";" + game.getRequisitos() + ";" + game.getClassificacao() + "\n";
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
