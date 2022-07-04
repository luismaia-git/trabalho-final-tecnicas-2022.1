package com.tecgames.controller;

import com.tecgames.model.User;
import com.tecgames.model.UsuarioDados;
import com.tecgames.model.Venda;
import com.tecgames.model.VendaDados;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GerenciarClientesController implements Initializable {


    public Button logout;
    public TextField fieldBusca;

    public TableView<User> TabelaClientes;
    public TableColumn<User, Integer> colunaId;
    public TableColumn<User, String> colunaNome;
    public TableColumn<User, String> colunaCpf;
    public Label labelClienteId;
    public Label labelClienteNome;
    public Label labelClienteEmail;
    public Label labelClienteCpf;
    public Label labelClienteData;


    private UsuarioDados UsuarioDao;
    private List<User> listClientes;
    private ObservableList<User> observableListClientes;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UsuarioDao = new UsuarioDados();
        carregarDadosTabela();


        TabelaClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTabelaClientes(newValue));

    }


    public void carregarDadosTabela() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listClientes = UsuarioDao.carregaArquivoUsuarios();

        observableListClientes = FXCollections.observableArrayList(listClientes);

        FilteredList<User> filteredData = new FilteredList<>(observableListClientes, b -> true);


        fieldBusca.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(cliente -> {


                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }


                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(cliente.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                } else if (cliente.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (cliente.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (cliente.getCpf().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (cliente.getData().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else
                    return false;
            });
        });


        SortedList<User> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(TabelaClientes.comparatorProperty());


        TabelaClientes.setItems(sortedData);

    }


    public void selecionarItemTabelaClientes(User cliente){

        if(cliente != null)  {

            labelClienteId.setText(String.valueOf(cliente.getId()));
            labelClienteNome.setText(cliente.getNome());
            labelClienteData.setText(cliente.getData());
            labelClienteCpf.setText(cliente.getCpf());
            labelClienteEmail.setText(cliente.getEmail());


        } else {
            labelClienteId.setText("");
            labelClienteNome.setText("");
            labelClienteData.setText("");
            labelClienteCpf.setText("");
            labelClienteEmail.setText("");
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
}
