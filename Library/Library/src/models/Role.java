package models;

import java.io.Serializable;

/**
 * Defines the access roles available to users of the Library Management System.
 *
 * <p>Roles control which commands a logged-in user may execute:
 * <ul>
 *   <li>{@link #ADMIN} – full access, including adding/removing books and users.</li>
 *   <li>{@link #CLIENT} – read-only access to the catalogue.</li>
 * </ul>
 */
public enum Role implements Serializable {
    ADMIN("Admin"),
    CLIENT("Client");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}