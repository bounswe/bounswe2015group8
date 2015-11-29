package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.HeritageViewAdapter;
import com.cmpe.bounswe2015group8.westory.front.adapter.PostAdapter;
import com.cmpe.bounswe2015group8.westory.model.Heritage;
import com.cmpe.bounswe2015group8.westory.model.Post;

import java.util.Arrays;

/**
 * Fragment for viewing heritage objects.
 * Needs a {@link Heritage} object to be passed in as argument.
 * Contains links to {@link HeritageEditFragment} for editing and {@link PostEditFragment}
 * for posting about this object.
 * @see Heritage
 * @author xyllan
 * Date: 15.11.2015.
 */
public class HeritageViewFragment extends NamedFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String NAME = "HERITAGE_VIEW";
    private Button btnEdit, btnAddPost;
    private TextView tvPlace, tvCreationDate, tvDescription;
    private Heritage heritage;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ExpandableListView elvData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemberLocalStore memberLocalStore = new MemberLocalStore(getActivity());
        View v = inflater.inflate(R.layout.fragment_heritage_view,container,false);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.srlHeritageView);
        swipeRefreshLayout.setOnRefreshListener(this);
        elvData = (ExpandableListView) v.findViewById(R.id.lvHeritageViewPosts);
        View header = inflater.inflate(R.layout.fragment_heritage_view_header,elvData,false);
        tvPlace = (TextView) header.findViewById(R.id.tvHeritageViewPlaceValue);
        tvCreationDate = (TextView) header.findViewById(R.id.tvHeritageViewCreationDateValue);
        tvDescription = (TextView) header.findViewById(R.id.tvHeritageViewDescriptionValue);
        btnEdit = (Button) header.findViewById(R.id.btnHeritageEdit);
        btnAddPost = (Button) header.findViewById(R.id.btnHeritageNewPost);
        if(memberLocalStore.getUserLoggedIn()) {
            btnEdit.setOnClickListener(this);
            btnAddPost.setOnClickListener(this);
        } else {
            btnEdit.setVisibility(View.GONE);
            btnAddPost.setVisibility(View.GONE);
        }
        initViews(this.getArguments());
        elvData.addHeaderView(header);
        manualRefresh();
        return v;
    }
    private void initViews(Bundle args) {
        heritage = args.getParcelable("heritage");
        tvPlace.setText(heritage.getPlace());
        tvCreationDate.setText(heritage.getPostDate());
        tvDescription.setText(heritage.getDescription());
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnHeritageEdit:
                NamedFragment nf = new HeritageEditFragment();
                Bundle b = new Bundle();
                b.putBoolean("isNew", false);
                b.putParcelable("heritage",heritage);
                nf.setArguments(b);
                MainActivity.beginFragment(getActivity(),nf);
                break;
            case R.id.btnHeritageNewPost:
                NamedFragment pef = new PostEditFragment();
                Bundle b2 = new Bundle();
                b2.putLong("heritageId",heritage.getId());
                pef.setArguments(b2);
                MainActivity.beginFragment(getActivity(),pef);
                break;
        }
    }
    @Override
    String getName() {
        return NAME;
    }
    @Override
    String getTitle() {
        return heritage.getName();
    }

    @Override
    public void onRefresh() {
        ServerRequests sr = new ServerRequests(getActivity());
        sr.getPostsByHeritageId(heritage.getId(), new Consumer<Post[]>() {
            @Override
            public void accept(Post[] posts) {
                elvData.setAdapter(new HeritageViewAdapter(getActivity(),Arrays.asList(posts),null,null));
                heritage.setPosts(Arrays.asList(posts));
            }
        });
    }
    private void manualRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }
}
