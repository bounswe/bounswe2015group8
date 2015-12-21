package com.cmpe.bounswe2015group8.westory.front.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.MainActivity;
import com.cmpe.bounswe2015group8.westory.front.NamedFragment;
import com.cmpe.bounswe2015group8.westory.front.ProfileFragment;
import com.cmpe.bounswe2015group8.westory.front.TheTask;
import com.cmpe.bounswe2015group8.westory.model.Member;

/**
 * Created by marslanbenzer on 20.12.2015.
 */
public class MemberAdapter extends ArrayAdapter<Member> {
    private FragmentActivity context;

    public MemberAdapter(FragmentActivity context, int resource, Member[] objects) {
        super(context, resource, objects);
        this.context = context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = (convertView == null) ? inflater.inflate(R.layout.member_small, parent, false) : convertView;
        final TextView tvFollowers = (TextView) v.findViewById(R.id.tvMemberFollowers);
        final TextView tvFollowing = (TextView) v.findViewById(R.id.tvMemberFollowing);
        final TextView tvUserName = (TextView) v.findViewById(R.id.tvMemberUserName);
        final ImageView ivProfile=(ImageView) v.findViewById(R.id.ivMemberProfSmall);
        ServerRequests sr=new ServerRequests(getContext());
        final Member m=getItem(position);
        sr.getMemberById(m.getId(), new Consumer<Member>() {
            @Override
            public void accept(Member member) {
                new TheTask(member.getProfilePicture(), ivProfile).execute();
                tvFollowers.setText("Followers: " + member.getFollowers().size());
                tvFollowing.setText("Following: " + member.getFollowedMembers().size());
                tvUserName.setText(member.getUsername());
            }
        });
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.tvMemberFollowers:
                    case R.id.tvMemberFollowing:
                    case R.id.tvMemberUserName:
                    case R.id.ivMemberProfSmall:
                        NamedFragment nfp = new ProfileFragment();
                        Bundle bp = new Bundle();
                        bp.putBoolean("isLogged",false);
                        bp.putLong("memberId",m.getId());
                        nfp.setArguments(bp);
                        MainActivity.beginFragment(context,nfp);
                        break;
                }
            }
        };
        tvFollowers.setOnClickListener(listener);
        tvFollowing.setOnClickListener(listener);
        tvUserName.setOnClickListener(listener);
        ivProfile.setOnClickListener(listener);

        return v;
    }
    public void onClick(View v) {

    }
}