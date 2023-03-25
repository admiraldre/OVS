package com.example.ovs.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ovs.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreatePollActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText pollname, option1, option2, option3, option4;
    private Button createpoll,goback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        pollname = (EditText) findViewById(R.id.pollname);
        pollname.setOnClickListener(this);

        option1 = (EditText) findViewById(R.id.option1);
        option1.setOnClickListener(this);

        option2 = (EditText) findViewById(R.id.option2);
        option2.setOnClickListener(this);

        option3 = (EditText) findViewById(R.id.option3);
        option3.setOnClickListener(this);

        option4 = (EditText) findViewById(R.id.option4);
        option4.setOnClickListener(this);

        createpoll = (Button) findViewById(R.id.createpoll);
        createpoll.setOnClickListener(this);

        goback = (Button) findViewById(R.id.goback);
        goback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createpoll:
                String pollName = pollname.getText().toString().trim();
                String optionOne = option1.getText().toString().trim();
                String optionTwo = option2.getText().toString().trim();
                String optionThree = option3.getText().toString().trim();
                String optionFour = option4.getText().toString().trim();
                if (pollName.isEmpty()) {
                    pollname.setError("Poll Name is required!");
                    pollname.requestFocus();
                    return;
                }
                if (optionOne.isEmpty()) {
                    option1.setError("Minimum of two options are required!");
                    option1.requestFocus();
                    return;
                }
                if (optionTwo.isEmpty()) {
                    option2.setError("Minimum of two options are required!");
                    option2.requestFocus();
                    return;
                }

            {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();

                myRef.child("Polls").child(pollName).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(CreatePollActivity.this, "Poll already exists", Toast.LENGTH_LONG).show();
                        } else {
                            myRef.child("Polls").child(pollName).child("votes").child(optionOne).setValue(0);
                            myRef.child("Polls").child(pollName).child("votes").child(optionTwo).setValue(0);
                            myRef.child("Polls").child(pollName).child("votes").child(optionThree).setValue(0);
                            myRef.child("Polls").child(pollName).child("votes").child(optionFour).setValue(0);
                            myRef.child("Polls").child(pollName).child("voters");
                            Toast.makeText(CreatePollActivity.this, "Poll created", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
                break;
            }
            case R.id.goback:
                onBackPressed();
                break;
        }

    }
}