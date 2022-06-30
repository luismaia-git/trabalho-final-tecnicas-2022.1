import java.io.*;
import java.util.*;

public class VendaDados {

    public void inserir(Venda venda) { 
        ArrayList<Venda> vendas = carregaArquivoVendas();
        Venda ultimo = vendas.get(vendas.size() - 1);           // Pega o ultimo elemento do ArrayList de vendas
        venda.setIdvenda(ultimo.getIdvenda() + 1);           // Pega o id do ultimo elemento, soma 1 e seta no ID da venda que será adicionada
        vendas.add(venda);
        escreveArquivoVendas(vendas);
    }


    public ArrayList<Venda> carregaArquivoVendas () {
        ArrayList<Venda> vendaArquivo = new ArrayList<>();
        // "src/main/java/com/tecgames/controller/dados/carrinhos.txt"
        try (BufferedReader buffRead = new BufferedReader(new FileReader("vendas.txt"))) {
            String linha;
            while (true) {
                linha = buffRead.readLine();

                if (!(linha == null)) {
                    String array[] = linha.split(";");
                    Venda venda = new Venda(array[0], array[1]);

                    String[] array2 = array[2].split(",");

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
            // "src/main/java/com/tecgames/controller/dados/carrinhos.txt"
            File arq = new File("vendas.txt");
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
                    String linha = venda.getIdvenda() + ";" + venda.getIdusuario() + ";" + idjogos + "\n";
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