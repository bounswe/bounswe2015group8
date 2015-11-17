package com.cmpe.bounswe2015group8.westory.front.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.front.MainActivity;
import com.cmpe.bounswe2015group8.westory.front.NamedFragment;
import com.cmpe.bounswe2015group8.westory.front.PostViewFragment;
import com.cmpe.bounswe2015group8.westory.model.Post;

/**
 * Created by xyllan on 17.11.2015.
 */
public class PostAdapter extends ArrayAdapter<Post> {
    private Activity context;
    public PostAdapter(Activity context, int resource, Post[] objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.post_small,parent,false);
        TextView tvTitle = (TextView) v.findViewById(R.id.tvPostSmallTitle);
        TextView tvOwner = (TextView) v.findViewById(R.id.tvPostSmallOwner);
        TextView tvCreationDate = (TextView) v.findViewById(R.id.tvPostSmallCreationDateValue);
        TextView tvContent = (TextView) v.findViewById(R.id.tvPostSmallContent);
        TextView tvSeeHeritages = (TextView) v.findViewById(R.id.tvPostSmallSeeHeritages);
        final Post p = getItem(position);
        tvTitle.setText(p.getTitle());
        tvOwner.setText(context.getResources().getString(R.string.generic_by)+" "+p.getOwnerId());
        tvCreationDate.setText(p.getPostDate());
        tvContent.setText(p.getContent());
        tvSeeHeritages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NamedFragment nf = new PostViewFragment();
                nf.setArguments(p.getBundle());
                MainActivity.beginFragment(context, nf);
            }
        });
        return v;
    }
}