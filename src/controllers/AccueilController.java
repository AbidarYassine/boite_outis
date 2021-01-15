package controllers;

import dao.RoleDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import utils.AlertUtil;


import java.net.URL;
import java.util.ResourceBundle;

public class AccueilController implements Initializable {
    @FXML
    private  Text username_nav;


    public  void setUsername_nav(String username) {
        username_nav.setText(username.toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void cree_role(ActionEvent event) {
        try {
            System.out.println(RoleDao.creeRole("test"));
        } catch (Exception e) {
            AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
        }
    }
}
