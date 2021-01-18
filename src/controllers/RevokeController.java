package controllers;

import bean.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dao.RecherchDao;
import dao.RevokeDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import services.RevokeService;
import utils.AlertUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RevokeController implements Initializable {

    @FXML
    private JFXTextField serach_input;

    @FXML
    private JFXButton btn_revoke;
    @FXML
    private TableView<User> table_users;
    @FXML
    private TableColumn<User, String> nom_user;
    @FXML
    private TableColumn<User, String> account_status;
    @FXML
    private TableColumn<User, Long> userId;
    @FXML
    private TableColumn<User, String> date_creation;
    @FXML
    private JFXListView<String> list_role_user;
    private User userSelected;
    String role_selected;
    ToggleGroup group = new ToggleGroup();
    @FXML
    JFXRadioButton radio_bublic;
    @FXML
    JFXRadioButton radio_utilisateur;
    int choice = 0;
    @FXML
    TextField show_sql;
    @FXML
    private CheckComboBox<String> list_privil_comboBox;
    @FXML
    private JFXListView<String> listView_role2;
    @FXML
    private JFXButton btb_revoke_privilige;
    private String role_selected2;
    @FXML
    private TabPane tab_revoke;
    @FXML
    private TextField tf_search_role;
    List<String> list_privil;
    List<String> list_role2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initiale();
        ObservableList<User> list = null;
        try {
            list = FXCollections.observableArrayList(RevokeDao.getAllUsers());
        } catch (Exception e) {
            AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
        }
        nom_user.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        account_status.setCellValueFactory(new PropertyValueFactory<User, String>("Status"));
        userId.setCellValueFactory(new PropertyValueFactory<User, Long>("user_id"));
        date_creation.setCellValueFactory(new PropertyValueFactory<User, String>("created"));
        table_users.setItems(list);
        table_users.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            btn_revoke.setDisable(true);
            show_sql.setVisible(false);
            //Check whether item is selected and set value of selected item to Label
            userSelected = table_users.getSelectionModel().getSelectedItem();
            if (userSelected != null) {
                try {

                    list_role_user.getItems().clear();
                    RevokeDao.getRoleByUser(userSelected.getUsername().toString()).forEach(el -> {
                        list_role_user.getItems().add(el);
                    });
                } catch (Exception e) {
                    AlertUtil.ShowAlert("INFO", e.getLocalizedMessage(), Alert.AlertType.ERROR);
                    e.printStackTrace();
                }
            }
        });
        list_role_user.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            role_selected = newValue;
//            AlertUtil.ShowAlert("INFO", "nouvelle valeur " + newValue, Alert.AlertType.INFORMATION);
            btn_revoke.setDisable(false);
        });
        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            // Has selection.
            if (group.getSelectedToggle() != null) {
                RadioButton button = (RadioButton) group.getSelectedToggle();
                //  1  piblic
                // 0 a un utilisateur
                if (button.getId().equalsIgnoreCase("radio_utilisateur")) {
                    choice = 0;
                } else if (button.getId().equalsIgnoreCase("radio_bublic")) {
                    choice = 1;
                }
            }
        });
        ObservableList<User> finalList = list;
        serach_input.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<User> list_search = FXCollections.observableArrayList();
            finalList.forEach(el -> {
                if (el.getUsername().toUpperCase().indexOf(newValue.toUpperCase()) >= 0) {
                    list_search.add(el);
                    table_users.setItems(list_search);
                }
            });
            if (newValue.equalsIgnoreCase("")) {
                table_users.setItems(finalList);
            }
        });
        listView_role2.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                role_selected2 = newValue;
                list_privil_comboBox.getItems().clear();
                list_privil = RecherchDao.getAllPriviligeByRole(newValue);
                list_privil_comboBox.getItems().addAll(list_privil);
                show_sql.setText("");
                show_sql.setVisible(false);
            } catch (Exception e) {
                AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
            }
        });
        tab_revoke.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> {
            show_sql.setText("");
            show_sql.setVisible(false);
        });
        tf_search_role.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<String> list_search = FXCollections.observableArrayList();
            list_role2.forEach(el -> {
                if (el.toUpperCase().indexOf(newValue.toUpperCase()) >= 0) {
                    list_search.add(el);
                    listView_role2.setItems(list_search);
                }
            });
            if (newValue.isEmpty()) {
                listView_role2.getItems().addAll(list_role2);
            }
        });
    }

    @FXML
    public void revoke_role(ActionEvent event) {
        try {
            RevokeService.execQuery(choice, userSelected.getUsername(), role_selected);
            list_role_user.getItems().clear();
            RevokeDao.getRoleByUser(userSelected.getUsername().toString()).forEach(el -> {
                list_role_user.getItems().add(el);
            });
            show_sql.setVisible(true);
            show_sql.setText("requête  sql utiliser : " + RevokeService.getQ());
//            AlertUtil.ShowAlert("LA REQUETE EST ECECUTER AVEC SUCCES", RevokeService.getQ(), Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            AlertUtil.ShowAlert("ERREUR", e.getLocalizedMessage(), Alert.AlertType.ERROR);
        }

    }

    public void initiale() {
        list_privil = new ArrayList<>();
        btn_revoke.setDisable(true);
        radio_bublic.setToggleGroup(group);
        radio_utilisateur.setToggleGroup(group);
        show_sql.setVisible(false);
        try {
            list_role2 = RecherchDao.getAllRole();
            listView_role2.getItems().addAll(list_role2);
        } catch (Exception e) {
            AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    public void revoke_privi_funct(ActionEvent actionEvent) {
        // role ,priviligeg;
        StringBuilder q = new StringBuilder();
        q.append("REVOKE ");
        List<String> list_privilige = list_privil_comboBox.getCheckModel().getCheckedItems();
        if (list_privilige.size() > 0) {
            for (int i = 0; i < list_privilige.size(); i++) {
                if (i != list_privilige.size() - 1) {
                    q.append(list_privilige.get(i) + ",");
                } else {
                    q.append(list_privilige.get(i));
                }
            }
            q.append(" FROM ");
            q.append(role_selected2);
            try {
                RevokeDao.revokeRole(q.toString());
                show_sql.setVisible(true);
                list_privil_comboBox.getItems().clear();
                list_privil_comboBox.getItems().addAll(RecherchDao.getAllPriviligeByRole(role_selected2));
                show_sql.setText("requête  sql utiliser " + q.toString());
//                list_privil_comboBox.getCheckModel().cl;
            } catch (Exception e) {
                AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
            }

        }
    }



}
