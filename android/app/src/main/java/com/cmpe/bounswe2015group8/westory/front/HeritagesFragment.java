package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
    public static final String BUNDLE_INDEX = "index";
    public static final String BUNDLE_TOP = "top";
    public static final String BUNDLE_HERITAGES = "heritages";
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
            int index = savedInstanceState.getInt(BUNDLE_INDEX, -1);
            int top = savedInstanceState.getInt(BUNDLE_TOP, 0);
            if(index!=-1){
                listView.setSelectionFromTop(index, top);
            }
            if(savedInstanceState.containsKey(BUNDLE_HERITAGES)) {
                List<Heritage> heritageList = savedInstanceState.getParcelableArrayList(BUNDLE_HERITAGES);
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

        outState.putInt(BUNDLE_INDEX, index);
        outState.putInt(BUNDLE_TOP, top);

        outState.putParcelableArrayList(BUNDLE_HERITAGES, new ArrayList<Heritage>(Arrays.asList(heritages)));
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
