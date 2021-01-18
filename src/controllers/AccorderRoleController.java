package controllers;

import beans.Privilege;
import beans.Role;
import beans.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import dao.PrivilegeDao;
import dao.RoleDao;
import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.textfield.CustomTextField;
import utils.AlertUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccorderRoleController implements Initializable {


    @FXML
    public JFXComboBox<String> grant_role_public_combo;
    @FXML
    public CheckComboBox grant_role_users_comboCheck;
    @FXML
    public JFXComboBox<String> grant_role_adminOpt_combo;
    @FXML
    public JFXButton grant_role_btn;
    @FXML
    public CheckComboBox grant_role_roles_comboCheck;
    @FXML
    public CheckComboBox grant_prev_prevs_comboCheck;
    @FXML
    public CheckComboBox grant_prev_role_comboCheck;
    @FXML
    public JFXButton grant_prev_btn;
    @FXML
    private CustomTextField requette_privs;
    @FXML
    private CustomTextField requette_role;

    //variables de controller
    ObservableList observableList;

    RoleDao roleDao = new RoleDao();
    PrivilegeDao privilegeDao = new PrivilegeDao();
    UserDao userDao = new UserDao();

    User user;
    Privilege privilege;
    Role role;

    List<User> users;
    List<Role> roles;
    List<Privilege> privileges;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadDataIntoChoiceBoxs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void grantRolesToUsers(ActionEvent actionEvent) throws Exception {
        String option = "";
        Boolean toPublic = false;
        observableList = grant_role_users_comboCheck.getCheckModel().getCheckedItems();

        users = new ArrayList<>();
        for (Object user_ : observableList) {
            user = new User();
            user.setUsername(user_.toString());
            users.add(user);
        }
        observableList = grant_role_roles_comboCheck.getCheckModel().getCheckedItems();

        roles = new ArrayList<>();
        for (Object role_ : observableList) {
            role = new Role();
            role.setRole_name(role_.toString());
            roles.add(role);
        }
        if (!grant_role_public_combo.getSelectionModel().isEmpty()) {
            if (grant_role_public_combo.getValue().equalsIgnoreCase("oui")) {
                toPublic = true;
            }
        }
        if (!grant_role_adminOpt_combo.getSelectionModel().isEmpty()) {
            if (grant_role_adminOpt_combo.getValue().equalsIgnoreCase("oui")) {
                option = option + " WITH ADMIN OPTION ";
            }
        }
        try {
            String result = roleDao.grantRolesToUsersDao(roles, users, option, toPublic);

            if (result != null) {
                requette_role.setText(result);
                AlertUtil.ShowAlert("Congratulation!!", "vos roles ont bien ete accorder aux utilisateurs choisies", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
        }


    }

    public void grantPrevsToRoles(ActionEvent actionEvent) {
        String option = "";
        observableList = grant_prev_prevs_comboCheck.getCheckModel().getCheckedItems();

        privileges = new ArrayList<>();
        for (Object privs_ : observableList) {
            privilege = new Privilege();
            privilege.setPriv_name(privs_.toString());
            privileges.add(privilege);
        }
        observableList = grant_prev_role_comboCheck.getCheckModel().getCheckedItems();

        roles = new ArrayList<>();
        for (Object role_ : observableList) {
            role = new Role();
            role.setRole_name(role_.toString());
            roles.add(role);
        }
        try {
            String result = roleDao.grantPrevToRolesDao(privileges, roles);

            if (result != null) {
                requette_privs.setText(result);
                AlertUtil.ShowAlert("Congratulation!!", "vos privileges ont bien ete accorde aux roles choisies", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
        }

    }

    private boolean validationInput() {
        return true;
    }

    private void loadDataIntoChoiceBoxs() throws SQLException {
        observableList = FXCollections.observableArrayList();

        observableList.removeAll(observableList);
        observableList.addAll("OUI", "NON");
        grant_role_public_combo.getItems().addAll(observableList);
        grant_role_adminOpt_combo.getItems().addAll(observableList);


        observableList.removeAll(observableList);

        users = new ArrayList<>();
        roles = new ArrayList<>();
        privileges = new ArrayList<>();

        users = userDao.getAllUsers();
        roles = roleDao.getAllRoles();
        privileges = privilegeDao.getAllprivileges();

        for (Privilege priv : privileges) {
            observableList.add(priv.getPriv_name());
        }
        grant_prev_prevs_comboCheck.getItems().addAll(observableList);
        observableList.removeAll(observableList);
        for (User user : users) {
            observableList.add(user.getUsername());
        }
        grant_role_users_comboCheck.getItems().addAll(observableList);

        observableList.removeAll(observableList);
        for (Role role : roles) {
            observableList.add(role.getRole_name());
        }
        grant_role_roles_comboCheck.getItems().addAll(observableList);
        grant_prev_role_comboCheck.getItems().addAll(observableList);
    }

}
