package com.example.myapplication;

public class User {
    private String email;
    private String password;
    private String fullName;
    private String username;
    private String gender;
    private String dateOfBirth;
    private String degree;

    //constructors
    public User() {

    }

    public User(String email, String password, String fullName, String username, String gender, String dateOfBirth, String degree) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.username = username;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.degree = degree;
    }

    //getter and setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
