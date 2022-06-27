module TecGames {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.tecgames to javafx.fxml;
    opens com.tecgames.view to javafx.fxml;
    exports com.tecgames;
    //exports com.tecgames.view;
    exports com.tecgames.controller;
    opens com.tecgames.controller to javafx.fxml;

}