package com.cmpe.bounswe2015group8.westory.front;

import android.app.Fragment;
import android.content.Context;

/**
 * Created by xyllan on 07.11.2015.
 */
public abstract class NamedFragment extends Fragment {
    abstract String getName();
    abstract String getTitle();

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getActionBar().setTitle(getTitle());
    }

}
