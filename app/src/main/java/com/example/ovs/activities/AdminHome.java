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

    private TextView userlogin, adminSignUp;
    private EditText admin_user, admin_password;
    private Button adminLogin;

   // private FirebaseAuth mAuth;
   // private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        userlogin = (TextView) findViewById(R.id.login);
        userlogin.setOnClickListener(this);

        adminSignUp = (TextView) findViewById(R.id.adminsignup);
        adminSignUp.setOnClickListener(this);

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

            case R.id.login_btn: //MAYBE MAKE AN ADMIN LOGIN FUNCTION???
                startActivity(new Intent(this,AdminMainActivity.class));
                break;

            case R.id.adminsignup:
                startActivity(new Intent(this,AdminSignUp.class));

        }
    }
}