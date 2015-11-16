package com.cmpe.bounswe2015group8.westory;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity{
    public static void beginFragment(Activity a, NamedFragment f) {
        beginFragment(a,f,true);
    }
    public static void beginFragment(Activity a, NamedFragment f, boolean addToBackstack) {
        FragmentTransaction ft = a.getFragmentManager().beginTransaction().replace(R.id.fragmentFrame, f, f.getName());
        if(addToBackstack) ft.addToBackStack(f.getName());
        ft.commit();
    }
    UserLocalStore userLocalStore;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLocalStore = new UserLocalStore(this);
        fm = this.getFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate()) {
            MainActivity.beginFragment(this, new HomeFragment());
        } else {
            MainActivity.beginFragment(this, new LoginFragment());
        }
    }

    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }

}
