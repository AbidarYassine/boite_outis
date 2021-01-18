package controllers;

import db_connexion.Connexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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


    public void goToCreateRole(ActionEvent actionEvent) throws IOException {
        Main.forward(actionEvent, "../view/CreateRole.fxml", this.getClass(),"Créer role");
    }

    public void goToRechercherRole(ActionEvent actionEvent) throws IOException {
        Main.forward(actionEvent, "../view/RechercherRole.fxml", this.getClass(),"Rechercher Role");

    }

    @FXML
    public void btn_goto_revokeScene(ActionEvent event) {
        try {
            Main.forward(event, "../view/revoke.fxml", this.getClass(),"Retirer role");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void disconnect(ActionEvent actionEvent) {
        try {
            Connexion.disconnect();
            Main.forward(actionEvent, "../view/Login.fxml", this.getClass(),"Login");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @FXML
    public void goto_searchWindow(ActionEvent actionEvent) {
        try {
            Main.forward(actionEvent, "../view/RechercheRole.fxml", this.getClass(),"Rechercher role");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @FXML
    private void goto_supp_window(ActionEvent event) {
        try {
            Main.forward(event, "../view/Suppression.fxml", this.getClass(),"Supprimer role");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
