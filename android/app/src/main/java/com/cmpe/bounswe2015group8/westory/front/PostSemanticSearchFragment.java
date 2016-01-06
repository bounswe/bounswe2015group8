package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Post;
import com.cmpe.bounswe2015group8.westory.model.Tag;

/**
 * Created by xyllan on 06.01.2016.
 */
public class PostSemanticSearchFragment extends PostsFragment {
    public static final String NAME = "Post Semantic Search";
    private Tag searchTag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchTag = getArguments().getParcelable("searchTag");
        View v = super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    @Override
    String getName() {
        return NAME;
    }

    @Override
    String getTitle() {
        return "Searching: " + searchTag.getWholeTag();
    }
    @Override
    public void onRefresh() {
        final ServerRequests sr = new ServerRequests(getActivity(),false);
        sr.searchPostsByTag(searchTag, new Consumer<Post[]>() {
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
