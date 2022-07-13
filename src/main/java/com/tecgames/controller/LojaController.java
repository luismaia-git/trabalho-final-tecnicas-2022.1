package com.tecgames.controller;

import com.tecgames.model.Game;
import com.tecgames.model.GameDados;
import com.tecgames.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;

/** Class LojaController, controller for the store screen */
public class LojaController {
    public ArrayList<Game> jogos; //JogosDados.listar() // arraylist games <-- carregar

    @FXML
    public ScrollPane scrollPane; //scroll
    public Button voltar;
    public Button pesquisar;
    public TextField campoTexto;

    private Pane paneGame; //card game
    private HBox hboxGames;
    private VBox BoxMain;

    private User usuarioLogado;


    /** Returns the logged user */
    public User getUsuarioLogado() {
        return usuarioLogado;
    }

    /** Sets user */
    public void setUsuarioLogado(User usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    /** Sets controller initial data */
    public void initData(User usuarioLogado) throws IOException {
        this.usuarioLogado = usuarioLogado;

        //instanciando o array com todos os jogos

        //criando um array de jogos para ser mostrado na tela

        GameDados g1 = new GameDados();

        jogos = g1.carregaArquivoGames(); //carregando todos os jogos da aplicação

        displayJogos(jogos);

    }


    //metodo que recebe um array de jogos e mostra na tela
    /** Sets controller initial data */
    public void displayJogos(ArrayList<Game> array) throws IOException{
        int hboxes = 0;
        //instanciando uma VBox
        BoxMain = new VBox();
        BoxMain.setStyle("-fx-background-color: #2B4C7E;");

        BoxMain.setPrefWidth(727);

        BoxMain.setPrefHeight(482);

        BoxMain.setSpacing(20.0);

        //instanciando a primeira HBox
        hboxGames = new HBox();
        hboxGames.setStyle("-fx-background-color: #2B4C7E;");
        hboxGames.setSpacing(20.0);
        hboxGames.setPrefWidth(721);
        hboxGames.setPrefHeight(198);
        hboxes++;


        int j = 1;
        for(int i = 0; i < array.size(); i++){

            //acess the controller and call a method (initData)

            //carregando a estilização dos cards e guardando o loader para saber o controller dele
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/tecgames/view/components/cardgame.fxml"));

            //criando o card do game
            paneGame = new Pane();

            //passando a estilização
            paneGame = loader.load();


            //instanciando um card controller passando o controller da estilização feita anteriormente
            CardGameController gameController = loader.getController();


            //chamando o metodo initData que passa um game no parametro e seta as informações do card
            gameController.initData(array.get(i));

            //agora vou criar o evento para fazer o botao ver mais mudar a tela para mostrar as informações do jogos
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/com/tecgames/view/gameinfo-view.fxml"));

            Parent View = loader2.load();

            //"estraindo o botao"
            Button botao = gameController.getVermais();


            //botao ver mais abre a tela de gameinfo
            botao.setOnAction(e -> {
                //carregando estilização

                Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização

                GameInfoController Controller = loader2.getController();
                Controller.initData(gameController.getJogo(), getUsuarioLogado(),1);//passando o usuario que esta logado para a tela de gameinfo

                //This line gets the Stage(window) information
                Stage window = (Stage) voltar.getScene().getWindow();

                window.setScene(ViewScene); //mudando a cena da janela para a gameinfo

            });



            //adicionando o card game no Hbox
            hboxGames.getChildren().add(paneGame);


            //se j = 4 é pq tem 4 jogos lidos na HBox, entao preciso instanciar uma nova
            if(j == 4){
                //antes de instanciar uma nova Hbox, preciso salvar a atual com os 4 jogos lidos, adicionando a Hbox na Vbox.
                BoxMain.getChildren().add(hboxGames);

                if(i == array.size()-1){ // se to no ultimo jogo nao preciso criar uma nova hbox
                    break;
                }

                //criando uma nova hbox
                hboxGames = new HBox();
                hboxGames.setStyle("-fx-background-color: #2B4C7E;");
                hboxGames.setSpacing(20.0);
                hboxGames.setPrefWidth(721);
                hboxGames.setPrefHeight(198);
                hboxes++;

                j=0;//zerando o "contador de jogos"

            }
            // i= contador do array do jogos , j = contador (controlador) do jogos
            if(i == array.size()-1){
                BoxMain.getChildren().add(hboxGames);
            }



            j++;

        }

        //tamanho da hboxs = 218

        if(hboxes >= 3) {
            int excedente = hboxes - 2;
            BoxMain.setPrefHeight(482 + (excedente*200));
        }


        //adicionando tudo da Vbox no ScrollPane
        scrollPane.setContent(BoxMain);

    }

    /** Returns a user that contains the String (game title) of the parameter */
    public ArrayList<Game> buscaTitulo(String titulo) {

        ArrayList<Game> aux = new ArrayList<>();
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getNome().toLowerCase().contains(titulo.toLowerCase()))
                aux.add(jogos.get(i));
        }
        return aux;
    }

    /** Returns a user that contains the String (game genre) of the parameter */
    public ArrayList<Game> buscaGenero(String genero) { // a String genero pode ser gerada ao clicar o botao do genero escolhido
        
        ArrayList<Game> aux = new ArrayList<>();
        genero = genero.replace(" e ", " ");
        genero = genero.replace("jogo", "");
        genero = genero.replace(" de ", "");
        genero = genero.replace(",", "");
        genero = genero.replace("/", " ");
    
        String array[] = genero.split(" ");

        for (int h = 0; h < array.length; h++) {
            for (int i = 0; i < jogos.size(); i++) {
                if (jogos.get(i).getGenero().toLowerCase().contains(array[h].toLowerCase()))
                    aux.add(jogos.get(i));
            }
        }
        
        return aux;
    }

    //botoes de eventos

    /** Button to search games */
    @FXML
    protected void onBuscaButtonClick() throws IOException {
        String result = campoTexto.getText();
        ArrayList<Game> array = buscaTitulo(result);
        displayJogos(array);
    }

    /** Button to search games by genre */
    @FXML
    protected void onBuscaGeneroButtonClick() throws IOException {
        String result = campoTexto.getText();
        ArrayList<Game> array = buscaGenero(result);
        displayJogos(array);
    }

    /** Button to return to the home screen */
    @FXML
    protected void onVoltarButtonClick() throws IOException {
        //carregando estilização
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/homecliente-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização

        HomepageClienteController Controller = loader.getController();
        Controller.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de home

        //This line gets the Stage(window) information
        Stage window = (Stage) voltar.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a home
    }

    /** Button to go to cart */
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
        Stage window = (Stage) voltar.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a tela de carrinho

    }

    /** Button to go to user games */
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
        Stage window = (Stage) pesquisar.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a loja

    }

}