package com.cmpe.bounswe2015group8.culturalheritage;

/**
 * Created by bugrahan on 26/10/15.
 */
public class User {

    String username, mail, password;    // profile_picture;

    public User(String name, String username, String password){     //, String profile_picture){
        this.username = username;
        this.mail = mail;
        this.password = password;
        //this.profile_picture = profile_picture;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.mail = "";
    }
}
