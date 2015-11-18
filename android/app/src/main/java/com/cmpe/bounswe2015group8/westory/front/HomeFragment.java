package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.model.Member;

/**
 * Fragment for the landing/home page of the users of the app.
 * This will be populated later. Currently is a placeholder.
 * @author xyllan
 * @author Buğrahan Memiş
 * Date: 07.11.2015.
 */
public class HomeFragment extends NamedFragment implements View.OnClickListener{
    public static final String NAME = "HOME";
    Button btnLogout;
    TextView tvUsername, tvEmail;
    MemberLocalStore memberLocalStore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_home,container,false);

        memberLocalStore = new MemberLocalStore(getActivity());

        tvUsername = (TextView) v.findViewById(R.id.tvHomeUsername);
        tvEmail = (TextView) v.findViewById(R.id.tvHomeEmail);
        btnLogout = (Button) v.findViewById(R.id.btnHomeLogout);

        btnLogout.setOnClickListener(this);
        displayMemberDetails();
        return v;
    }
    public void displayMemberDetails(){
        Member member = memberLocalStore.getLoggedInMember();

        tvUsername.setText(member.getUsername());
        tvEmail.setText(member.getEmail());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHomeLogout:
                memberLocalStore.clearMemberData();
                memberLocalStore.setMemberLoggedIn(false);
                MainActivity.beginFragment(getActivity(), new LoginFragment());
                break;
        }
    }
    @Override
    public String getName() {
        return NAME;
    }
    @Override
    public String getTitle() {
        return getResources().getString(R.string.app_name);
    }
}
