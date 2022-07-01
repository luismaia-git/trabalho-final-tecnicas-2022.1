package com.tecgames.controller;

import com.tecgames.model.Game;
import com.tecgames.model.GameDados;
import com.tecgames.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class MeusJogosController implements Initializable {


    public TextField campoTexto;
    private ArrayList<Game> jogos;
    @FXML
    public ScrollPane scrollPane; //scroll
    public Button voltar;
    public Button pesquisar;
    private Pane paneGame; //card game
    private HBox hboxGames;
    private VBox BoxMain;

    private User usuarioLogado;

    public User getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(User usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    // carregar jogos de arquivo etc
    public void initData(User usuarioLogado) throws IOException {
        this.usuarioLogado = usuarioLogado;

        //instanciando o array com todos os jogos
        ArrayList<Integer> jogosId;

        jogosId = usuarioLogado.getJogosUsuario(); //carregando os ids dos jogos do usuario

        jogos = new ArrayList<>(); //criando um array de jogos para ser mostrado na tela

        GameDados g1 = new GameDados();

        ArrayList<Game> aux = g1.carregaArquivoGames(); //carregando todos os jogos da aplicação

        //agora adicionar ao array de jogos, os jogos do usuario
        for (int i =0; i < jogosId.size(); i++){

            for(int j = 0; j < aux.size(); j++){


                if(jogosId.get(i).equals(aux.get(j).getId())){

                    jogos.add(aux.get(j));
                }

            }
        }


        displayMeusJogos(jogos);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    //metodo que recebe um array de jogos e mostra na tela
    public void displayMeusJogos(ArrayList<Game> array) throws IOException{

        int hboxes =0;
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

            Pane painelpreco = gameController.getPainelpreco();
            painelpreco.getChildren().clear();


            //"estraindo o botao"
            Button botao = gameController.getVermais();



            //chamando o metodo initData que passa um game no parametro e seta as informações do card
            gameController.initData(array.get(i));

            //agora vou criar o evento para fazer o botao ver mais mudar a tela para mostrar as informações do jogos
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/com/tecgames/view/gameinfo-view.fxml"));

            Parent View = loader2.load();


            //botao ver mais abre a tela de gameinfo
            botao.setOnAction(e -> {
                //carregando estilização

                Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização

                GameInfoController Controller = loader2.getController();
                Controller.initData(gameController.getJogo(), getUsuarioLogado(), 2);//passando o usuario que esta logado para a tela de gameinfo

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

                if(i == array.size()-1){
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

            if(i == array.size()-1){
                BoxMain.getChildren().add(hboxGames);
            }

            j++;

        }

        //calculo para saber a altura necessaria da VBox para o ScrollPane funcionar de forma eficiente

        if(hboxes >= 3) {
            int excedente = hboxes - 2;
            BoxMain.setPrefHeight(482 + (excedente*200));
        }

        //adicionando tudo da Vbox no ScrollPane
        scrollPane.setContent(BoxMain);

    }


    public ArrayList<Game> buscaTitulo(String titulo) {

        ArrayList<Game> aux = new ArrayList<>();
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getNome().toLowerCase().contains(titulo.toLowerCase()))
                aux.add(jogos.get(i));
        }
        return aux;
    }

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
                if (jogos.get(i).getGenero().toLowerCase().contains(array[h].toLowerCase())) // add genero e getGenero() no model
                    aux.add(jogos.get(i));
            }
        }
        return aux;
    }

    //botoes de eventos

    @FXML
    protected void onBuscaButtonClick() throws IOException {
        String result = campoTexto.getText();
        ArrayList<Game> array = buscaTitulo(result);
        displayMeusJogos(array);
    }

    @FXML
    protected void onBuscaGeneroButtonClick() throws IOException {
        String result = campoTexto.getText();
        ArrayList<Game> array = buscaGenero(result);
        displayMeusJogos(array);
    }

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
        Stage window = (Stage) voltar.getScene().getWindow();

        window.setScene(lojaViewScene); //mudando a cena da janela para a loja

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
        Stage window = (Stage) voltar.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a tela de carrinho

    }
}
