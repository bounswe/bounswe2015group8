package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Heritage;

/**
 * Fragment for displaying recommended {@link Heritage} objects to a user.
 * @see Heritage
 * @author xyllan
 * Date: 06.01.2016
 */
public class RecommendationFragment extends HeritagesFragment {
    public static final String NAME = "Recommendations";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        fab.hide();
        return v;
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
        MemberLocalStore memberLocalStore = new MemberLocalStore(getContext());
        sr.recommendHeritages(memberLocalStore.getLoggedInMember().getId(),new Consumer<Heritage[]>() {
            @Override
            public void accept(Heritage[] heritages) {
                if(heritages == null) {
                    ServerRequests.handleErrors(getContext(),sr);
                    swipeRefreshLayout.setRefreshing(false);
                }
                else setAdapter(heritages);
            }
        });
    }
}
