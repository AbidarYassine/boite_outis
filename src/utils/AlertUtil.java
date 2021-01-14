package utils;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class AlertUtil {

    public static void ShowAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(null);
        alert.show();
    }
}
