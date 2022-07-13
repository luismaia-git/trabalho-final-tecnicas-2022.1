package com.tecgames.model;

import java.io.*;
import java.util.ArrayList;

/**
 * Class model CarrinhoDados, contains the "connections" to the "database" carrinhos
 * */
public class CarrinhoDados {

    /**
     * Constructor for class CarrinhoDados
     * */
    public CarrinhoDados(){}

    /**
     * inserts into "db' admin
     * */
    public boolean inserir(Carrinho carrinho) {
        ArrayList<Carrinho> carrinhos = carregaArquivoCarrinhos();
        carrinhos.add(carrinho);
        escreveArquivoCarrinhos(carrinhos);
        return true;
    }


    /**
     * removes Carrinho in "db' carrinhos
     * */
    public boolean remover_carrinho(Carrinho carrinho){
        ArrayList<Carrinho> carrinhos;

        carrinhos = carregaArquivoCarrinhos();
        Carrinho aux = retornaCarrinho(carrinho, carrinhos);
        if (!aux.equals(null)) {
            carrinhos.remove(aux);
            escreveArquivoCarrinhos(carrinhos);
            return true;
        }
        else
            return false;
    }

    /**
     * checks on "database" if Carrinho exists
     * */
    public Carrinho retornaCarrinho(Carrinho carrinho, ArrayList<Carrinho> carrinhos) {
        int i = 0;
        while (i < carrinhos.size()) {
            Carrinho c = carrinhos.get(i);
            if ((carrinho.getId() == c.getId()))
                return c;

            i = i + 1;
        }

        return null;
    }

    /**
     * loads the contents of the carrinhos database
     * */
    public ArrayList<Carrinho> carregaArquivoCarrinhos () {
        ArrayList<Carrinho> carrinhoArquivo = new ArrayList<>();

        try (BufferedReader buffRead = new BufferedReader(new FileReader("dados/carrinhos.txt"))) {
            String linha;
            while (true) {
                linha = buffRead.readLine();

                if (!(linha == null)) {
                    String array[] = linha.split(";");
                    Carrinho carrinho = new Carrinho(array[0]);

                    String[] array2 = array[1].split(",");

                    ArrayList<Integer> games = new ArrayList<>();

                    for (int i = 0; i < array2.length; i++) {
                        games.add(Integer.parseInt(array2[i]));
                    }

                    carrinho.setIdjogos(games);

                    carrinhoArquivo.add(carrinho);

                }else{
                    break;
                }
            }

            buffRead.close();

        }catch(Exception e){
            System.out.println(e);
        }

        return carrinhoArquivo;

    }


    /**
     * writes in database of carrinhos
     * */
    public void escreveArquivoCarrinhos(ArrayList<Carrinho> carrinhos){
        try {
            File arq = new File(".\\dados/carrinhos.txt");
            if (!arq.exists()) {
                arq.createNewFile();
            }

            FileWriter gravador = new FileWriter(arq);
            try (BufferedWriter buffer = new BufferedWriter(gravador)) {
                for (int i = 0; i < carrinhos.size(); i++) {
                    Carrinho carrinho = carrinhos.get(i);
                    String idjogos = "";
                    if ((carrinho.getIdjogos().size() > 1)) {
                        for (int j = 0; j < carrinho.getIdjogos().size() - 1; j++) {
                            idjogos = idjogos + String.valueOf(carrinho.getIdjogos().get(j)) + ",";
                        }
                        idjogos = idjogos + carrinho.getIdjogos().get(carrinho.getIdjogos().size() - 1);
                    }

                    if (carrinho.getIdjogos().size() == 1)
                        idjogos = idjogos + carrinho.getIdjogos().get(carrinho.getIdjogos().size() - 1);

                    String linha = carrinho.getId() + ";" + idjogos + "\n";
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

    /**
     * updates Carrinho in "db' carrinhos
     * */
    public void Atualiza(Carrinho ca_user) {
        ArrayList<Carrinho> carrinhos;

        carrinhos = carregaArquivoCarrinhos();

        if(ca_user.getIdjogos().size() == 0) {
            remover_carrinho(ca_user);
        } else {
            int i = 0;
            while (i < carrinhos.size()) {
                Carrinho carrinho = carrinhos.get(i);
                if ((ca_user.getId() == carrinho.getId())) {
                    carrinho.setIdjogos(ca_user.getIdjogos());
                    break;
                }

                i = i + 1;
            }
            escreveArquivoCarrinhos(carrinhos);
        }

    }
}
