package com.tecgames.controller;

import com.tecgames.model.Game;
import com.tecgames.model.GameDados;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Class CadastroJogoController, controller for the CadastroJogo screen
 * */
public class CadastroJogoController {

    public ImageView image;
    private FileChooser chooser = new FileChooser();
    private File f;
    private File destino;


    public TextField campoNome;
    public TextField campoPreco;
    public TextField campoGenero;
    public TextField campoDescricao;
    public TextField campoData;
    public TextField campoRequisitos;
    public TextField campoFaixa;
    public Button botaoCancelar;
    public Button botaoConfirmar;
    public Button botaoUpload;
    private Stage CadastroJogo;

    private Game jogo;

    private boolean BotaoClicado = false;

    public Stage getCadastroJogo() {
        return CadastroJogo;
    }

    public void setCadastroJogo(Stage cadastroJogo) {
        CadastroJogo = cadastroJogo;
    }

    public Game getJogo() {
        return jogo;
    }

    /**
     * Sets the game in controller, and displays info
     * */
    public void setJogo(Game jogo) {
        this.jogo = jogo;
        this.campoNome.setText(jogo.getNome());
        this.campoPreco.setText(String.valueOf(jogo.getPreço()));
        this.campoGenero.setText(jogo.getGenero());
        this.campoDescricao.setText(jogo.getDescricao());
        this.campoData.setText(jogo.getAnolançamento());
        this.campoRequisitos.setText(jogo.getRequisitos());
        this.campoFaixa.setText(jogo.getClassificacao());

        if(jogo != null){

            //Jogo existe
            //Vou procurar o arquivo da foto

            if(jogo.getId() != 0){
                try{
                    f = new File("images/games/"+jogo.getId()+".jpg");
                } catch (Exception evv) {}
            }


            //se o arquivo da foto existe, entao eu mostro
            if(f != null){
                Image m2 = new Image( f.getAbsolutePath(), 220, 145 ,false, false);
                image.setImage(m2);
            }else{ //se nao existe, entao mostro que está vazio
                Image m3 = new Image( "com/tecgames/view/images/empty.png", 220, 145 ,false, false);
                image.setImage(m3);
            }

        }
    }

    /**
     * confirm Button is clicked
     * */
    public boolean isBotaoClicado() {
        return BotaoClicado;
    }

    public void setBotaoClicado(boolean botaoClicado) {
        BotaoClicado = botaoClicado;
    }


    /**
     * if confirm Button is clicked, inserts Game in "database" games
     * */
    @FXML
    protected void onConfirmarButtonClick() throws IOException {
        //se todos os campos estão preenchidos
        if(validarEntradaDeDados()){
            jogo.setNomeGame(campoNome.getText());
            jogo.setPreço(Double.valueOf(campoPreco.getText()));
            jogo.setGenero(campoGenero.getText());
            jogo.setDescricao(campoDescricao.getText());
            jogo.setAnolançamento(campoData.getText());
            jogo.setRequisitos(campoRequisitos.getText());
            jogo.setClassificacao(campoFaixa.getText());

            GameDados gd = new GameDados();
            ArrayList<Game> games = gd.carregaArquivoGames();


            if(jogo.getId() == 0){
                if(games.size() == 0) {
                    jogo.setId(1);
                } else {
                    jogo.setId(games.get(games.size() - 1).getId()+1);                     // Pega o id do ultimo elemento, soma 1 e seta no ID do jogo que será adicionado
                }
            }



            //String extensao = getFileExtension(f);
            File destinoFile = new File(".\\images/games/" + String.valueOf(jogo.getId())+".jpg");

            destino = destinoFile.getAbsoluteFile();

            Files.copy(f.toPath(), destino.toPath(), REPLACE_EXISTING);


            BotaoClicado = true;
            CadastroJogo.close();
        }

    }
    /**
     * Close the window CadastroJogo
     * */
    @FXML
    protected void onCancelarButtonClick(){
        CadastroJogo.close();
    }

    /**
     * Upload image buttom
     * */
    @FXML
    protected void onUploadButtonClick(){
        File f2 = f;
        f = chooser.showOpenDialog(null); //selecionando uma foto do jogo
        if(f == null) {
            f = f2;
        }
        try{
            Image imagem = new Image(f.getPath(), 220, 145 , false ,false);
            image.setImage(imagem);
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Você não selecionou uma imagem!");
            alert.show();
        }

    }

    /**
     * Valid data entry for the form
     * */
    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (campoNome.getText() == null || campoNome.getText().length() == 0) errorMessage += "Nome inválido!\n";

        if (campoPreco.getText() == null || campoPreco.getText().length() == 0) errorMessage += "Preço inválido!\n";

        if (campoData.getText() == null || campoData.getText().length() == 0) errorMessage += "Data inválida!\n";

        if (campoDescricao.getText() == null || campoDescricao.getText().length() == 0) errorMessage += "Descrição inválida!\n";

        if (campoGenero.getText() == null || campoGenero.getText().length() == 0) errorMessage += "Gênero inválido!\n";

        if (campoFaixa.getText() == null || campoFaixa.getText().length() == 0) errorMessage += "Classificação etária inválida!\n";

        if (campoRequisitos.getText() == null || campoRequisitos.getText().length() == 0) errorMessage += "Requisitos inválidos!\n";

        if(f == null) errorMessage += "Imagem inválida!\n";

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
