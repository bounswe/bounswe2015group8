package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Post;

/**
 * Created by xyllan on 05.01.2016.
 */
public class PostSearchFragment extends PostsFragment {
    public static final String NAME = "Post Search";
    private String searchTerm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchTerm = getArguments().getString("searchTerm","");
        View v = super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    @Override
    String getName() {
        return NAME;
    }

    @Override
    String getTitle() {
        return "Searching: "+searchTerm;
    }
    @Override
    public void onRefresh() {
        final ServerRequests sr = new ServerRequests(getActivity(),false);
        sr.searchByPostTitle(searchTerm, new Consumer<Post[]>() {
            @Override
            public void accept(Post[] posts) {
                if (posts == null) {
                    ServerRequests.handleErrors(getContext(), sr);
                    swipeRefreshLayout.setRefreshing(false);
                } else setAdapter(posts);
            }
        });
    }
}
