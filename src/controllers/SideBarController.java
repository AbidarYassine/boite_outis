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
        Main.forward(actionEvent, "../view/Accueil.fxml", this.getClass(),"Accueil");
    }

    public void gotoAlterRole(ActionEvent actionEvent) throws IOException {
        Main.forward(actionEvent, "../view/EditRole.fxml", this.getClass(),"Modifier les roles");
    }

    public void gotoGrantRole(ActionEvent actionEvent) throws IOException {
        Main.forward(actionEvent, "../view/AccorderRole.fxml", this.getClass(),"Accorder role");
    }

    public void disconnect(ActionEvent actionEvent) {
    }

    public void goToCreateRole(ActionEvent actionEvent) throws IOException {
        Main.forward(actionEvent, "../view/CreateRole.fxml", this.getClass(),"Créer role");
    }

    public void goToRechercherRole(ActionEvent actionEvent) throws IOException {
        Main.forward(actionEvent, "../view/RechercherRole.fxml", this.getClass(),"Rechercher Role");

    }
}
