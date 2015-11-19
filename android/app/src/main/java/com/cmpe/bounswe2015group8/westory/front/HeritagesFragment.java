package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

/**
 * Created by xyllan on 17.11.2015.
 */
public class HeritagesFragment extends NamedFragment implements AdapterView.OnItemClickListener {
    public static final String NAME = "All Heritages";
    private ListView listView;
    private FloatingActionButton fab;
    private MemberLocalStore memberLocalStore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memberLocalStore = new MemberLocalStore(getActivity());
        final boolean loggedIn = memberLocalStore.getUserLoggedIn();
        View v = inflater.inflate(R.layout.fragment_heritages,container,false);
        listView = (ListView) v.findViewById(R.id.lvHeritages);
        fab = (FloatingActionButton) v.findViewById(R.id.fabHeritages);
        listView.setOnItemClickListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loggedIn)MainActivity.beginFragment(getActivity(),new HeritageEditFragment());
                else MainActivity.beginFragment(getActivity(),new LoginFragment());
            }
        });
        ServerRequests sr = new ServerRequests(getActivity());
        sr.getAllHeritages(new Consumer<Heritage[]>() {
            @Override
            public void accept(Heritage[] heritages) {
                setAdapter(heritages);
            }
        });
        return v;
    }
    private void setAdapter(Heritage[] heritages) {
        listView.setAdapter(new HeritageAdapter(getActivity(),R.layout.heritage_small, heritages));
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
