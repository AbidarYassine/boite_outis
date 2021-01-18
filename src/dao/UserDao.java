package dao;

import beans.User;
import db_connexion.Connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    static Connection conection = Connexion.getConnection();


    ////////////
    public List<User> getAllUsers() throws SQLException {
        PreparedStatement statement = null;
        User user;
        List<User> users = new ArrayList<>();

        if (conection != null) {
            String query = "select user_id,username,created,account_status from dba_users order by username";
            statement = conection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();


            while (rs.next()) {
                user = new User();
                user.setUser_id(rs.getLong(1));
                user.setUsername(rs.getString(2));
                user.setCreated(rs.getDate(3));
                user.setStatus(rs.getString(4));
                users.add(user);
            }
            return users;
        } else {
            return null;
        }


    }


}
