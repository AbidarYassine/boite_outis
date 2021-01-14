package controllers;

import dao.RoleDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AccueilController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void cree_role(ActionEvent event) {
        System.out.println(RoleDao.creeRole("test"));
    }
}
