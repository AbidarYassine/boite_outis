package dao;

import db_connexion.Connexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RoleDao {
    static Connection conection = Connexion.getConnection();

    public static String creeRole(String role_name) {
        Statement statement = null;
        String result = "";
        try {
            if (conection != null) {
                statement = conection.createStatement();
                statement.executeUpdate("CREATE ROLE test_role2");
                result = "Success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = e.getMessage();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;

    }
}
