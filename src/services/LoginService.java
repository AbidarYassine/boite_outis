package services;

import db_connexion.Connexion;

public class LoginService {


    public boolean login(String login, String password, String role, String inst, String port) throws Exception {

        if (role.equalsIgnoreCase("NORMAL") || role.equalsIgnoreCase("")) {
            return Connexion.getConnecter(login, password, port, inst) != null;
        } else {
            login += " as sysdba";
            return Connexion.getConnecter(login, password, port, inst) != null;
        }
    }

}

