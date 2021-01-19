package controllers;

import beans.Role;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dao.RoleDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.CustomTextField;
import utils.AlertUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateRoleController implements Initializable {

    //instanciation des variables de jfx
    @FXML
    private JFXTextField cr_role_password_txt;
    @FXML
    private JFXTextField cr_role_schema_txt;
    @FXML
    private JFXTextField cr_role_package_txt;
    @FXML
    private JFXRadioButton cr_role_rb_password;
    @FXML
    private JFXRadioButton cr_role_rb_globally;
    @FXML
    private JFXRadioButton cr_role_rb_externally;
    @FXML
    private JFXRadioButton cr_role_rb_package;
    @FXML
    private JFXTextField cr_role_name;
    @FXML
    private JFXComboBox<String> cr_role_identified;
    @FXML
    private CustomTextField requette_cr_role;

    //variables de controller
    ObservableList observableList = FXCollections.observableArrayList();
    ToggleGroup toggleGroup = new ToggleGroup();

    RoleDao roleDao = new RoleDao();
    Role role;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDataIntoChoiceBox();

        cr_role_rb_password.setToggleGroup(toggleGroup);
        cr_role_rb_password.setId(1 + "");
        cr_role_rb_globally.setToggleGroup(toggleGroup);
        cr_role_rb_globally.setId(2 + "");
        cr_role_rb_externally.setToggleGroup(toggleGroup);
        cr_role_rb_externally.setId(3 + "");
        cr_role_rb_package.setToggleGroup(toggleGroup);
        cr_role_rb_package.setId(4 + "");

        radioButtonsDisable(true);

        //
        cr_role_identified.getSelectionModel().selectedItemProperty().addListener((observable, oldval, newval) -> {
            if (newval.equalsIgnoreCase("non")) {
                radioButtonsDisable(true);

                cr_role_password_txt.setDisable(true);
                cr_role_schema_txt.setDisable(true);
                cr_role_package_txt.setDisable(true);
            } else {
                cr_role_password_txt.setDisable(true);
                cr_role_schema_txt.setDisable(true);
                cr_role_package_txt.setDisable(true);
                radioButtonsDisable(false);
            }
        });
        //
        cr_role_password_txt.setDisable(true);
        cr_role_schema_txt.setDisable(true);
        cr_role_package_txt.setDisable(true);

        toggleGroup.selectedToggleProperty().addListener((observable, oldval, newVal) -> {
            JFXRadioButton rb = (JFXRadioButton) toggleGroup.getSelectedToggle();
            int id = Integer.parseInt(rb.getId());
            if (id != 1) {
                cr_role_password_txt.setDisable(true);
                cr_role_password_txt.setText("");
            } else {
                cr_role_password_txt.setDisable(false);
            }
            if (id != 4) {
                cr_role_schema_txt.setDisable(true);
                cr_role_schema_txt.setText("");
                cr_role_package_txt.setDisable(true);
                cr_role_package_txt.setText("");
            } else {
                cr_role_package_txt.setDisable(false);
                cr_role_schema_txt.setDisable(false);
            }
        });
    }

    @FXML
    public void createRole(ActionEvent actionEvent) {
        if (validationInput()) {

            role = new Role();

            String identified_ = "";
            String identification_ = "";
            role.setRole_name(" " + cr_role_name.getText() + " ");
            role.setPassword_(" " + cr_role_password_txt.getText() + " ");
            role.setSchema_(cr_role_schema_txt.getText());
            role.setPackage_(cr_role_package_txt.getText());
            role.setIdentified(cr_role_identified.getValue());

            JFXRadioButton rb = (JFXRadioButton) toggleGroup.getSelectedToggle();
            if (rb != null) {
                int id = Integer.parseInt(rb.getId());
                if (id == 1) {
                    role.setIdentification(" BY " + role.getPassword_());
                } else if (id == 2) {
                    role.setIdentification(" GLOBALLY");
                } else if (id == 3) {
                    role.setIdentification(" EXTERNALLY");
                } else if (id == 4) {
                    role.setIdentification(" USING " + role.getSchema_() + "." + role.getPackage_());
                }
            } else {
                role.setIdentification("");
            }
            if (role.getIdentified().equalsIgnoreCase("OUI")) {
                role.setIdentified("IDENTIFIED");
            } else {
                role.setIdentified("NOT IDENTIFIED");
                role.setIdentification("");
            }
            try {
                String result = roleDao.createRoleDao(role);
                if (result != null) {
                    requette_cr_role.setText(result);
                    AlertUtil.ShowAlert("Congratulation!!", "Votre role a bie ete crée", Alert.AlertType.INFORMATION);
                }
            } catch (Exception e) {
                AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
            }
        } else {
            AlertUtil.ShowAlert("Whoops!", "Veuillez remplir touts les champs necessaires pour continuer :)", Alert.AlertType.WARNING);
        }
    }

    private void loadDataIntoChoiceBox() {

        observableList.removeAll(observableList);
        observableList.addAll("OUI", "NON");
        if (cr_role_identified != null) {
            cr_role_identified.getItems().addAll(observableList);
        }
    }

    private boolean validationInput() {
        if (cr_role_name.getText().equalsIgnoreCase("") || cr_role_identified.getValue().equalsIgnoreCase("")) {

            return false;
        } else {
            JFXRadioButton rb = (JFXRadioButton) toggleGroup.getSelectedToggle();
            if (rb != null) {
                String ids = rb.getId();
                int id = Integer.parseInt(ids);
                if (id == 1) {
                    if (cr_role_password_txt.getText().equalsIgnoreCase("")) {
                        return false;
                    }
                }
                if (id == 4) {
                    if (cr_role_schema_txt.getText().equalsIgnoreCase("") || cr_role_package_txt.getText().equalsIgnoreCase("")) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private void radioButtonsDisable(Boolean disable) {
        cr_role_rb_password.setDisable(disable);
        cr_role_rb_globally.setDisable(disable);
        cr_role_rb_externally.setDisable(disable);
        cr_role_rb_package.setDisable(disable);
    }
}
