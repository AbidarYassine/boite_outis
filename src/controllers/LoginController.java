package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lancher.Main;
import services.LoginService;
import utils.AlertUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    String[] mode = {"Normal", "SYSDBA"};
    ObservableList observableList = FXCollections.observableArrayList();
    // create a choiceBox
    @FXML
    JFXComboBox<String> select_mode;
    @FXML
    JFXTextField login_tv;
    @FXML
    JFXPasswordField password_tv;
    @FXML
    JFXTextField instance;
    @FXML
    JFXTextField port;
    @FXML
    JFXButton buttn_login;
    LoginService loginService = new LoginService();
    boolean status = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDataIntoChoiceBox();
        port.setText("1521");
    }

    @FXML
    public void clickBtn(ActionEvent event) {
        if (validationInput()) {
            String role = select_mode.getValue();
            String inst = instance.getText();
            String port_c = port.getText();
            Thread thread_login = new Thread(() -> {
                try {
                    status = loginService.login(login_tv.getText(), password_tv.getText(), role, inst, port_c);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage().toString());
                }
            });
            try {
                thread_login.start();
                thread_login.join();
                if (status) {
                    Main.forward(event, "../view/CreateRole.fxml", this.getClass(),"Accueil");
                } else {
                    System.out.println("not login");
                }
            } catch (InterruptedException | IOException e) {
                AlertUtil.ShowAlert("Erreur", e.getLocalizedMessage(), Alert.AlertType.ERROR);
            }
        }
    }


    private boolean validationInput() {
        if (login_tv.getText().isEmpty() || password_tv.getText().isEmpty() || select_mode.getValue() == null || select_mode.getValue().isEmpty()
                || port.getText().isEmpty() || instance.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }


    }

    private void loadDataIntoChoiceBox() {
        observableList.removeAll(observableList);
        observableList.addAll("NORMAL", "SYSDBA");
        select_mode.getItems().addAll(observableList);

    }
}
