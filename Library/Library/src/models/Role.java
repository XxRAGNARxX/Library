package models;

import java.io.Serializable;

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