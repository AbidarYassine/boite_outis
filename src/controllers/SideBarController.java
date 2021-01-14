package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import lancher.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SideBarController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void gotoDashboard(ActionEvent actionEvent) throws IOException {
        Main.forward(actionEvent, "../view/Exemple1.fxml", this.getClass());
    }

    public void gotoAlterRole(ActionEvent actionEvent) {
    }

    public void gotoGrantRole(ActionEvent actionEvent) {
    }

    public void disconnect(ActionEvent actionEvent) {
    }

    public void goToCreateRole(ActionEvent actionEvent) throws IOException {
        Main.forward(actionEvent, "../view/Exemple2.fxml", this.getClass());
    }
}
