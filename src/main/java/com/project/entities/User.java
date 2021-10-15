package com.project.entities;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private String sex;
    private String phone;
    private String role;
    private int banList;
    private String password;


    public User(int id, String name, String email, String sex, String phone, String role, int banList, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.role = role;
        this.banList = banList;
        this.password = password;
    }

    public User(String name, String email, String sex, String phone, String role, int banList, String password) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.role = role;
        this.banList = banList;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getBanList() {
        return banList;
    }

    public void setBanList(int banList) {
        this.banList = banList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && banList == user.banList && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(sex, user.sex) && Objects.equals(phone, user.phone) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, sex, phone, role, banList);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", banList=" + banList +
                '}';
    }
}
