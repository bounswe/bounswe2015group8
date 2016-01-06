package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Post;

/**
 * Created by xyllan on 06.01.2016.
 */
public class PostFeedFragment extends PostsFragment {
    public static final String NAME = "Post Feed";

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
        sr.postFeed(new MemberLocalStore(getContext()).getLoggedInMember().getId(), new Consumer<Post[]>() {
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
