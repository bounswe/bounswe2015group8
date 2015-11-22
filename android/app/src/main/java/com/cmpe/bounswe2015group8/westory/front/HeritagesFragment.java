package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.HeritageAdapter;
import com.cmpe.bounswe2015group8.westory.model.Heritage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xyllan on 17.11.2015.
 */
public class HeritagesFragment extends NamedFragment implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String NAME = "All Heritages";
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private FloatingActionButton fab;
    private MemberLocalStore memberLocalStore;
    private Heritage[] heritages;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memberLocalStore = new MemberLocalStore(getActivity());
        final boolean loggedIn = memberLocalStore.getUserLoggedIn();
        View v = inflater.inflate(R.layout.fragment_heritages, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.srlHeritages);
        listView = (ListView) v.findViewById(R.id.lvHeritages);
        fab = (FloatingActionButton) v.findViewById(R.id.fabHeritages);
        swipeRefreshLayout.setOnRefreshListener(this);
        listView.setOnItemClickListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loggedIn) MainActivity.beginFragment(getActivity(), new HeritageEditFragment());
                else MainActivity.beginFragment(getActivity(), new LoginFragment());
            }
        });
        if(heritages != null) {
            setAdapter(heritages);
            return v;
        }
        if (savedInstanceState != null) {
            int index = savedInstanceState.getInt("index", -1);
            int top = savedInstanceState.getInt("top", 0);
            if(index!=-1){
                listView.setSelectionFromTop(index, top);
            }
            if(savedInstanceState.containsKey("heritages")) {
                List<Heritage> heritageList = savedInstanceState.getParcelableArrayList("heritages");
                heritages = heritageList.toArray(new Heritage[heritageList.size()]);
                setAdapter(heritages);
            } else {
                manualRefresh();
            }
        } else {
            manualRefresh();
        }
        return v;
    }
    private void setAdapter(Heritage[] heritages) {
        this.heritages = heritages;
        listView.setAdapter(new HeritageAdapter(getActivity(),R.layout.heritage_small, heritages));
        swipeRefreshLayout.setRefreshing(false);
    }
    @Override
    String getName() {
        return NAME;
    }

    @Override
    String getTitle() {
        return NAME;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        int index = listView.getFirstVisiblePosition();
        View v = listView.getChildAt(0);
        int top = (v == null) ? 0 : v.getTop();

        outState.putInt("index", index);
        outState.putInt("top", top);

        outState.putParcelableArrayList("heritages", new ArrayList<Heritage>(Arrays.asList(heritages)));
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onRefresh() {
        ServerRequests sr = new ServerRequests(getActivity(),false);
        sr.getAllHeritages(new Consumer<Heritage[]>() {
            @Override
            public void accept(Heritage[] heritages) {
                setAdapter(heritages);
            }
        });
    }
    private void manualRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }
}
