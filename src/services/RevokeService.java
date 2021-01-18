package services;

import dao.RevokeDao;

public class RevokeService {
    static String q;

    public static String getQ() {
        return q;
    }

    public static void execQuery(int choose, String username, String role) throws Exception {
        StringBuilder query = new StringBuilder();
//        REVOKE dw_manager
//        FROM sh;

        q = "REVOKE " + role + " FROM " + username; //
        query.append(" " + username);
        if (choose == 1) {
            // au utilisateur
            q = "REVOKE " + role + " FROM PUBLIC";
        }
        RevokeDao.revokeRole(q);

    }
}
