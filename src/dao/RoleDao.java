package dao;

import db_connexion.Connexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RoleDao {
    static Connection conection = Connexion.getConnection();

    public static String creeRole(String role_name) throws Exception {
        Statement statement = null;
        String result = "";
        if (conection != null) {
            statement = conection.createStatement();
            statement.executeUpdate("CREATE ROLE test_role3");
            if (statement != null) {
                statement.close();
            }
            result = "Success";
        } else {
            result = "error";
        }
        return result;

    }
}
