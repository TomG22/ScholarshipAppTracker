package backend;

import java.util.HashSet;
import java.util.Set;

public class Reviewer extends User {

    private final Set<String> majors;

    public Reviewer(String netId, String name, String password, Set<String> majors) {
        super(netId, name, password, RoleType.REVIEWER);

        if (majors == null)
            throw new IllegalArgumentException("Majors cannot be null.");

        this.majors = new HashSet<>(majors);
    }

    public Set<String> getMajors() {
        return new HashSet<>(majors);
    }
}

