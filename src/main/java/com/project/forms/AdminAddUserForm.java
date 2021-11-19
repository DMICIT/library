package com.project.forms;

import java.util.Objects;

public class AdminAddUserForm {

    private String name;
    private String email;
    private String sex;
    private String phone;
    private String password;

    public AdminAddUserForm(String name, String email, String sex, String phone, String password) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminAddUserForm that = (AdminAddUserForm) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(sex, that.sex) && Objects.equals(phone, that.phone) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, sex, phone, password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
