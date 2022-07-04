package com.tecgames.controller;

import com.tecgames.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;

public class CarrinhoCheioController {
    public ScrollPane scrollPane;
    public Pane divcontent2;
    public Label subtotal;
    public Label total;

    private VBox BoxMain;

    private Pane PaneGame;
    private User usuarioLogado;
    private ArrayList<Game> jogos;

    private Carrinho carrinhoUsuario;

    private ArrayList<Carrinho> array_carrinhos;

    private double preco = 0;

    public void initData(User usuarioLogado) throws IOException {
        this.usuarioLogado = usuarioLogado;

        CarrinhoDados carrinhoDAO = new CarrinhoDados();
        array_carrinhos = carrinhoDAO.carregaArquivoCarrinhos();

        GameDados gameDAO = new GameDados();
        ArrayList<Game> array_games = gameDAO.carregaArquivoGames();



        for(int i = 0; i < array_carrinhos.size(); i++) {
            if(usuarioLogado.getId() == array_carrinhos.get(i).getId()){//achei o usuario
                carrinhoUsuario = array_carrinhos.get(i);
            }
        }

        //instanciando o array para preencher com todos os jogos do usuario
        jogos = new ArrayList<>();


        //agora adicionar ao array de jogos, os jogos do usuario
        for (int l =0; l < carrinhoUsuario.getIdjogos().size(); l++){

            for(int j = 0; j < array_games.size(); j++){


                if(carrinhoUsuario.getIdjogos().get(l).equals(array_games.get(j).getId())){
                    jogos.add(array_games.get(j));
                    preco = preco + array_games.get(j).getPreço();
                }

            }
        }


        if(jogos.size() == 0) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/tecgames/view/components/carrinhovazio.fxml"));


            Pane new_pane = loader.load();
            divcontent2.getChildren().setAll(new_pane);

            CarrinhoVazioController controller = loader.getController();

            controller.initData(getUsuarioLogado());

        } else {
            subtotal.setText(String.valueOf(preco));//setando subvalor do carrinho
            total.setText(String.valueOf(preco));//setando total valor do carrinho
            display(jogos);
        }


    }



    public void display(ArrayList<Game> array) throws IOException {

        //instanciando uma VBox
        BoxMain = new VBox();
        BoxMain.setStyle("-fx-background-color: #2B4C7E;");

        BoxMain.setPrefWidth(555);

        BoxMain.setPrefHeight(440);

        BoxMain.setSpacing(25.0);

        //instanciando o primeiro jogo
        PaneGame = new Pane();

        for(int i = 0; i < array.size(); i++) {

            //acess the controller and call a method (initData)

            //carregando a estilização dos cards e guardando o loader para saber o controller dele
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/tecgames/view/components/cardcarrinho.fxml"));


            //passando a estilização
            PaneGame = loader.load();

            CardCarrinhoController controller = loader.getController();

            controller.initData(array.get(i));

            //"estraindo o botao"
            Button botao = controller.getRemover();



            botao.setOnAction(e -> {

                //remove o jogo

                Game jogo = controller.getJogo();

                int k = 0;
                while(true){
                    if(carrinhoUsuario.getIdjogos().get(k) == jogo.getId()){
                        carrinhoUsuario.getIdjogos().remove(k);
                        break;
                    }
                    k = k + 1;
                }


                CarrinhoDados CD = new CarrinhoDados();


                CD.Atualiza(carrinhoUsuario);


                try {
                    initData(usuarioLogado);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            });

            BoxMain.getChildren().add(PaneGame);

            PaneGame = new Pane();
        }


        if(array.size() >= 3) {
            int excedente = array.size() - 3;
            BoxMain.setPrefHeight(440 + (excedente*117));
        }


        //adicionando tudo da Vbox no ScrollPane
        scrollPane.setContent(BoxMain);

    }

    public User getUsuarioLogado() {
        return usuarioLogado;
    }


    @FXML
    protected void onComprarButtonClick() throws IOException {

        Venda venda = new Venda();
        venda.setIdjogos(carrinhoUsuario.getIdjogos());
        venda.setValortotal(preco);
        venda.setIdusuario(usuarioLogado.getId());

        VendaDados vd = new VendaDados();//inserindo a venda no txt
        vd.inserir(venda);

        CarrinhoDados cd2 = new CarrinhoDados();//apagando o carrinho do txt
        cd2.remover_carrinho(carrinhoUsuario);

        usuarioLogado.getJogosUsuario().addAll(carrinhoUsuario.getIdjogos()); //inserindo os jogos novos no usuario comprador

        UsuarioDados ud = new UsuarioDados();

        ud.alterar(usuarioLogado);//alterando/atualizando o usuario no txt

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Carrinho de compra");
        alert.setHeaderText("Compra efetuada com sucesso!");
        alert.setContentText("Clique em ok");
        alert.show();


        //carregando estilização da loja
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/meusjogos-view.fxml"));

        Parent View = loader.load();

        Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização da loja

        MeusJogosController meusjogosController = loader.getController();

        meusjogosController.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja


        //This line gets the Stage(window) information
        Stage window = (Stage) divcontent2.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a loja
    }
}
