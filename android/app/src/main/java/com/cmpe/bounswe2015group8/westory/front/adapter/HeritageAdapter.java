package com.cmpe.bounswe2015group8.westory.front.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.front.HeritageViewFragment;
import com.cmpe.bounswe2015group8.westory.front.MainActivity;
import com.cmpe.bounswe2015group8.westory.front.NamedFragment;
import com.cmpe.bounswe2015group8.westory.model.Heritage;

/**
 * Created by xyllan on 17.11.2015.
 */
public class HeritageAdapter extends ArrayAdapter<Heritage> {
    private FragmentActivity context;
    public HeritageAdapter(FragmentActivity context, int resource, Heritage[] objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.heritage_small,parent,false);
        TextView tvName = (TextView) v.findViewById(R.id.tvHeritageSmallName);
        TextView tvPlace = (TextView) v.findViewById(R.id.tvHeritageSmallPlace);
        TextView tvCreationDate = (TextView) v.findViewById(R.id.tvHeritageSmallCreationDateValue);
        TextView tvDescription = (TextView) v.findViewById(R.id.tvHeritageSmallDescription);
        TextView tvSeePosts = (TextView) v.findViewById(R.id.tvHeritageSmallSeePosts);
        final Heritage h = getItem(position);
        tvName.setText(h.getName());
        tvPlace.setText(h.getPlace());
        tvCreationDate.setText(h.getPostDate());
        tvDescription.setText(h.getDescription());
        tvSeePosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NamedFragment nf = new HeritageViewFragment();
                Bundle b = new Bundle();
                b.putParcelable("heritage",h);
                nf.setArguments(b);
                MainActivity.beginFragment(context, nf);
            }
        });
        return v;
    }
}
