package db_connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    static Connection connection ;


    public static Connection getConnecter(String login, String password, String port, String instance) throws Exception {
        //String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//        System.out.println("pour choisit " + port);
        String url = "jdbc:oracle:thin:@localhost:" + port + ":" + instance;


        // Etape 1 : Chargement du driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = null;
        // Etape 2 : recupuration de la connexion
        connection = DriverManager.getConnection(url, login, password);
        System.out.println("Connexion etablie");
        return connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static int disconnect() {
        try {
            connection.close();
            connection=null;
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
