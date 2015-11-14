package com.cmpe.bounswe2015group8.westory.front;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;

public class MainActivity extends Activity{
    public static void beginFragment(Activity a, NamedFragment f) {
        beginFragment(a,f,true);
    }
    public static void beginFragment(Activity a, NamedFragment f, boolean addToBackstack) {
        FragmentTransaction ft = a.getFragmentManager().beginTransaction().replace(R.id.fragmentFrame, f, f.getName());
        if(addToBackstack) ft.addToBackStack(f.getName());
        ft.commit();
    }
    MemberLocalStore memberLocalStore;
    FragmentManager fm;
    ListView navBarView;
    ArrayAdapter<String> navBarAdapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memberLocalStore = new MemberLocalStore(this);
        fm = this.getFragmentManager();
        String[] navbarItems = this.getResources().getStringArray(R.array.nav_drawer_items);

        navBarView = (ListView) findViewById(R.id.drawer_list);
        navBarAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navbarItems);
        navBarView.setAdapter(navBarAdapter);
        setOnItemClickListener(navBarView);

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.generic_description,R.string.generic_description);
        drawerLayout.setDrawerListener(drawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
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
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean authenticate() {
        return memberLocalStore.getUserLoggedIn();
    }
    private void setOnItemClickListener(ListView lv) {
        final Activity a = this;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLayout.closeDrawer(navBarView);
                switch(position) {
                    case 0:
                        MainActivity.beginFragment(a,new HomeFragment());
                        break;
                    case 1:
                        MainActivity.beginFragment(a,new HeritageEditFragment());
                        break;
                    case 3:
                        memberLocalStore.clearMemberData();
                        MainActivity.beginFragment(a,new RegisterFragment());
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
