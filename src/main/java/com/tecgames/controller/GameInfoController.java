package com.tecgames.controller;

import com.tecgames.model.Carrinho;
import com.tecgames.model.CarrinhoDados;
import com.tecgames.model.Game;
import com.tecgames.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/** Class GameInfoController, controller for the info games */
public class GameInfoController {

    public HBox divheader;
    public Label nomejogo;
    public Label descricaojogo;
    public ImageView imagem;
    public Label genero;
    public Label anolancamento;
    public Label requisitos;
    public Label preco;
    public Button adicionarcarrinho;
    public Label title;

    private User usuarioLogado;
    private Game jogoEscolhido;
    private int valueTela;// se 1 entao eu entrei na tela de gameinfo apartir da tela de loja, se 2 entao foi da tela meus jogos

    boolean have_game;

    /** Returns the logged user */
    public User getUsuarioLogado() {
        return this.usuarioLogado;
    }

    /** Sets controller initial data */
    public void initData(Game jogo, User usuario, int valor) {
        this.usuarioLogado = usuario;
        this.jogoEscolhido = jogo;
        this.valueTela = valor;

        nomejogo.setText(jogo.getNome());
        descricaojogo.setText(jogo.getDescricao());
        genero.setText(jogo.getGenero());
        anolancamento.setText(jogo.getAnolançamento());
        requisitos.setText(jogo.getRequisitos());
        preco.setText(String.valueOf(jogo.getPreço()));

        String caminho = String.format(".\\images/games/%s.jpg", jogo.getId());
        File foto = new File(caminho);
        Image imageminput  = new Image(foto.getAbsolutePath() , 412, 220 , false , false);
        imagem.setImage(imageminput);


        have_game = false;

        for(int i=0; i < usuarioLogado.getJogosUsuario().size();i++){

            if(usuarioLogado.getJogosUsuario().get(i).equals(jogoEscolhido.getId())){//verifico se o usuario ja tem o jogo
                have_game = true;
            }
        }

        if(have_game) {//se ele ja tem o jogo, ele nao pode adicionar no carrinho
            adicionarcarrinho.setText("Ja comprado");
            adicionarcarrinho.setOnAction( e -> {
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Informação");
                dialogoInfo.setHeaderText("Você ja tem esse jogo!");
                dialogoInfo.setContentText("Clique em ok");
                dialogoInfo.showAndWait();
            });
        } else {//se ele nao tem, entao pode adicionar no carrinho
            adicionarcarrinho.setOnAction( e -> {
                CarrinhoDados carrinhoDAO = new CarrinhoDados();

                ArrayList<Carrinho> array_ca = carrinhoDAO.carregaArquivoCarrinhos();

                int j=0;
                while(j < array_ca.size()) {

                    if(usuarioLogado.getId() == array_ca.get(j).getId()){ //verifica se tem o carrinho no arquivo

                        if(array_ca.get(j).getIdjogos().contains(jogoEscolhido.getId())){ // usuario ja tem o jogo no carrinho
                            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                            dialogoInfo.setTitle("Informação");
                            dialogoInfo.setHeaderText("Este jogo ja está no carrinho");
                            dialogoInfo.setContentText("Clique em ok");
                            dialogoInfo.showAndWait();

                        }else{//usuario nao tem o jogo no carrinho
                            array_ca.get(j).getIdjogos().add(jogoEscolhido.getId());
                            carrinhoDAO.escreveArquivoCarrinhos(array_ca);
                            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                            dialogoInfo.setTitle("Informação");
                            dialogoInfo.setHeaderText("Jogo adicionado ao carrinho com sucesso!");
                            dialogoInfo.setContentText("Clique em ok");
                            dialogoInfo.showAndWait();
                        }

                        break;
                    }
                    j = j +1;
                }
                if(j== array_ca.size()) { // add uma linha no carrinhos.txt
                    Carrinho ca = new Carrinho();
                    ca.setId(usuarioLogado.getId());

                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(jogoEscolhido.getId());

                    ca.setIdjogos(temp);
                    carrinhoDAO.inserir(ca);//inserindo no carrinho
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Informação");
                    dialogoInfo.setHeaderText("Jogo adicionado ao carrinho com sucesso!");
                    dialogoInfo.setContentText("Clique em ok");
                    dialogoInfo.showAndWait();
                }


            });
        }

    }


    /** Button to return to the home screen */
    @FXML
    protected void onVoltarButtonClick() throws IOException {

        //carregando estilização
        FXMLLoader loader = new FXMLLoader();

        //This line gets the Stage(window) information
        Stage window = (Stage) divheader.getScene().getWindow();

        if(this.valueTela == 1) {// se valueTela = 1 muda pra tela da loja, se nao, tela de meus jogos
            loader.setLocation(getClass().getResource("/com/tecgames/view/loja-view.fxml"));

            Parent View = loader.load();

            Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização

            LojaController lojaController = loader.getController();
            lojaController.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja

            window.setScene(ViewScene); //mudando a cena da janela para a loja
        } else {
            loader.setLocation(getClass().getResource("/com/tecgames/view/meusjogos-view.fxml"));

            Parent View = loader.load();

            Scene ViewScene = new Scene(View); // instanciando uma nova cena com a estilização

            //+estilizações com css
            ViewScene.getStylesheets().add(getClass().getResource("/com/tecgames/view/css/meusjogos.css").toExternalForm());

            MeusJogosController meusjogosController = loader.getController();
            meusjogosController.initData(getUsuarioLogado());//passando o usuario que esta logado para a tela de loja

            window.setScene(ViewScene); //mudando a cena da janela para a loja
        }


    }

    /** Button to open the cart screen */
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
        Stage window = (Stage) title.getScene().getWindow();

        window.setScene(ViewScene); //mudando a cena da janela para a tela de carrinho

    }

}
