package com.cmpe.bounswe2015group8.westory.front.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.MainActivity;
import com.cmpe.bounswe2015group8.westory.front.NamedFragment;
import com.cmpe.bounswe2015group8.westory.front.PostViewFragment;
import com.cmpe.bounswe2015group8.westory.model.Member;
import com.cmpe.bounswe2015group8.westory.model.Post;

/**
 * Created by xyllan on 17.11.2015.
 */
public class PostAdapter extends ArrayAdapter<Post> {
    private FragmentActivity context;
    public PostAdapter(FragmentActivity context, int resource, Post[] objects) {
        super(context, resource, objects);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = (convertView == null) ? inflater.inflate(R.layout.post_small,parent,false) : convertView;
        TextView tvTitle = (TextView) v.findViewById(R.id.tvPostSmallTitle);
        TextView tvOwner = (TextView) v.findViewById(R.id.tvPostSmallOwner);
        TextView tvCreationDate = (TextView) v.findViewById(R.id.tvPostSmallCreationDateValue);
        TextView tvContent = (TextView) v.findViewById(R.id.tvPostSmallContent);
        TextView tvSeeMore = (TextView) v.findViewById(R.id.tvPostSmallSeeMore);
        final TextView tvVoteCount = (TextView) v.findViewById(R.id.tvPostVoteCount);
        final Post p = getItem(position);
        tvTitle.setText(p.getTitle());
        tvOwner.setText(context.getResources().getString(R.string.generic_by_username, Long.toString(p.getOwnerId())));
        tvCreationDate.setText(p.getPostDate());

        tvContent.setText(p.getContent()+"- "+p.getId());
        ImageButton btnDownVote = (ImageButton) v.findViewById(R.id.btnPostDownVote);
        ImageButton btnUpVote = (ImageButton) v.findViewById(R.id.btnPostUpVote);


        tvVoteCount.setText("" + p.getVoteCount());
        tvSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NamedFragment nf = new PostViewFragment();
                Bundle b = new Bundle();
                b.putParcelable("post", p);
                nf.setArguments(b);
                MainActivity.beginFragment(context, nf);
            }
        });
        btnUpVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerRequests sr = new ServerRequests(getContext());
                MemberLocalStore memberLocalStore = new MemberLocalStore(getContext());
                Member m = memberLocalStore.getLoggedInMember();
                sr.votePost(p, true, m.getId(), new Consumer<String>() {
                    @Override
                    public void accept(String vote) {
                        System.out.println("mumu: " + vote);
                        tvVoteCount.setText("" + vote);
                    }
                });
            }
        });

        btnDownVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerRequests sr = new ServerRequests(getContext());
                MemberLocalStore memberLocalStore = new MemberLocalStore(getContext());
                Member m= memberLocalStore.getLoggedInMember();
                sr.votePost(p, false, m.getId(), new Consumer<String>() {
                    @Override
                    public void accept(String vote) {
                        System.out.println("mumu: " + vote);
                        tvVoteCount.setText("" + vote);
                    }
                });
            }
        });
        return v;
    }

}