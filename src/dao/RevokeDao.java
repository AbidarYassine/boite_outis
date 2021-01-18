package dao;

import bean.User;
import db_connexion.Connexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RevokeDao {
    static Connection conection = Connexion.getConnection();


    public static List<User> getAllUsers() throws Exception {
        String query = "SELECT username,user_id,account_status,created FROM dba_users";
        List<User> users = new ArrayList<>();
        Statement stmt = conection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            User user = new User();
            user.setCreated(rs.getString("created"));
            user.setStatus(rs.getString("account_status"));
            user.setUser_id(rs.getLong("user_id"));
            user.setUsername(rs.getString("username"));
            users.add(user);
        }
        stmt.close();
        return users;
    }

    public static List<String> getRoleByUser(String username) throws Exception {
        List<String> roles = new ArrayList<>();
        String query = "select granted_role from DBA_ROLE_PRIVS where grantee='" + username + "'";
        Statement stmt = conection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            roles.add(rs.getString("granted_role"));
        }
        stmt.close();
        return roles;
    }

    public static List<String> getUserByRole(String role) throws Exception {
        List<String> users = new ArrayList<>();
        String query = "select grantee from DBA_ROLE_PRIVS where granted_role='" + role + "'";
        Statement stmt = conection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            users.add(rs.getString("grantee"));
        }
        stmt.close();
        return users;
    }
//    select grantee from DBA_ROLE_PRIVS where granted_role='CONNECT' ;

    public static boolean revokeRole(String query) throws Exception {
        Statement statement = null;
        statement = conection.createStatement();
        System.out.println(query);
        statement.executeUpdate(query);
        return true;
    }

    public static void revokePrivilige(String query) throws Exception {
        Statement stmt = conection.createStatement();
        stmt.executeQuery(query);
        stmt.close();
    }
}
