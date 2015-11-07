package com.cmpe.bounswe2015group8.westory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by xyllan on 07.11.2015.
 */
public class HomeFragment extends NamedFragment implements View.OnClickListener{
    public static final String NAME = "HOME";
    Button btnLogout;
    EditText etUsername, etMail;
    UserLocalStore userLocalStore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_login,container,false);

        userLocalStore = new UserLocalStore(getActivity());

        etUsername = (EditText) v.findViewById(R.id.etUsername);
        etMail = (EditText) v.findViewById(R.id.etMail);
        btnLogout = (Button) v.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(this);
        displayUserDetails();
        return v;
    }
    public void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();

        etUsername.setText(user.username);
        etMail.setText(user.mail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                MainActivity.beginFragment(getActivity(), new LoginFragment());
                break;
        }
    }
    @Override
    public String getName() {
        return NAME;
    }
}
