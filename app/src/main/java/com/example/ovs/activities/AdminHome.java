package com.example.ovs.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ovs.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminHome extends AppCompatActivity implements View.OnClickListener {

    private TextView userlogin;
    private EditText admin_user, admin_password;
    private Button adminLogin;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        userlogin = (TextView) findViewById(R.id.login);
        userlogin.setOnClickListener(this);

        adminLogin = (Button) findViewById(R.id.login_btn);
        adminLogin.setOnClickListener(this);

        admin_user = (EditText) findViewById(R.id.admin_username);
        admin_user.setOnClickListener(this);

        admin_password = (EditText) findViewById(R.id.admin_password);
        admin_password.setOnClickListener(this);

        // progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                startActivity(new Intent(this,LogInActivity.class));
                break;

            case R.id.login_btn:
                adminLogin();
                break;
        }
    }

    private void adminLogin() {
        String username = admin_user.getText().toString().trim();
        String adminpass = admin_password.getText().toString().trim();

        if(username.isEmpty()){
            admin_user.setError("Admin Username is required!");
            admin_user.requestFocus();
            return;
        }
        if(adminpass.isEmpty()){
            admin_password.setError("Admin Password is required!");
            admin_password.requestFocus();
            return;
        }
        startActivity(new Intent(AdminHome.this,AdminMainActivity.class));
    }
}