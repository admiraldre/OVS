package com.example.ovs.activities;

public class User {
    public String name, email, password;

    public User(String name, String password){

    }

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
