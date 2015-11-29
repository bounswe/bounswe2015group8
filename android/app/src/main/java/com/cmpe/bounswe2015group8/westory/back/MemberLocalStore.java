package com.cmpe.bounswe2015group8.westory.back;

import android.content.Context;
import android.content.SharedPreferences;

import com.cmpe.bounswe2015group8.westory.model.Member;

/**
 * Class for storing current member information.
 * @author bugrahan
 * Date: 29/10/15.
 */
public class MemberLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences memberLocalDatabase;

    public MemberLocalStore(Context c) {
        memberLocalDatabase = c.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(Member m) {
        SharedPreferences.Editor spEditor = memberLocalDatabase.edit();
        spEditor.putLong("id",m.getId());
        spEditor.putString("username", m.getUsername());
        spEditor.putString("email", m.getEmail());
        spEditor.putString("profilePicture",m.getProfilePicture());
        spEditor.putBoolean("loggedIn",true);
        spEditor.commit();
    }

    public Member getLoggedInMember() {
        long id = memberLocalDatabase.getLong("id", -1);
        String username = memberLocalDatabase.getString("username", "");
        String email = memberLocalDatabase.getString("email", "");
        String profilePicture = memberLocalDatabase.getString("profilePicture","");
        return new Member(id, username, "", email, profilePicture);
    }

    public void setMemberLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = memberLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn() {
        return memberLocalDatabase.getBoolean("loggedIn", false);
    }

    public void clearMemberData() {
        SharedPreferences.Editor spEditor = memberLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
