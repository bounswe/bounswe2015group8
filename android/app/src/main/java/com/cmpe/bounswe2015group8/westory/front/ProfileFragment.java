package com.cmpe.bounswe2015group8.westory.front;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.CloudinaryAPI;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Member;

/**
 * Created by marslanbenzer on 19.11.2015.
 */
public class ProfileFragment extends NamedFragment implements AdapterView.OnItemClickListener {
    public static final String NAME = "PROFILE";
    private TextView tvName;
    private ListView listView;
    private ImageView iv;
    private Button followBtn;
    Bitmap image ;
    Member member,loggedInMember;
    ProgressDialog pd;
    MemberLocalStore memberLocalStore;
    private String profPicUrl="http://ninjaturtles-animalsdigestion.weebly.com/uploads/2/6/9/3/26932485/612610_orig.jpg";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_profile, container, false);
        iv = (ImageView) v.findViewById(R.id.profilePicture);
        memberLocalStore = new MemberLocalStore(getActivity());
        tvName = (TextView) v.findViewById(R.id.tvHomeName);
        boolean isLogged=true;
        member = memberLocalStore.getLoggedInMember();
        ServerRequests sr = new ServerRequests(getActivity());
        sr.getMemberById(member.getId(), new Consumer<Member>() {
            @Override
            public void accept(Member mm) {
                loggedInMember=mm;
            }
        });
        Long idd= getArguments().getLong("memberId");
        if(idd==0) {
            System.out.println("iff: "+member.getId());
            isLogged = true;
            idd=member.getId();
        }else if(idd!=member.getId()){
            View b = v.findViewById(R.id.addProfilePic);
            b.setVisibility(View.GONE);
            isLogged=false;
        }
        sr = new ServerRequests(getActivity());
        sr.getMemberById(idd, new Consumer<Member>() {
            @Override
            public void accept(Member mm) {
                initViews(mm, v);
            }
        });
        System.out.println("babababababa" + member.getProfilePicture());
        ImageButton imgAdd = (ImageButton) v.findViewById(R.id.addProfilePic);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfilePicture();
            }
        });
        if(isLogged){
            View c = v.findViewById(R.id.btnFollowMember);
            c.setVisibility(View.GONE);
        }
        followBtn= (Button)v.findViewById(R.id.btnFollowMember);
        followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vv) {
                if (!loggedInMember.isFollowed(member)) {
                    ServerRequests sr = new ServerRequests(getActivity());
                    sr.followMember(loggedInMember, member.getId(), new Consumer<Long>() {
                        @Override
                        public void accept(Long followed) {
                            loggedInMember.follow(member);
                            followBtn.setText("Unfollow");
                        }
                    });
                } else {
                    ServerRequests sr = new ServerRequests(getActivity());
                    sr.unfollowMember(loggedInMember, member.getId(), new Consumer<Long>() {
                        @Override
                        public void accept(Long unfollowed) {
                            loggedInMember.unfollow(member);
                            followBtn.setText("Follow");
                        }
                    });
                }
            }
        });
        return v;
    }
    public void onRefresh(){

    }

    public void initViews(Member member2,View v) {
        member = member2;
        tvName.setText(member.getUsername());
        String s=member.getProfilePicture();
        if(!s.equals("")) { //if user has a picture than show it
            profPicUrl = s;
            new TheTask(profPicUrl,iv).execute();
        } else {
            iv.setImageResource(R.drawable.ic_image_black_48dp);
        }

        if(!loggedInMember.isFollowed(member)){
            followBtn.setText("Follow");
        } else {
            followBtn.setText("Unfollow");
        }

        //**************************************tab_layout****************************************
        final TabLayout tabLayout = (TabLayout)v.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Posts"));
        tabLayout.addTab(tabLayout.newTab().setText("Following"));
        tabLayout.addTab(tabLayout.newTab().setText("Followers"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        System.out.println("Tabdan önce: "+member.getUsername());
        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.profilePager);
        final PagerAdapter adapter =new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void uploadProfilePicture() {
        startActivityForResult(CloudinaryAPI.getMediaIntent(0), 0);
    }

    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if(CloudinaryAPI.canHandleRequest(requestCode)) {
            if(resultCode == FragmentActivity.RESULT_OK) {
                Consumer<String> c = new Consumer<String>() {
                    @Override
                    public void accept(String link) {
                        ServerRequests sr = new ServerRequests(getActivity());
                        System.out.println("anam: "+link);
                        final String lll=link;
                        sr.uploadProfilePicture(loggedInMember,link, new Consumer<Member>() {
                            @Override
                            public void accept(Member member) {
                                System.out.println("memis: " + member.getUsername() + "mumus: " + lll);
                            }
                        });
                    }
                };
                new CloudinaryAPI.CloudinaryUploadTask(getActivity(),c).execute(data.getData());
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    public class PagerAdapter extends FragmentPagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    final NamedFragment namedFragment=new PostsFeed();
                    Bundle b= new Bundle();
                    b.putParcelable("member",member);
                    namedFragment.setArguments(b);
                    System.out.println("Case 0: " + member.getUsername());
                    return namedFragment;
                case 1:
                    final NamedFragment membersFragment=new MembersFragment();
                    Bundle bb= new Bundle();
                    Member[]mm=member.getFollowedMembers().toArray(new Member[member.getFollowedMembers().size()]);
                    bb.putParcelableArray("Members",mm);
                    membersFragment.setArguments(bb);
                    return membersFragment;
                case 2:
                    final NamedFragment membersFragment2=new MembersFragment();
                    Bundle bb2= new Bundle();
                    Member[]mm2=member.getFollowers().toArray(new Member[member.getFollowers().size()]);
                    bb2.putParcelableArray("Members",mm2);
                    membersFragment2.setArguments(bb2);
                    return membersFragment2;
                default:
                    final NamedFragment namedFragment3=new PostsFeed();
                    Bundle b3= new Bundle();
                    b3.putParcelable("member",member);
                    namedFragment3.setArguments(b3);
                    return namedFragment3;
            }
        }



        @Override
        public int getCount() {
            return 3;
        }
    }
    @Override
    String getName() {
        return NAME;
    }

    @Override
    String getTitle() {
        return NAME;
    }


}