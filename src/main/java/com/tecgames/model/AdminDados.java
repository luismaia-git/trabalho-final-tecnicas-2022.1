package com.tecgames.model;

import com.tecgames.model.Admin;

import java.io.*;
import java.util.*;

public class AdminDados {

    public ArrayList<Admin> carregaArquivoAdmin () {

        ArrayList<Admin> adminArquivo = new ArrayList<>();
        try (BufferedReader buffRead = new BufferedReader(new FileReader("src/main/java/com/tecgames/controller/dados/admin.txt"))) {
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
}
