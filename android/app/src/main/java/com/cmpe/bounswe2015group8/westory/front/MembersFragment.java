package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.front.adapter.MemberAdapter;
import com.cmpe.bounswe2015group8.westory.model.Member;

/**
 * Created by marslanbenzer on 20.12.2015.
 */
public class MembersFragment extends NamedFragment{
    public static final String NAME = "Follow";
    private ListView listView;
    public Member [] members;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_heritages,container,false);
        listView = (ListView) v.findViewById(R.id.lvHeritages);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fabHeritages);
        fab.hide();
        members = (Member[]) getArguments().getParcelableArray("Members");
        setAdapter(members);
        return v;
    }
    private void setAdapter(Member[] members) {
        listView.setAdapter(new MemberAdapter(getActivity(),R.layout.member_small, members));
    }
    @Override
    String getName() {
        return NAME;
    }

    @Override
    String getTitle() {
        return NAME;
    }

}