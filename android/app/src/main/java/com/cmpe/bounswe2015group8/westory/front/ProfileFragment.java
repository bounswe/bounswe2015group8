package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.PostAdapter;
import com.cmpe.bounswe2015group8.westory.model.Member;
import com.cmpe.bounswe2015group8.westory.model.Post;

import java.util.ArrayList;

/**
 * Created by marslanbenzer on 19.11.2015.
 */
public class ProfileFragment extends NamedFragment implements AdapterView.OnItemClickListener {
    public static final String NAME = "PROFILE";
    private TextView tvName;
    private ListView listView;
    MemberLocalStore memberLocalStore;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        memberLocalStore = new MemberLocalStore(getActivity());
        tvName = (TextView) v.findViewById(R.id.tvHomeName);
        displayMemberDetails();
        listView = (ListView) v.findViewById(R.id.listPosts);
        Member member = memberLocalStore.getLoggedInMember();
        long id=member.getId();
        ServerRequests sr = new ServerRequests(getActivity());
        sr.getAllPosts(new Consumer<Post[]>() {
            @Override
            public void accept(Post[] posts) {
                setAdapter(posts);
            }
        });
        return v;
    }

    private void setAdapter(Post[] posts) {
        ArrayList<Post> usersPosts = new ArrayList<Post>();
        Member member = memberLocalStore.getLoggedInMember();
        long id=member.getId();
        for (Post p:posts){
            if(p.getOwnerId()==id)
                usersPosts.add(p);
        }
        Post[] postArr = new Post[usersPosts.size()];
        postArr = usersPosts.toArray(postArr);
        listView.setAdapter(new PostAdapter(getActivity(),R.layout.post_small, postArr));
    }

    public void displayMemberDetails(){
        Member member = memberLocalStore.getLoggedInMember();
        tvName.setText(member.getUsername());

    }

    @Override
    String getName() {
        return NAME;
    }

    @Override
    String getTitle() {
        return NAME;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}