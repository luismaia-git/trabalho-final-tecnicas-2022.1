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

public class CriarContaAdminController {

    private Admin adminLogado;
    public Button criar;
    public Button cancelar;
    public TextField fieldnome;
    public TextField fieldemail;
    public TextField fieldsenha;
    public TextField fielddata;
    public TextField fieldcpf;

    public Admin getAdminLogado() {
        return adminLogado;
    }

    public void initData(Admin adminLogado){
        this.adminLogado = adminLogado;
    }
    @FXML
    protected void onCancelarButtonClick() throws IOException {
        telahome();//muda pra tela de login
    }

    @FXML
    protected void onConfirmarButtonClick() throws Exception {
        if(validaCampos()){

            Admin adminnovo = new Admin();

            AdminDados adminDAO = new AdminDados();
            UsuarioDados usuarioDAO = new UsuarioDados();

            ArrayList<User> ArquivoUsuario = usuarioDAO.carregaArquivoUsuarios();

            //checando se o email ou cpf do novo usuario existe no banco de dados de usuarios
            boolean result = false;

            boolean existe_usuario = false;

            int i = 0;
            while(i < ArquivoUsuario.size()){
                if(fieldemail.getText().equals(ArquivoUsuario.get(i).getEmail()) || fieldcpf.getText().equals(ArquivoUsuario.get(i).getCpf())){
                    existe_usuario = true;
                    break;
                }
                i = i + 1;
            }

            if(!existe_usuario){
                adminnovo.setCpf(fieldcpf.getText());
                adminnovo.setEmail(fieldemail.getText());
                adminnovo.setNome(fieldnome.getText());
                adminnovo.setSenha(fieldsenha.getText());
                adminnovo.setData(fielddata.getText());

                result = adminDAO.inserir(adminnovo); // tenta inserir, se o email ou cpf ja existirem em admin.txt, retorna false
            }

            if(!result){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro no cadastro");
                alert.setHeaderText("Campos inválidos, por favor, corrija...");
                alert.setContentText("Email ou Cpf ja cadastrados");
                alert.show();
            } else {
                telahome(); //muda pra tela de login
            }
        }
    }

    public void telahome() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/homeadmin-view.fxml"));
        Parent View = loader.load();

        Scene ViewScene = new Scene(View);

        //acess the controller and call a method (initData)
        HomepageAdminController Controller = loader.getController();
        Controller.initData(getAdminLogado());

        Stage window = (Stage) criar.getScene().getWindow();

        window.setScene(ViewScene);
    }

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
