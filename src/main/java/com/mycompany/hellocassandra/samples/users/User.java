/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellocassandra.samples.users;

/**
 *
 * @author denys
 */
public class User {

    private String name;
    private String password;
    private String role;
    private String email;

    public User(String name, String password, String role, String email) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", password=" + password + ", role=" + role + ", email=" + email + '}';
    }

}
