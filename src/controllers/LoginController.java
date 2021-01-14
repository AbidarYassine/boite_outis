package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import services.LoginService;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    String mode[] = {"Normal", "SYSDBA"};
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDataIntoChoiceBox();
    }

    @FXML
    public void clickBtn(ActionEvent event) {
        if(validationInput()){
            String role = select_mode.getValue();
            String inst = instance.getText();
            String port_ = port.getText();
            boolean status = loginService.login(login_tv.getText().toString(), password_tv.getText(), role,inst,port_);
            if (status) {
                System.out.println("oki you are login");
            } else {
                System.out.println("not login");
            }
        }



    }


    private boolean validationInput() {
        if (login_tv.getText().isEmpty() || password_tv.getText().isEmpty() || select_mode.getValue().isEmpty()) {
            buttn_login.setDisable(false);
            return false;
        } else {
            buttn_login.setDisable(false);
            return true;
        }


    }

    private void loadDataIntoChoiceBox() {
        observableList.removeAll(observableList);
        observableList.addAll("NORMAL", "SYSDBA");
        select_mode.getItems().addAll(observableList);

    }
}
