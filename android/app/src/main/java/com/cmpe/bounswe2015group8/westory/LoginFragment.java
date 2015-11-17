package com.cmpe.bounswe2015group8.westory;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends NamedFragment implements View.OnClickListener {
    public static final String NAME = "LOGIN";
    Button btnLogin;
    EditText etUsername, etPassword;
    TextView tvLinkToRegisterScreen;
    UserLocalStore userLocalStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_login,container,false);

        userLocalStore = new UserLocalStore(getActivity());

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
                User user = new User(username, password);   //TODO (HALFDONE) take the real values. and then, in the same way, add them to main activity.

                authenticate(user);

                break;
            case R.id.tvLinkToRegisterScreen:
                MainActivity.beginFragment(getActivity(), new RegisterFragment());
                break;
        }
    }

    private void authenticate(User user){
        ServerRequests serverRequests = new ServerRequests(getActivity());
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    logUserIn(returnedUser );
                }
            }
        });

    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setMessage("Incorrect user details.");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        MainActivity.beginFragment(getActivity(), new HomeFragment());
    }
    @Override
    public String getName() {
        return NAME;
    }
}