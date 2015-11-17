package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
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
public class HeritageViewFragment extends NamedFragment implements View.OnClickListener {
    public static final String NAME = "HERITAGE_VIEW";
    Button btnEdit, btnAddPost;
    TextView tvPlace, tvCreationDate, tvDescription;
    Heritage heritage;
    ListView lvPosts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_heritage_view,container,false);
        tvPlace = (TextView) v.findViewById(R.id.tvHeritageViewPlaceValue);
        tvCreationDate = (TextView) v.findViewById(R.id.tvHeritageViewCreationDateValue);
        tvDescription = (TextView) v.findViewById(R.id.tvHeritageViewDescriptionValue);
        btnEdit = (Button) v.findViewById(R.id.btnHeritageEdit);
        btnAddPost = (Button) v.findViewById(R.id.btnHeritageNewPost);
        lvPosts = (ListView) v.findViewById(R.id.lvHeritageViewPosts);
        btnEdit.setOnClickListener(this);
        btnAddPost.setOnClickListener(this);
        initViews(this.getArguments());
        ServerRequests sr = new ServerRequests(getActivity());
        sr.getPostsByHeritageId(heritage.getId(), new Consumer<Post[]>() {
            @Override
            public void accept(Post[] posts) {
                lvPosts.setAdapter(new PostAdapter(getActivity(), R.layout.post_small,
                        posts));
                heritage.setPosts(Arrays.asList(posts));
            }
        });
        return v;
    }
    private void initViews(Bundle args) {
        heritage = new Heritage(args);
        tvPlace.setText(heritage.getPlace());
        tvCreationDate.setText(heritage.getPostDate());
        tvDescription.setText(heritage.getDescription());
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnHeritageEdit:
                NamedFragment nf = new HeritageEditFragment();
                Bundle b = heritage.getBundle();
                b.putBoolean("isNew", false);
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
}
