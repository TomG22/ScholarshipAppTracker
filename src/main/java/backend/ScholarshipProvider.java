package backend;

import java.io.File;

public class ScholarshipProvider extends User {

    public ScholarshipProvider(String netId, String name, String password) {
        super(netId, name, password, RoleType.SCHOLARSHIP_PROVIDER);
    }
}
