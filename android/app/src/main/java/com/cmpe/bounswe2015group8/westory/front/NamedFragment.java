package com.cmpe.bounswe2015group8.westory.front;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.cmpe.bounswe2015group8.westory.R;

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
    public static boolean hasAsciiError(Context context, EditText... texts) {
        if(containsNonAscii(texts)) {
            Toast.makeText(context, R.string.asciiError, Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
    public static boolean containsNonAscii(EditText... texts) {
        for(EditText text : texts) {
            if(!isStringAscii(text.getText().toString())) return true;
        }
        return false;
    }
    public static boolean isStringAscii(String in) {
        return in.matches("\\A\\p{ASCII}*\\z");
    }
    abstract String getName();
    abstract String getTitle();

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getTitle());
    }


}
