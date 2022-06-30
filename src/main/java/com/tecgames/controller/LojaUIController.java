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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;

public class LojaUIController implements Initializable {
    public ArrayList<Game> jogos; //JogosDados.listar() // arraylist games <-- carregar

    @FXML
    public ScrollPane scrollPane; //scroll
    public Button voltar;
    public Button pesquisar;

    private Pane paneGame; //card game
    private HBox hboxGames;
    private VBox BoxMain;



    private User usuarioLogado;


    public void initData(User usuarioLogado) throws IOException {
        this.usuarioLogado = usuarioLogado;

        //instanciando o array com todos os jogos

        //criando um array de jogos para ser mostrado na tela

        GameDados g1 = new GameDados();

        jogos = g1.carregaArquivoGames(); //carregando todos os jogos da aplicação

        displayJogos(jogos);


    }

     // carregar jogos de arquivo
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    //metodo que recebe um array de jogos e mostra na tela
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

            if(i == jogos.size()-1){
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

    public ArrayList<Game> buscaTitulo(String titulo) {

        ArrayList<Game> aux = new ArrayList<>();
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getNome().contains(titulo))
                aux.add(jogos.get(i));
        }
        return aux;
    }

    public ArrayList<Game> buscaGenero(String genero) { // a String genero pode ser gerada ao clicar o botao do genero escolhido

        ArrayList<Game> aux = new ArrayList<>();
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getGenero().contains(genero)) // add genero e getGenero() no model
                aux.add(jogos.get(i));
        }
        return aux;
    }

    public User getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(User usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }



    //botoes de eventos


    //busca pelo nome ( campo de texto )
    @FXML
    protected void onBuscaButtonClick()  {
        // result = CampodeTexto.getText()
        // buscanome(result)
        //display();
    }


    //botao que abre as opçõs de genero
    @FXML
    protected void onBuscaGeneroButtonClick()  {

        //abre uma tela com bottoes com generos e vc escolhe
    }


    //botao pra escolher o genero
    @FXML
    protected void onGeneroButtonClick()  {

        // resultgenero = get do botao escolhido do genero

        // buscagenero(resultgenero)

        //fechar a janela e
        //displayJogos();
    }

    @FXML
    protected void onVoltarButtonClick() throws IOException {
        //carregando estilização
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/homecliente-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização

        //+estilizações com css
        ViewScene.getStylesheets().add(getClass().getResource("/com/tecgames/view/css/homecliente.css").toExternalForm());


        HomePageClienteController Controller = loader.getController();
        Controller.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de home


        //This line gets the Stage(window) information
        Stage window = (Stage) voltar.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a home
    }

    @FXML
    protected void OnCarrinhoButtonClick(){
        System.out.println(getUsuarioLogado().getNome());

        //array = buscar(user)
        //if(array for nulo) instancio a tela carrinho vazio
        //else instancio a a tela de carrinho
        //
    }


    @FXML
    protected void onVerMaisButtonClick() throws IOException {



    }

    @FXML
    protected void onMeusjogosButtonClick() throws IOException {
        //carregando estilização da loja
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/meusjogos-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização da loja

        //+estilizações com css
        ViewScene.getStylesheets().add(getClass().getResource("/com/tecgames/view/css/meusjogos.css").toExternalForm());

        MeusjogosController meusjogosController = loader.getController();

        meusjogosController.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja


        //This line gets the Stage(window) information
        Stage window = (Stage) pesquisar.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a loja

    }


}
