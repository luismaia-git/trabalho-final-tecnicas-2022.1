package com.tecgames.model;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class VendaDados {



    public void inserir(Venda venda) { 
        ArrayList<Venda> vendas = carregaArquivoVendas();
        Venda ultimo = vendas.get(vendas.size() - 1);           // Pega o ultimo elemento do ArrayList de vendas
        venda.setIdvenda(ultimo.getIdvenda() + 1);             // Pega o id do ultimo elemento, soma 1 e seta no ID da venda que será adicionada
        
        Date dh = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dh);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dh);

        venda.setData(data);
        venda.setHora(hora);

        vendas.add(venda);
        escreveArquivoVendas(vendas);
    }


    public ArrayList<Venda> carregaArquivoVendas () {
        ArrayList<Venda> vendaArquivo = new ArrayList<>();
        try (BufferedReader buffRead = new BufferedReader(new FileReader(".\\dados/vendas.txt"))) {
            String linha;
            while (true) {
                linha = buffRead.readLine();

                if (!(linha == null)) {
                    String array[] = linha.split(";");
                    Venda venda = new Venda(array[0], array[1], array[2], array[3], array[4]);

                    String[] array2 = array[5].split(",");

                    ArrayList<Integer> games = new ArrayList<>();

                    for (int i = 0; i < array2.length; i++) {
                        games.add(Integer.parseInt(array2[i]));
                    }

                    venda.setIdjogos(games);

                    vendaArquivo.add(venda);

                }else{
                    break;
                }
            }

            buffRead.close();

        }catch(Exception e){
            System.out.println(e);
        }
        return vendaArquivo;

    }

    public void escreveArquivoVendas(ArrayList<Venda> vendas){
        try { 
            File arq = new File(".\\dados/vendas.txt");
            if (!arq.exists()) {
                arq.createNewFile();
            }

            FileWriter gravador = new FileWriter(arq);
            try (BufferedWriter buffer = new BufferedWriter(gravador)) {
                for (int i = 0; i < vendas.size(); i++) {
                    Venda venda = vendas.get(i);
                    String idjogos = "";
                    if ((venda.getIdjogos().size() > 1)) {
                        for (int j = 0; j < venda.getIdjogos().size() - 1; j++) {
                            idjogos = idjogos + String.valueOf(venda.getIdjogos().get(j)) + ",";
                        }
                    }
                    
                    idjogos = idjogos + venda.getIdjogos().get(venda.getIdjogos().size() - 1);
                    
                    String linha = venda.getIdvenda() + ";" + venda.getIdusuario() + ";" + venda.getValortotal() + ";" + venda.getData() + ";" + venda.getHora() + ";" + idjogos + "\n";
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