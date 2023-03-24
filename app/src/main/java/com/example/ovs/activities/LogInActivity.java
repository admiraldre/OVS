package com.example.ovs.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView forgotPassword;
    private EditText user_email, user_password;
    private Button logIn, register, admin;

    private FirebaseAuth mAuth;

    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        logIn = (Button) findViewById(R.id.login_btn);
        logIn.setOnClickListener(this);

        user_email = (EditText) findViewById(R.id.user_email);
        user_password = (EditText) findViewById(R.id.user_password);

        mAuth = FirebaseAuth.getInstance();

        forgotPassword = (TextView) findViewById(R.id.forget_password);
        forgotPassword.setOnClickListener(this);

        admin = (Button) findViewById(R.id.admin_login);
        admin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case R.id.login_btn:
                userLogIn();
                break;

            case R.id.forget_password:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;

            case R.id.admin_login:
                startActivity(new Intent(this, AdminHome.class));
                break;
        }
    }

    private void userLogIn() {
        String email = user_email.getText().toString().trim();
        String password = user_password.getText().toString().trim();

        if(email.isEmpty()){
            user_email.setError("Email is required!");
            user_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            user_email.setError("Please enter a valid email!");
            user_email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            user_password.setError("Password is required!");
            user_password.requestFocus();
            return;
        }
        if(password.length() < 6){
            user_password.setError("Password must be a minimum of 6 characters!");
            user_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()) {
                        //redirect to user profile
                        startActivity(new Intent(LogInActivity.this, HomeActivity.class));
                    }
                    else {
                        user.sendEmailVerification();
                        Toast.makeText(LogInActivity.this, "Check your email to verify your account", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(LogInActivity.this, "Failed to log in. Please check your log in information.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}