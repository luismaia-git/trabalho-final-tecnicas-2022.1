package com.tecgames.model;

import com.tecgames.model.Admin;

import java.io.*;
import java.util.*;

public class AdminDados {

    public ArrayList<Admin> carregaArquivoAdmin () {

        ArrayList<Admin> adminArquivo = new ArrayList<>();
        try (BufferedReader buffRead = new BufferedReader(new FileReader(".\\dados/admin.txt"))) {
            String linha;
            while (true) {
                linha = buffRead.readLine();

                if(linha == null){
                    break;
                }else {
                    String array[] = linha.split(";");
                    Admin adm = new Admin(array[0], array[1],array[2], array[3], array[4], array[5]);
                    adminArquivo.add(adm);
                }
                
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return adminArquivo;
    }

    public boolean inserir(Admin adminnovo) throws Exception{
        ArrayList<Admin> admins;
        admins = carregaArquivoAdmin();

        if (!checaAdmin(adminnovo, admins)) {
            if (admins.size()==0)
                adminnovo.setId(1);
            else {
                Admin ultimo = admins.get(admins.size() - 1);          // Pega o ultimo elemento do ArrayList de users
                adminnovo.setId(ultimo.getId() + 1);            // Pega o id do ultimo elemento, soma 1 e seta no ID do usuario que será adicionado
            }

            admins.add(adminnovo);
            escreveArquivoAdmin(admins);
            return true;
        }
        else
            return false;
    }


    public boolean checaAdmin(Admin adminInput, ArrayList<Admin> admins) {
        int i = 0;

        while (i < admins.size()) {
            Admin admin = admins.get(i);
            if ((admin.getEmail().equals(adminInput.getEmail())) || (admin.getCpf().equals(adminInput.getCpf()))) {
                return true;
            }
            i = i + 1;
        }
        return false;
    }

    public void escreveArquivoAdmin(ArrayList<Admin> adms){
        try {
            File arq = new File(".\\dados/admin.txt");
            if (!arq.exists()) {
                arq.createNewFile();
            }

            FileWriter gravador = new FileWriter(arq);
            try (BufferedWriter buffer = new BufferedWriter(gravador)) {
                for (int i = 0; i < adms.size(); i++) {
                    Admin adm = adms.get(i);

                    String linha = adm.getId() + ";" + adm.getNome() + ";" + adm.getEmail() + ";" + adm.getSenha() + ";" + adm.getCpf() + ";" + adm.getData() + "\n";
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
