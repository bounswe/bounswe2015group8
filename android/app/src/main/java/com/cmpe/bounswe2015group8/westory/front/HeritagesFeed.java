package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.HeritageAdapter;
import com.cmpe.bounswe2015group8.westory.model.Heritage;
import com.cmpe.bounswe2015group8.westory.model.Member;

/**
 * Created by marslanbenzer on 22.12.2015.
 */
public class HeritagesFeed extends NamedFragment {
    private ListView listView;
    private Heritage[] posts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_heritages,container,false);
        listView = (ListView) v.findViewById(R.id.lvHeritages);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fabHeritages);
        fab.hide();
        MemberLocalStore memberLocalStore = new MemberLocalStore(getActivity());
        Member member = memberLocalStore.getLoggedInMember();
        Long idd =member.getId();
        ServerRequests sr=new ServerRequests(getActivity());
        sr.heritagesFeed(idd, new Consumer<Heritage[]>() {
            @Override
            public void accept(Heritage[] heritages) {
                setAdapter(heritages);
            }
        });
        return v;
    }
    private void setAdapter(Heritage[] heritages) {
        this.posts = heritages;
        listView.setAdapter(new HeritageAdapter(getActivity(),R.layout.heritage_small,heritages));
    }

    public static final String NAME = "HeritagesFeed";
    @Override
    String getName() {
        return NAME;
    }

    @Override
    String getTitle() {
        return NAME;
    }
}

