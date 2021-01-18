package controllers;

import bean.Role;
import bean.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import dao.RecherchDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import utils.AlertUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RechercheContreoller implements Initializable {

    @FXML
    TextField tf_role;
    @FXML
    TextField tf_priv;
    @FXML
    JFXListView<String> list_role;
    @FXML
    JFXListView<String> list_priv;
    @FXML
    JFXButton btn_role;
    @FXML
    JFXButton btn_priv;
    List<String> all_roles;
    List<String> privilige;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        all_roles = getAllRoles();
        initie();
        list_role.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                list_priv.getItems().clear();
                privilige = RecherchDao.getAllPriviligeByRole(newValue);
                list_priv.getItems().addAll(privilige);
            } catch (Exception e) {
                AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
            }

        });

    }

    private void initie() {

        list_role.getItems().addAll(getAllRoles());
    }

    private List<String> getAllRoles() {
        try {
            return RecherchDao.getAllRole();
        } catch (Exception e) {
            AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
            return new ArrayList<>();
        }
    }

    @FXML
    public void searchRole(ActionEvent event) {
        System.out.println("clicke role");
        String role_name = tf_role.getText().toString();
        List<String> result = new ArrayList<>();
        if (tf_role.getText().isEmpty()) {
            list_role.getItems().addAll(all_roles);
        }
        all_roles.forEach(el -> {
            if (el.toUpperCase().equalsIgnoreCase(role_name.toUpperCase()) ||
                    el.toUpperCase().indexOf(role_name.toUpperCase()) >= 0) {
                result.add(el);
            }
        });
        list_role.getItems().clear();
        list_role.getItems().addAll(result);
    }

    @FXML
    public void search_privilige(ActionEvent event) {
        System.out.println("clicke privil");
        String priv_name = tf_priv.getText().toString();
        List<String> result = new ArrayList<>();
        if (privilige != null && privilige.size() > 0) {
            privilige.forEach(el -> {
                if (el.toUpperCase().equalsIgnoreCase(priv_name.toUpperCase()) || el.toUpperCase().indexOf(priv_name.toUpperCase()) >= 0) {
                    result.add(el);
                }
            });
            list_priv.getItems().clear();
            list_priv.getItems().addAll(result);
        }
    }


}

