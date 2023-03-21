package com.example.ovs.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LogInActivity extends AppCompatActivity {

    private EditText userEmail, userPassword;
    private Button loginBtn;
    private TextView forgetPassword;
    private FirebaseAuth mAuth;

    private static final String PREFERENCES = "prefKey";
    private static final String Name = "nameKey";
    private static final String Email = "emailKey";
    private static final String Password = "passwordKey";

    SharedPreferences sharedPreferences;
    StorageReference reference;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        sharedPreferences = getApplicationContext().getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        reference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        findViewById(R.id.dont_have_acc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
            }
        });

        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        loginBtn = findViewById(R.id.login_btn);
        forgetPassword = findViewById(R.id.forget_password);
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();


                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            verifyEmail();
                        }
                        else {
                            Toast.makeText(LogInActivity.this, "User is not found.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    private void verifyEmail() {
        FirebaseUser user = mAuth.getCurrentUser();
        if(user.isEmailVerified()){

            String name = sharedPreferences.getString(Name,null);
            String password = sharedPreferences.getString(Password,null);
            String email = sharedPreferences.getString(Email,null);

            // We first sent email verification
            // Then, we store data in shared preference if user verifies the email
            // then we login and upload data to Firestore
            if(name !=null && password !=null && email !=null){
                String uid = mAuth.getUid();
                startActivity(new Intent(LogInActivity.this,HomeActivity.class));
                finish();
            }
        }
        else{
            mAuth.signOut();
            Toast.makeText(LogInActivity.this, "Please verify your email.", Toast.LENGTH_SHORT).show();
        }
    }
}