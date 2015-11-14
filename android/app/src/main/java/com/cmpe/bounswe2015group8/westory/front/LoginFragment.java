package com.cmpe.bounswe2015group8.westory.front;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.GetCallback;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Member;
import com.cmpe.bounswe2015group8.westory.model.Requestable;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginFragment extends NamedFragment implements View.OnClickListener {
    public static final String NAME = "LOGIN";
    Button btnLogin;
    EditText etUsername, etPassword;
    TextView tvLinkToRegisterScreen;
    MemberLocalStore memberLocalStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_login,container,false);

        memberLocalStore = new MemberLocalStore(getActivity());

        etUsername = (EditText) v.findViewById(R.id.etUsername);
        etPassword = (EditText) v.findViewById(R.id.etPassword);
        btnLogin = (Button) v.findViewById(R.id.btnLogin);
        tvLinkToRegisterScreen = (TextView) v.findViewById(R.id.tvLinkToRegisterScreen);

        btnLogin.setOnClickListener(this);
        tvLinkToRegisterScreen.setOnClickListener(this);
        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                Member member = new Member(username, password,"","");   //TODO (HALFDONE) take the real values. and then, in the same way, add them to main activity.

                authenticate(member);

                break;
            case R.id.tvLinkToRegisterScreen:
                MainActivity.beginFragment(getActivity(), new RegisterFragment());
                break;
        }
    }
    private void authenticate(Member member){
        ServerRequests serverRequests = new ServerRequests(getActivity());
//        LoginRequestable r = new LoginRequestable(member);
//        serverRequests.fetchDataInBackground(r, r, new GetCallback<LoginRequestable>() {
//            @Override
//            public void done(LoginRequestable r) {
//                if (r == null) {
//                    showErrorMessage();
//                } else {
//                    logMemberIn(r.member);
//                }
//            }
//        }).execute(member);

    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setMessage("Incorrect user details.");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }

    private void logMemberIn(Member member) {
        memberLocalStore.storeUserData(member);
        memberLocalStore.setMemberLoggedIn(true);

        MainActivity.beginFragment(getActivity(), new HomeFragment());
    }
    @Override
    public String getName() {
        return NAME;
    }
}
