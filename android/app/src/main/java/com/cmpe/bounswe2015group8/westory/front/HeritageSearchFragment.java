package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Heritage;

/**
 * Fragment for displaying search results in Heritage names.
 * @see Heritage
 * @author xyllan
 * Date: 05.01.2016
 */
public class HeritageSearchFragment extends HeritagesFragment {
    public static final String NAME = "Heritage Search";
    private String searchTerm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchTerm = getArguments().getString("searchTerm","");
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
        return "Searching: "+searchTerm;
    }
    @Override
    public void onRefresh() {
        final ServerRequests sr = new ServerRequests(getActivity(),false);
        sr.searchByHeritageName(searchTerm,new Consumer<Heritage[]>() {
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
