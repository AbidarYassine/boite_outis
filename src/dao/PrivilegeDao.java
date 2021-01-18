package dao;

import beans.Privilege;
import db_connexion.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrivilegeDao {

    static Connection conection = Connexion.getConnection();


    public List<Privilege> getAllprivileges() throws SQLException {
        PreparedStatement statement = null;
        Privilege privilege;
        List<Privilege> privileges = new ArrayList<>();

        if (conection != null) {
            String query = "select distinct privilege from role_sys_privs order by privilege ";
            statement = conection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();


            while (rs.next()) {
                privilege = new Privilege();
                privilege.setPriv_name(rs.getString(1));
                privileges.add(privilege);
            }
            return privileges;
        } else {
            return null;
        }


    }
}
