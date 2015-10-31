package com.cmpe.bounswe2015group8.culturalheritage2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends ActionBarActivity implements View.OnClickListener {

    Button btnLogin;
    EditText etUsername, etPassword;
    TextView tvLinkToRegisterScreen;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userLocalStore = new UserLocalStore(this);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvLinkToRegisterScreen = (TextView) findViewById(R.id.tvLinkToRegisterScreen);

        btnLogin.setOnClickListener(this);
        tvLinkToRegisterScreen.setOnClickListener(this);

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
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    private void authenticate(User user){
        ServerRequests serverRequests = new ServerRequests(this);
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
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details.");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, MainActivity.class));
    }
}
