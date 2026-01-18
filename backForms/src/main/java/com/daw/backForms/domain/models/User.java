package com.daw.backForms.domain.models;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;

    public User() {}

    public User(Long id, String name, String surname, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public String getSurname() {return surname;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}

    public void setId(Long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
}