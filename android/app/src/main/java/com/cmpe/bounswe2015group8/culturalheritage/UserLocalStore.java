package com.cmpe.bounswe2015group8.culturalheritage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bugrahan on 26/10/15.
 */
public class UserLocalStore {

    public static final String SP_Name = "User Details";
    SharedPreferences  userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_Name, 0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEdidor = userLocalDatabase.edit();
        spEdidor.putString("username", user.username);
        spEdidor.putString("mail", user.mail);
        spEdidor.putString("password", user.password);
        spEdidor.commit();
    }

    public User getLoggedInUser(){
        String username = userLocalDatabase.getString("username", "");
        String mail = userLocalDatabase.getString("mail", "");
        String password = userLocalDatabase.getString("password", "");

        User storedUser = new User(username, mail, password);
        return  storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEdidor = userLocalDatabase.edit();
        spEdidor.putBoolean("loggedIn", loggedIn);
        spEdidor.commit();
    }

    public void clearUserData(){
        SharedPreferences.Editor spEdidor = userLocalDatabase.edit();
        spEdidor.clear();
        spEdidor.commit();
    }
}
