package com.project.web.data;

import java.util.Objects;

public class UserPrincipal {
    private String email;
    private String role;

    public UserPrincipal(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(email, that.email) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, role);
    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
