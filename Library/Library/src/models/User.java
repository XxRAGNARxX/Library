package models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a library system user.
 *
 * <p>Each user has a unique username, a plain-text password, and a {@link Role}
 * that determines their access level. Users are serialized as part of the
 * library data file.
 *
 * <p>Equality is based solely on {@code username} (case-sensitive).
 */
public class User implements Serializable {
    private String username;
    private String password;
    private Role role;

    /**
     * Creates a new user.
     *
     * @param username unique login name
     * @param password plain-text password
     * @param role     access role ({@link Role#ADMIN} or {@link Role#CLIENT})
     */
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Returns the user's login name.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    /**
     * Returns {@code true} if this user holds the {@link Role#ADMIN} role.
     * @return {@code true} for admins, {@code false} otherwise
     */
    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Returns a short representation: {@code username [Role]}.
     * @return display string
     */
    @Override
    public String toString() {
        return username + " [" + role + "]";
    }
}