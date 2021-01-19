package controllers;

import db_connexion.Connexion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class NavController implements Initializable {


    private Pane view;
    @FXML
    private Text username_nav;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username_nav.setText(Connexion.getUsername());
    }


}
