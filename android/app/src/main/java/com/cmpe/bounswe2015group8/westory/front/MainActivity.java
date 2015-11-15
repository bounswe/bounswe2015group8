package com.cmpe.bounswe2015group8.westory.front;

import android.app.Activity;

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
import com.cmpe.bounswe2015group8.westory.model.Heritage;
import com.cmpe.bounswe2015group8.westory.model.Post;

import java.sql.Timestamp;
/**
 * Main activity overviewing all operations. Contains an action bar and navigation sidebar.
 * Is responsible for fragment management and all operations related to action bar and sidebar.
 * @author xyllan
 * Date: 01.11.2015.
 */
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
                NamedFragment nf = null;
                switch(position) {
                    case 0:
                        nf = new HomeFragment();
                        break;
                    case 1:
                        nf = new HeritageViewFragment();
                        Heritage h = new Heritage("asd","asdasd","asdasdasd", new Timestamp(System.currentTimeMillis()));
                        nf.setArguments(h.getBundle());
                        break;
                    case 2:
                        nf = new HeritageEditFragment();
                        break;
                    case 3:
                        nf = new PostViewFragment();
                        Post p = new Post(null,-1,new Timestamp(System.currentTimeMillis()),"asd","leflef");
                        nf.setArguments(p.getBundle());
                        break;
                    case 4:
                        nf = new PostEditFragment();
                        break;
                    case 5:
                        memberLocalStore.clearMemberData();
                        nf = new RegisterFragment();
                        break;
                    default:
                        break;
                }
                MainActivity.beginFragment(a,nf);
            }
        });
    }

}
