package lancher;

import db_connexion.Connexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root=FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("dba toolbox");
//        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
