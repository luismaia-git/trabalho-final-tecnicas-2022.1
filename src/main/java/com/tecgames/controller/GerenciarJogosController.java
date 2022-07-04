package com.tecgames.controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    private GameDados JogosDao;

    private ArrayList<Game> listJogos;
    private ObservableList<Game> observableListJogos;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JogosDao = new GameDados();
        carregarDadosTabela();

        TabelaJogos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTabelaClientes(newValue));


    }


    public void carregarDadosTabela() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preço"));


        listJogos = JogosDao.carregaArquivoGames();

        observableListJogos = FXCollections.observableArrayList(listJogos);

        FilteredList<Game> filteredData = new FilteredList<>(observableListJogos, b -> true);


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

        } else {
            labelJogoId.setText("");
            labelJogoNome.setText("");
            labelJogoPreco.setText("");
            labelJogoGenero.setText("");
            labelJogoData.setText("");
            labelJogoDescricao.setText("");
            labelJogoRequisitos.setText("");
            labelJogoFaixa.setText("");
        }

    }


    @FXML
    protected void onLogoutButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/tecgames/view/login-view.fxml"));
        Scene scene = new Scene(root, 1000, 600); //cena

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);
    }

    @FXML
    protected void onVoltarButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/tecgames/view/homeadmin-view.fxml"));
        Scene scene = new Scene(root, 1000, 600); //cena

        //This line gets the Stage(window) information
        Stage window = (Stage) logout.getScene().getWindow();

        window.setScene(scene);
    }


    public boolean showCadastroJogo(Game jogo) throws IOException {
        //carregando estilização
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/tecgames/view/components/cadastroJogo.fxml"));

        AnchorPane page = loader.load();

        Scene scene = new Scene(page);

        Stage newWindow = new Stage();
        newWindow.setTitle("Cadastro de jogo");
        newWindow.setScene(scene);

        CadastroJogoController controller = loader.getController();
        controller.setJogo(jogo);
        controller.setCadastroJogo(newWindow);

        newWindow.showAndWait();

        return controller.isBotaoClicado();
    }

    @FXML
    protected void onInserirButtonClick() throws IOException {
        Game game = new Game();
        boolean IsBotaoClicked = showCadastroJogo(game);
        if(IsBotaoClicked) {
            JogosDao.inserir(game);

            carregarDadosTabela();
        }
    }

    @FXML
    protected void onAlterarButtonClick() throws IOException {
        Game game = TabelaJogos.getSelectionModel().getSelectedItem();

        if(game != null){
            boolean IsBotaoClicked = showCadastroJogo(game);
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

    @FXML
    protected void onRemoverButtonClick() throws IOException {
        Game game = TabelaJogos.getSelectionModel().getSelectedItem();

        if(game != null){
            JogosDao.remover(game);
            carregarDadosTabela();

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um jogo na tabela!");
            alert.show();
        }

    }

}
