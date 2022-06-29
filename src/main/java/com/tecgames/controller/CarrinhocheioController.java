package com.tecgames.controller;

import com.tecgames.model.Game;
import com.tecgames.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.util.ArrayList;

public class CarrinhocheioController {
    public ScrollPane scrollPane;

    private VBox BoxMain;

    private Pane PaneGame;
    private User usuarioLogado;
    private ArrayList<Game> jogos;

    public void initData(User usuarioLogado) throws IOException {
        this.usuarioLogado = usuarioLogado;

        Game jogo1 = new Game("1","The last of us", "50.00", "Ação", "Cidades abandonadas retomadas pela natureza. Uma população dizimada por uma praga moderna. Os sobreviventes matam uns aos outros por comida, armas e qualquer outra coisa em que puderem botar as mãos. Joel, um sobrevivente brutal, e Ellie, uma adolescente corajosa e madura apesar da pouca idade, devem unir forças para saírem vivos da sua jornada pelos Estados Unidos.", "21/7/2014", "Windows: 7 (64-bit). CPU: Quad core Intel Core i5 or i7 processor, or AMD FX 8000 series chip. RAM: 4 GB. Hard Disc Space: 14 GB." );
        Game jogo2 = new Game("2","GTA-V", "32.00" , "bla bla",  "bla bla", "bla bla", "bla bla");
        Game jogo3 = new Game("2","GTA-V", "32.00" , "bla bla",  "bla bla", "bla bla", "bla bla");
        Game jogo4 = new Game("2","GTA-V", "32.00" , "bla bla",  "bla bla", "bla bla", "bla bla");


        //instanciando o array com todos os jogos
        jogos = new ArrayList<>();

        jogos.add(jogo1);
        jogos.add(jogo2);
        jogos.add(jogo3);
        jogos.add(jogo4);

        display(jogos);
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
                System.out.println("Apertei");

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
}
