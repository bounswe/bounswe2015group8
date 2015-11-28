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
        navBarView = (ListView) findViewById(R.id.drawer_list);
        setOnItemClickListener(navBarView);
        resetNavbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.generic_description,R.string.generic_description);
        drawerLayout.setDrawerListener(drawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //MainActivity.beginFragment(this, new HomeFragment());
        MainActivity.beginFragment(this, new HeritagesFragment());
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
    public void resetNavbar() {
        String[] navbarItems = getResources().getStringArray(R.array.nav_drawer_items);
        if(authenticated()) navbarItems[navbarItems.length-1] = getResources().getString(R.string.btn_logout);
        navBarAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navbarItems);
        navBarView.setAdapter(navBarAdapter);
    }
    private boolean authenticated() {
        return memberLocalStore.getUserLoggedIn();
    }
    private void setOnItemClickListener(ListView lv) {
        final MainActivity a = this;
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
                        nf = new HeritagesFragment();
                        break;
                    case 2:
                        nf = new HeritageEditFragment();
                        break;
                    case 3:
                        nf = new PostsFragment();
                        break;
                    case 4:
                        nf = new ProfileFragment();
                        break;
                    case 5:
                        nf = new CommentsFragment();
                        break;
                    case 6:
                        if(authenticated()) {
                            memberLocalStore.clearMemberData();
                            nf = new HomeFragment();
                        } else {
                            nf = new LoginFragment();
                        }
                        resetNavbar();
                        break;
                    default:
                        break;
                }
                MainActivity.beginFragment(a,nf);
            }
        });
    }

}
