package dao;

import bean.User;
import db_connexion.Connexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecherchDao {
    static Connection conection = Connexion.getConnection();

    public static List<String> getAllRole() throws Exception {
        String query = "SELECT role FROM DBA_ROLES";
        List<String> roles = new ArrayList<>();
        Statement stmt = conection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String role = new String();
            role = rs.getString("role");
            roles.add(role);
        }
        stmt.close();
        return roles;
    }

    //    SELECT
//  *
//    FROM
////    DBA_SYS_PRIVS where grantee='RESOURCE';
    public static List<String> getAllPriviligeByRole(String role) throws Exception {
        String query = "SELECT privilege FROM DBA_SYS_PRIVS WHERE grantee='" + role + "'";
        System.out.println(query);
        List<String> privils = new ArrayList<>();
        Statement stmt = conection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String priv = new String();
            priv = rs.getString("privilege");
            privils.add(priv);
        }
        stmt.close();
        return privils;
    }


}

