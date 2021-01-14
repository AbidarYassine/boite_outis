package db_connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    public static Connection getConnecterAsDba() {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String login = "sys as sysdba";
        String passwd = "system";
        Connection cn = null;
        try {

            // Etape 1 : Chargement du driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Etape 2 : recupuration de la connexion
            cn = DriverManager.getConnection(url, login, passwd);
            if(cn!=null){
                System.out.println("Connexion etablie");
            }else{
                System.out.println("Connexion echouer");
            }

            return cn;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;


    }

    public static Connection getConnecter() {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String login = "sys";
        String passwd = "system";
        Connection cn = null;
        try {

            // Etape 1 : Chargement du driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Etape 2 : recupuration de la connexion
            cn = DriverManager.getConnection(url, login, passwd);
            System.out.println("Connexion etablie");
            return cn;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;

    }
    public static Connection getConnecter(String login,String password,String port,String instance) {
        //String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String url = "jdbc:oracle:thin:@localhost:"+port+":"+instance;
        Connection cn = null;
        try {

            // Etape 1 : Chargement du driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Etape 2 : recupuration de la connexion
            cn = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion etablie");
            return cn;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;

    }
}
