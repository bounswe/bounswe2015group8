package com.cmpe.bounswe2015group8.westory.front.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.cmpe.bounswe2015group8.westory.model.Comment;
import com.cmpe.bounswe2015group8.westory.model.Member;

import java.text.SimpleDateFormat;

/**
 * Created by marslanbenzer on 27.11.2015.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {
    private Activity context;
    private ImageButton btnUpVote,btnDownVote;
    private Comment c;
    public CommentAdapter(Activity context, int resource, Comment[] objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.comment_small,parent,false);
        TextView tvOwner = (TextView) v.findViewById(R.id.tvCommentSmallOwner);
        TextView tvCreationDateValue = (TextView) v.findViewById(R.id.tvCommentSmallCreationDate);
        TextView tvContent = (TextView) v.findViewById(R.id.tvCommentSmallContent);
        final TextView tvVoteCount = (TextView) v.findViewById(R.id.tvCommentVoteCount);
        c = getItem(position);
        tvOwner.setText(context.getResources().getString(R.string.generic_by_username,c.getUsername()));
        String s  =c.getPostDate();
        tvCreationDateValue.setText(s);
        tvContent.setText(c.getContent());
        btnDownVote = (ImageButton) v.findViewById(R.id.btnCommentDownVote);
        btnUpVote = (ImageButton) v.findViewById(R.id.btnCommentUpVote);
        tvVoteCount.setText("" + c.getNetCount());
        btnUpVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerRequests sr = new ServerRequests(getContext());
                MemberLocalStore memberLocalStore = new MemberLocalStore(getContext());
                Member m = memberLocalStore.getLoggedInMember();
                c = getItem(position);
                sr.voteComment(c, true, m.getId(), new Consumer<String>() {
                    @Override
                    public void accept(String vote) {
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
                Member m = memberLocalStore.getLoggedInMember();
                c = getItem(position);
                sr.voteComment(c, false, m.getId(), new Consumer<String>() {
                    @Override
                    public void accept(String vote) {
                        tvVoteCount.setText("" + vote);
                    }
                });
            }
        });

        return v;
    }

}
