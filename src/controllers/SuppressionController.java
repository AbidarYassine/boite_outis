package controllers;

import bean.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import dao.RecherchDao;
import dao.RevokeDao;
import dao.RoleDao;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import utils.AlertUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SuppressionController implements Initializable {


    @FXML
    private JFXListView<String> listView_role;
    @FXML
    private JFXListView<String> listView_user;
    @FXML
    private JFXButton btn_delete_role;
    @FXML
    private TextField tf_search_role;
    List<String> list_role;
    List<String> list_user;
    @FXML
    private MaterialDesignIconView iconDelete;
    private String selected_role;

    @FXML
    private TextField show_sql_tf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initileListRole();
        listView_role.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                list_user = RevokeDao.getUserByRole(newValue);
                listView_user.getItems().clear();
                listView_user.getItems().addAll(list_user);
                btn_delete_role.setDisable(false);
                iconDelete.setDisable(false);
                selected_role = newValue;
            } catch (Exception e) {
                AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
            }
        });
        tf_search_role.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<String> list_search = FXCollections.observableArrayList();
            list_role.forEach(el -> {
                if (el.toUpperCase().indexOf(newValue.toUpperCase()) >= 0) {
                    list_search.add(el);
                    listView_role.setItems(list_search);
                }
            });
            if (newValue.equalsIgnoreCase("")) {
                listView_role.getItems().clear();
                listView_role.getItems().addAll(list_role);
            }
        });
    }

    private void initileListRole() {
        try {
            list_role = RecherchDao.getAllRole();
            listView_role.getItems().clear();
            listView_role.getItems().addAll(list_role);
        } catch (Exception e) {
            AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void delete_role(ActionEvent actionEvent) {
        try {
            if (AlertUtil.ShowAlertConfirmation("Warning", "Vous voulez vraiment supprimer le rôle: " + selected_role)) {
                RoleDao.supprimerRole(selected_role);
                show_sql_tf.setVisible(true);
                show_sql_tf.setText("requête  sql utiliser :" + RoleDao.getQ());
                initileListRole();
                btn_delete_role.setDisable(true);
            }
        } catch (Exception e) {
            AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
        }
    }
}
