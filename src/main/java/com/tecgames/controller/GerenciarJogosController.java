package com.tecgames.controller;

import com.tecgames.model.Admin;
import com.tecgames.model.Game;
import com.tecgames.model.GameDados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/** Class GerenciarJogosController, controller for the manage games */
public class GerenciarJogosController implements Initializable {

    public Button logout;
    public TableView<Game> TabelaJogos;
    public TableColumn<Game, Integer> colunaId;
    public TableColumn<Game, String> colunaNome;
    public TableColumn<Game, Double> colunaPreco;
    public Label labelJogoId;
    public Label labelJogoNome;
    public Label labelJogoPreco;
    public Label labelJogoGenero;
    public Label labelJogoDescricao;
    public Label labelJogoData;
    public Label labelJogoRequisitos;
    public Label labelJogoFaixa;
    public TextField fieldBusca;
    public Button botaoInserir;
    public Button botaoAlterar;
    public Button botaoRemover;
    public ImageView imagegame;
    private GameDados JogosDao;

    private Admin adminLogado;


    private ArrayList<Game> listJogos;
    private ObservableList<Game> observableListJogos;

    /** Returns the logged admin */
    public Admin getAdminLogado() {
        return adminLogado;
    }

    /** Sets controller initial data */
    public void initData(Admin adminLogado){
        this.adminLogado = adminLogado;
    }

    /** Defines the initial behavior of the controller */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JogosDao = new GameDados();
        carregarDadosTabela();

        TabelaJogos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTabelaClientes(newValue));


    }

    /** Build a table from the data */
    public void carregarDadosTabela() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preço"));


        listJogos = JogosDao.carregaArquivoGames();

        observableListJogos = FXCollections.observableArrayList(listJogos);

        FilteredList<Game> filteredData = new FilteredList<>(observableListJogos, b -> true);

        //adicionando um listener em campo de pesquisa, para fazer a busca na tabela e mostrar o conteudo referente oque está no campo de texto de pesquisa
        fieldBusca.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(jogo -> {


                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(jogo.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                } else if (jogo.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(jogo.getPreço()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (jogo.getGenero().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (jogo.getDescricao().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (jogo.getAnolançamento().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (jogo.getRequisitos().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (jogo.getClassificacao().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else
                    return false;
            });
        });


        SortedList<Game> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(TabelaJogos.comparatorProperty());


        TabelaJogos.setItems(sortedData);

    }

    /** Selects one item in the table and display info */
    public void selecionarItemTabelaClientes(Game jogo){

        if(jogo != null)  {

            labelJogoId.setText(String.valueOf(jogo.getId()));
            labelJogoNome.setText(jogo.getNome());
            labelJogoPreco.setText(String.valueOf(jogo.getPreço()));
            labelJogoGenero.setText(jogo.getGenero());
            labelJogoData.setText(jogo.getAnolançamento());
            labelJogoDescricao.setText(jogo.getDescricao());
            labelJogoRequisitos.setText(jogo.getRequisitos());
            labelJogoFaixa.setText(jogo.getClassificacao());

            File f = new File("images/games/"+jogo.getId()+".jpg");
            Image m = new Image( f.getAbsolutePath(), 236, 145 ,false, false);
            imagegame.setImage(m); //setando imagem do jogo nos detalhes

        } else {
            labelJogoId.setText("");
            labelJogoNome.setText("");
            labelJogoPreco.setText("");
            labelJogoGenero.setText("");
            labelJogoData.setText("");
            labelJogoDescricao.setText("");
            labelJogoRequisitos.setText("");
            labelJogoFaixa.setText("");
            imagegame.setImage(null);
        }

    }

    /** Button logout */
    @FXML
    protected void onLogoutButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/tecgames/view/login-view.fxml"));
        Scene scene = new Scene(root, 1000, 600); //cena

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);
    }

    /** Button to return to the home screen */
    @FXML
    protected void onVoltarButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/homeadmin-view.fxml"));
        Parent page = loader.load();
        Scene scene = new Scene(page, 1000, 600); //cena

        HomepageAdminController controller = loader.getController();
        controller.initData(getAdminLogado());
        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);
    }

    /** Game register button */
    public boolean showCadastroJogo(Game jogo, int i) throws IOException {
        //carregando estilização
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/components/cadastroJogo.fxml"));

        AnchorPane page = loader.load();

        Scene scene = new Scene(page);

        Stage newWindow = new Stage();

        newWindow.setResizable(false);

        if(i == 1){
            newWindow.setTitle("Cadastro de jogo");
        }else {
            newWindow.setTitle("Alterando "+ jogo.getNome());
        }

        newWindow.setScene(scene);

        CadastroJogoController controller = loader.getController();
        controller.setJogo(jogo);
        controller.setCadastroJogo(newWindow);

        newWindow.showAndWait();

        return controller.isBotaoClicado();
    }

    /** Insert game button */
    @FXML
    protected void onInserirButtonClick() throws IOException {
        Game game = new Game();
        boolean IsBotaoClicked = showCadastroJogo(game, 1); // recebe a "resposta" se clicou no botao confirmar
        if(IsBotaoClicked) {
            JogosDao.inserir(game);

            carregarDadosTabela();
        }
    }

    /** Change game button */
    @FXML
    protected void onAlterarButtonClick() throws IOException {
        Game game = TabelaJogos.getSelectionModel().getSelectedItem();

        if(game != null){
            boolean IsBotaoClicked = showCadastroJogo(game, 2); // recebe a "resposta" se clicou no botao confirmar
            if(IsBotaoClicked) {
                JogosDao.alterar(game);
                carregarDadosTabela();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um jogo na tabela!");
            alert.show();
        }

    }

    /** Remove game button */
    @FXML
    protected void onRemoverButtonClick() throws IOException {
        Game game = TabelaJogos.getSelectionModel().getSelectedItem();

        if(game != null) {
            JogosDao.remover(game);
            carregarDadosTabela();

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um jogo na tabela!");
            alert.show();
        }

    }

}