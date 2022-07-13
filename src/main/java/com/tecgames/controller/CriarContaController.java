package com.tecgames.controller;

import com.tecgames.model.Admin;
import com.tecgames.model.AdminDados;
import com.tecgames.model.User;
import com.tecgames.model.UsuarioDados;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Class CriarContaController, controller for the CriarConta screen
 * */
public class CriarContaController {
    public Button criar;
    public Button cancelar;
    public TextField fieldnome;
    public TextField fieldemail;
    public TextField fieldsenha;
    public TextField fielddata;
    public TextField fieldcpf;

    /**
     * to return to the login screen
     * */
    @FXML
    protected void onCancelarButtonClick() throws IOException {
        telalogin();//muda pra tela de login
   }

    /**
     * create an account
     * */
    @FXML
    protected void onConfirmarButtonClick() throws IOException {
        //se todos os campos estao preenchidos
        if(validaCampos()){
            User usuarionovo = new User();

            AdminDados adminDAO = new AdminDados();
            UsuarioDados usuarioDAO = new UsuarioDados();

            ArrayList<Admin> ArquivoAdmin = adminDAO.carregaArquivoAdmin();


            boolean result = false;
            boolean existe_admin = false;

            int i = 0;
            while(i < ArquivoAdmin.size()){//checando se o email ou cpf do novo usuario existe no banco de dados de administradores
                if(fieldemail.getText().equals(ArquivoAdmin.get(i).getEmail()) || fieldcpf.getText().equals(ArquivoAdmin.get(i).getCpf())){
                    existe_admin = true;
                    break;
                }
                i = i +1;
            }

            if(!existe_admin){
                usuarionovo.setCpf(fieldcpf.getText());
                usuarionovo.setEmail(fieldemail.getText());
                usuarionovo.setNome(fieldnome.getText());
                usuarionovo.setSenha(fieldsenha.getText());
                usuarionovo.setData(fielddata.getText());

                result = usuarioDAO.inserir(usuarionovo); // tenta inserir, se o email ou cpf ja existirem em usuarios.txt, retorna false
            }

            if(!result){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro no cadastro");
                alert.setHeaderText("Campos inválidos, por favor, corrija...");
                alert.setContentText("Email ou Cpf ja cadastrados");
                alert.show();
            } else {
                telalogin(); //muda pra tela de login
            }
        }
    }

    /**
     * to return to the login screen
     * */
    public void telalogin() throws IOException {
        //carregando estilização
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/login-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização
        //This line gets the Stage(window) information
        Stage window = (Stage) criar.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela
    }

    /**
     * Valid data entry for the form
     * */
    public boolean validaCampos(){

        String errorMessage = "";

        if (fieldnome.getText() == null || fieldnome.getText().length() == 0) errorMessage += "Preencha o campo Nome!\n";

        if (fieldemail.getText() == null || fieldemail.getText().length() == 0) errorMessage += "Preencha o campo Email!\n";

        if (fieldsenha.getText() == null || fieldsenha.getText().length() == 0) errorMessage += "Preencha o campo Senha!\n";

        if (fielddata.getText() == null || fielddata.getText().length() == 0) errorMessage += "Preencha o campo Data de nascimento!\n";

        if (fieldcpf.getText() == null || fieldcpf.getText().length() == 0) errorMessage += "Preencha o campo CPF!\n";


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
