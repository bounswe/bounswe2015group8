package com.cmpe.bounswe2015group8.westory;

/**
 * Created by bugrahan on 29/10/15.
 */
public class User {

    String username, mail, password;


    public User (String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }

    public User (String username, String password) {
        this.username = username;
        this.password = password;
        this.mail = "@";
    }
}
