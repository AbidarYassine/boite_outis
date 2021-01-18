package dao;

import beans.Privilege;
import beans.Role;
import beans.User;
import db_connexion.Connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {
    static Connection conection = Connexion.getConnection();


    public static String createRoleDao(Role role) throws Exception {
        // PreparedStatement statement = null;
        Statement statement = null;
        String result = null;


        if (conection != null) {
            System.out.println("CREATE ROLE " + role.getRole_name() + role.getIdentified() + role.getIdentification());
            String query = "CREATE ROLE " + role.getRole_name() + role.getIdentified() + role.getIdentification();
            statement = conection.createStatement();
            int count = 1;
            statement.executeQuery(query);
            result = query;
        } else {
            result = null;
        }


        return result;

    }

    public static String editRoleDao(Role role) throws Exception {
        // PreparedStatement statement = null;
        Statement statement = null;
        String result = null;


        if (conection != null) {
            System.out.println("ALTER ROLE " + role.getRole_name() + role.getIdentified() + role.getIdentification().toString());
            String query = "ALTER ROLE " + role.getRole_name() + role.getIdentified() + role.getIdentification().toString();
            statement = conection.createStatement();
            int count = 1;
            statement.executeQuery(query);
            result = query;
        } else {
            result = null;
        }


        return result;

    }

    public static String grantRolesToUsersDao(List<Role> roles, List<User> users, String options, Boolean toPublic) throws Exception {
        // PreparedStatement statement = null;
        Statement statement = null;
        String result = null;

        int countUsers = users.size();
        int countRoles = roles.size();

        String roles_ = "";
        String users_ = "";
        for (Role role : roles) {
            countRoles--;
            roles_ = roles_ + " " + role.getRole_name();
            if (countRoles != 0) {
                roles_ = roles_ + " , ";
            }
        }

        if (!toPublic) {
            for (User user : users) {
                countUsers--;
                users_ = users_ + " " + user.getUsername();
                if (countUsers != 0) {
                    users_ = users_ + " , ";
                }
            }
        } else {
            users_ = " PUBLIC ";
        }

        if (conection != null) {
            System.out.println("GRANT " + roles_ + " TO " + users_ + options);
            String query = "GRANT " + roles_ + " TO " + users_ + options;
            statement = conection.createStatement();
            int count = 1;
            statement.executeQuery(query);
            result = query;
        } else {
            result = null;
        }


        return result;

    }

    public static String grantPrevToRolesDao(List<Privilege> privs, List<Role> roles) throws Exception {
        // PreparedStatement statement = null;
        Statement statement = null;
        String result = null;


        int countUsers = privs.size();
        int countRoles = roles.size();

        String roles_ = "";
        String privs_ = "";
        for (Role role : roles) {
            countRoles--;
            roles_ = roles_ + " " + role.getRole_name();
            if (countRoles != 0) {
                roles_ = roles_ + " , ";
            }
        }
        for (Privilege privilege : privs) {
            countUsers--;
            privs_ = privs_ + " " + privilege.getPriv_name();
            if (countUsers != 0) {
                privs_ = privs_ + " , ";
            }
        }
        System.out.println("GRANT " + privs_ + " TO " + roles_);
        if (conection != null) {
            String query = "GRANT " + privs_ + " TO " + roles_;
            statement = conection.createStatement();
            int count = 1;
            statement.executeQuery(query);
            result = query;
        } else {
            result = null;
        }


        return result;

    }

    public List<Role> getAllRoles() throws SQLException {
        PreparedStatement statement = null;
        Role role;
        List<Role> roles = new ArrayList<>();

        if (conection != null) {
            String query = "select role, PASSWORD_REQUIRED, AUTHENTICATION_TYPE from dba_Roles order by role ";
            statement = conection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();


            while (rs.next()) {
                role = new Role();
                role.setRole_name(rs.getString(1));
                role.setIdentified(rs.getString(2));
                role.setPassword_(rs.getString(3));
                roles.add(role);
            }
            return roles;
        } else {
            return null;
        }


    }


}
