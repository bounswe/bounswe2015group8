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
import com.cmpe.bounswe2015group8.westory.front.ProfileFragment;
import com.cmpe.bounswe2015group8.westory.model.Comment;
import com.cmpe.bounswe2015group8.westory.model.Member;

/**
 * Created by marslanbenzer on 27.11.2015.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {
    //private Activity context;
    private FragmentActivity context;
    public CommentAdapter(FragmentActivity context, int resource, Comment[] objects) {
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
        final Comment c = getItem(position);
        tvOwner.setText(context.getResources().getString(R.string.generic_by_username,c.getUsername()));
        String s  =c.getPostDate();
        tvCreationDateValue.setText(context.getString(R.string.generic_created_at,s));
        tvContent.setText(c.getContent());
        ImageButton btnDownVote = (ImageButton) v.findViewById(R.id.btnCommentDownVote);
        ImageButton btnUpVote = (ImageButton) v.findViewById(R.id.btnCommentUpVote);
        tvVoteCount.setText("" + c.getNetCount());
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.tvCommentSmallOwner:
                        NamedFragment nfp = new ProfileFragment();
                        Bundle bp = new Bundle();
                        bp.putLong("memberId", c.getOwnerId());
                        nfp.setArguments(bp);
                        MainActivity.beginFragment(context, nfp);
                        break;
                    case R.id.btnCommentUpVote:
                        final ServerRequests sr = new ServerRequests(getContext());
                        MemberLocalStore memberLocalStore = new MemberLocalStore(getContext());
                        Member m = memberLocalStore.getLoggedInMember();
                        sr.voteComment(c, true, m.getId(), new Consumer<String>() {
                            @Override
                            public void accept(String vote) {
                                if(vote == null) ServerRequests.handleErrors(context,sr);
                                else tvVoteCount.setText(vote);

                            }
                        });
                        break;
                    case R.id.btnCommentDownVote:
                        final ServerRequests sr2 = new ServerRequests(getContext());
                        MemberLocalStore memberLocalStore2 = new MemberLocalStore(getContext());
                        Member m2 = memberLocalStore2.getLoggedInMember();
                        sr2.voteComment(c, false, m2.getId(), new Consumer<String>() {
                            @Override
                            public void accept(String vote) {
                                if(vote == null) ServerRequests.handleErrors(context,sr2);
                                else tvVoteCount.setText(vote);
                            }
                        });
                        break;
                }
            }
        };
        tvOwner.setOnClickListener(listener);
        btnUpVote.setOnClickListener(listener);
        btnDownVote.setOnClickListener(listener);

        return v;
    }

}
