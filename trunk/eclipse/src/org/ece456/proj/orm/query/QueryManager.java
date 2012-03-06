package org.ece456.proj.orm.query;

import org.ece456.proj.UserRole;
import org.ece456.proj.orm.query.jdbc.PatientQueryJdbc;

public class QueryManager {

    public static QueryManager connect(UserRole role, String username, long password_hash) {
        // check role, username and password_hash with database
        return new QueryManager();
    }

    public PatientQuery getPatientQuery() {
        return new PatientQueryJdbc();
    }
}
