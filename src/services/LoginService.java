package services;

import db_connexion.Connexion;

public class LoginService {


    public boolean login(String login, String password, String role,String inst,String port) {
        try {
            if (role.equalsIgnoreCase("NORMAL")) {
                if (Connexion.getConnecter(login, password,inst,port) != null) return true;
                return false;
            } else {
                login += " as sysdba";
                if (Connexion.getConnecter(login, password,inst,port) != null) return true;
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

    }
}
