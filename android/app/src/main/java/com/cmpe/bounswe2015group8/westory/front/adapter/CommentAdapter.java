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
        TextView tvCreationDate = (TextView) v.findViewById(R.id.tvCommentSmallCreationDate);
        TextView tvCreationDateValue = (TextView) v.findViewById(R.id.tvCommentSmallCreationDate);
        TextView tvContent = (TextView) v.findViewById(R.id.tvCommentSmallContent);
        final TextView tvVoteCount = (TextView) v.findViewById(R.id.tvCommentVoteCount);
        c = getItem(position);
        tvOwner.setText(context.getResources().getString(R.string.generic_by) + " " + c.getOwnerId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String s  =c.getPostDate();
        System.out.println(s);
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
                        System.out.println("mumu: " + vote + " - " + c.getVotes().size());
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
                        System.out.println("mumu: " + vote + " - " + c.getVotes().size());
                        tvVoteCount.setText("" + vote);
                    }
                });
            }
        });

        return v;
    }

}
