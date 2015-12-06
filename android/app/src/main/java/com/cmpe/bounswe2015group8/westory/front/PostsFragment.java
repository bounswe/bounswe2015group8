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
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.PostAdapter;
import com.cmpe.bounswe2015group8.westory.model.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xyllan on 17.11.2015.
 */
public class PostsFragment extends NamedFragment implements SwipeRefreshLayout.OnRefreshListener{
    public static final String NAME = "All Posts";
    public static final String BUNDLE_INDEX = "index";
    public static final String BUNDLE_TOP = "top";
    public static final String BUNDLE_POSTS = "posts";
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private Post[] posts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_heritages,container,false);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.srlHeritages);
        listView = (ListView) v.findViewById(R.id.lvHeritages);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fabHeritages);
        fab.hide();
        swipeRefreshLayout.setOnRefreshListener(this);
        if(posts != null) {
            setAdapter(posts);
            return v;
        }
        if (savedInstanceState != null) {
            int index = savedInstanceState.getInt(BUNDLE_INDEX, -1);
            int top = savedInstanceState.getInt(BUNDLE_TOP, 0);
            if(index!=-1){
                listView.setSelectionFromTop(index, top);
            }
            if(savedInstanceState.containsKey(BUNDLE_POSTS)) {
                List<Post> postList = savedInstanceState.getParcelableArrayList(BUNDLE_POSTS);
                posts = postList.toArray(new Post[postList.size()]);
                setAdapter(posts);
            } else {
                manualRefresh();
            }
        } else {
            manualRefresh();
        }
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private void setAdapter(Post[] posts) {
        this.posts = posts;
        listView.setAdapter(new PostAdapter(getActivity(),R.layout.post_small, posts));
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

        outState.putParcelableArrayList(BUNDLE_POSTS, new ArrayList<Post>(Arrays.asList(posts)));
    }

    @Override
    public void onRefresh() {
        ServerRequests sr = new ServerRequests(getActivity(),false);
        sr.getAllPosts(new Consumer<Post[]>() {
            @Override
            public void accept(Post[] posts) {
                setAdapter(posts);
            }
        });
    }
    private void manualRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }
}

