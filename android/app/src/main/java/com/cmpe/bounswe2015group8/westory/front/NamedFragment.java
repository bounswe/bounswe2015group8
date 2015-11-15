package com.cmpe.bounswe2015group8.westory.front;

import android.app.Fragment;
import android.content.Context;

/**
 * Base fragment for all fragments used in the application.
 * It contains a name used for fragment transaction purposes and a title for changing
 * the actionbar titles - which is implemented in the {@link #onResume()} method.
 * All classes overriding {@link #onResume()} must call the super method, as is already
 * the case with {@link Fragment#onResume()}
 * @author xyllan
 * Date: 07.11.2015.
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
