package com.tecgames.controller;

import com.tecgames.model.Game;
import com.tecgames.model.GameDados;
import com.tecgames.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HomepageClienteController {

    public Label iconLetter;
    public Circle circleIcon;

    public Button logout;
    public Label title;
    public ImageView jogodestaque;
    public Button vermais;

    private Game GameDestaque;

    private User usuarioLogado; //ainda vai ser usado

    public void initData(User usuario) {

        this.usuarioLogado = usuario;
        title.setText("Olá, "+ usuario.getNome());

        char firstCaracter = usuario.getNome().charAt(0);

        iconLetter.setText(String.valueOf(firstCaracter));

        GameDados gameDAO = new GameDados();

        this.GameDestaque = gameDAO.buscarGame(4); // so mudar o id se quiser mudar o jogo destaque, E O JOGO TEM QUE EXISTIR

        String caminho;
        if (GameDestaque == null){
            caminho = "com/tecgames/view/images/empty.png";
        }else{
            caminho = String.format(".\\images/games/%s.jpg", GameDestaque.getId());
        }

        File foto = new File(caminho);
        Image imagem  = new Image(foto.getAbsolutePath() , 647, 393 , false , false);
        jogodestaque.setImage(imagem);
    }

    //button logout
    @FXML
    protected void onLogoutButtonClick() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/tecgames/view/login-view.fxml"));
        Scene scene = new Scene(root, 1000, 600); //cena

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);

    }

    //botao loja
    @FXML
    protected void onLojaButtonClick() throws IOException {
        //carregando estilização da loja
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/loja-view.fxml"));

        Parent lojaView = loader.load();

        Scene lojaViewScene = new Scene(lojaView); // instanciando uma nova cena com a estilização da loja

        LojaController lojaController = loader.getController();

        lojaController.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja


        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(lojaViewScene); //mudando a cena da janela para a loja

    }


    @FXML
    protected void onMeusjogosButtonClick() throws IOException {
        //carregando estilização da loja
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/meusjogos-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização da loja

        MeusJogosController meusjogosController = loader.getController();

        meusjogosController.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja


        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a loja

    }

    @FXML
    protected void onCarrinhoButtonClick() throws IOException {
        //carregando estilização da loja
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/meucarrinho-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização de carrinho

        MeuCarrinhoController Controller = loader.getController();

        Controller.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de carrinho

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a tela de carrinho

    }


    public User getUsuarioLogado() {
        return this.usuarioLogado;
    }



    @FXML
    protected void onVermaisButtonClick() throws IOException {

        //agora vou  fazer o botao ver mais mudar a tela para mostrar as informações do jogo
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/com/tecgames/view/gameinfo-view.fxml"));

        Parent View = loader2.load();


        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização

        GameInfoController Controller = loader2.getController();
        Controller.initData(this.GameDestaque, getUsuarioLogado(),1);//passando o usuario que esta logado para a tela de gameinfo

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a gameinfo


    }

}
