package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.CommentAdapter;
import com.cmpe.bounswe2015group8.westory.model.Comment;

/**
 * Created by marslanbenzer on 27.11.2015.
 */
public class CommentsFragment extends NamedFragment implements SwipeRefreshLayout.OnRefreshListener{
    public static final String NAME = "All Comments";
    protected SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_heritages,container,false);
        listView = (ListView) v.findViewById(R.id.lvHeritages);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fabHeritages);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.srlHeritages);
        swipeRefreshLayout.setOnRefreshListener(this);
        fab.hide();
        manualRefresh();
        return v;
    }
    private void setAdapter(Comment[] comments) {
        listView.setAdapter(new CommentAdapter(getActivity(), R.layout.comment_small, comments));
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
    public void onRefresh() {
        final ServerRequests sr = new ServerRequests(getActivity(),false);
        sr.getAllComments(new Consumer<Comment[]>() {
            @Override
            public void accept(Comment[] comments) {
                if(comments == null) {
                    ServerRequests.handleErrors(getContext(),sr);
                    swipeRefreshLayout.setRefreshing(false);
                }
                else setAdapter(comments);
            }
        });
    }
    private void manualRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }
}
