package com.example.ovs.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ovs.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.common.value.qual.StringVal;

public class ViewPollActivity extends AppCompatActivity implements View.OnClickListener {

    private Button option1,option2,option3,option4,refresh,goback;
    private TextView result1,result2,result3,result4,pollname;
    FirebaseUser user;
    FirebaseAuth auth;
    String childKey, key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_poll);

        option1 = (Button) findViewById(R.id.option1_vote);
        option1.setOnClickListener(this);

        option2 = (Button) findViewById(R.id.option2_vote);
        option2.setOnClickListener(this);

        option3 = (Button) findViewById(R.id.option3_vote);
        option3.setOnClickListener(this);

        option4 = (Button) findViewById(R.id.option4_vote);
        option4.setOnClickListener(this);

        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(this);

        goback = (Button) findViewById(R.id.goback);
        goback.setOnClickListener(this);

        result1 = (TextView) findViewById(R.id.result1);
        result1.setOnClickListener(this);

        result2 = (TextView) findViewById(R.id.result2);
        result2.setOnClickListener(this);

        result3 = (TextView) findViewById(R.id.result3);
        result3.setOnClickListener(this);

        result4 = (TextView) findViewById(R.id.result4);
        result4.setOnClickListener(this);

        pollname= findViewById(R.id.pollname);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("key")) {
            key = intent.getStringExtra("key");
            pollname.setText(key);

            DatabaseReference pollRef = FirebaseDatabase.getInstance().getReference().child("Polls").child(key).child("votes");

            // Add a listener to retrieve the value of the child node
            pollRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int childIndex = 0;
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        childKey = childSnapshot.getKey();
                        switch(childIndex) {
                            case 0:
                                option1.setText(childKey);
                                break;
                            case 1:
                                option2.setText(childKey);
                                break;
                            case 2:
                                option3.setText(childKey);
                                break;
                            case 3:
                                option4.setText(childKey);
                                break;
                        }
                        childIndex++;
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

    }

    @Override
    public void onClick(View view) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String email = user.getEmail();

        switch (view.getId()){

            case R.id.option1_vote:
                myRef.child("Polls").child(key).child("voters").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(ViewPollActivity.this, "Already voted", Toast.LENGTH_SHORT).show();
                        } else {
                            Voter Voter = new Voter(email);
                            myRef.child("Polls").child(key).child("voters").push().setValue(Voter);
                            myRef.child("Polls").child(key).child("votes").child(option1.getText().toString()).push().setValue(1);
                            Toast.makeText(ViewPollActivity.this, "Vote counted", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                break;
            case R.id.option2_vote:
                myRef.child("Polls").child(key).child("voters").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(ViewPollActivity.this, "Already voted", Toast.LENGTH_SHORT).show();
                        } else {
                            Voter Voter = new Voter(email);
                            myRef.child("Polls").child(key).child("voters").push().setValue(Voter);
                            myRef.child("Polls").child(key).child("votes").child(option2.getText().toString()).push().setValue(1);
                            Toast.makeText(ViewPollActivity.this, "Vote counted", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                break;
            case R.id.option3_vote:
                myRef.child("Polls").child(key).child("voters").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(ViewPollActivity.this, "Already voted", Toast.LENGTH_SHORT).show();
                        } else {
                            Voter Voter = new Voter(email);
                            myRef.child("Polls").child(key).child("voters").push().setValue(Voter);
                            myRef.child("Polls").child(key).child("votes").child(option3.getText().toString()).push().setValue(1);
                            Toast.makeText(ViewPollActivity.this, "Vote counted", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                break;
            case R.id.option4_vote:
                myRef.child("Polls").child(key).child("voters").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(ViewPollActivity.this, "Already voted", Toast.LENGTH_SHORT).show();
                        } else {
                            Voter Voter = new Voter(email);
                            myRef.child("Polls").child(key).child("voters").push().setValue(Voter);
                            myRef.child("Polls").child(key).child("votes").child(option4.getText().toString()).push().setValue(1);
                            Toast.makeText(ViewPollActivity.this, "Vote counted", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                break;



            case R.id.refresh:
                DatabaseReference optionRef = myRef.child("Polls").child(key).child("votes").child(option1.getText().toString());
                optionRef.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            long count = dataSnapshot.getChildrenCount();
                            result1.setText(option1.getText().toString()+" has "+ String.valueOf(count)+" votes");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
                DatabaseReference optionRef2 = myRef.child("Polls").child(key).child("votes").child(option2.getText().toString());
                optionRef2.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            long count = dataSnapshot.getChildrenCount();
                            result2.setText(option2.getText().toString()+" has "+ String.valueOf(count)+" votes");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
                DatabaseReference optionRef3 = myRef.child("Polls").child(key).child("votes").child(option3.getText().toString());
                optionRef3.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            long count = dataSnapshot.getChildrenCount();
                            result3.setText(option3.getText().toString()+" has "+ String.valueOf(count)+" votes");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
                DatabaseReference optionRef4 = myRef.child("Polls").child(key).child("votes").child(option4.getText().toString());
                optionRef4.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            long count = dataSnapshot.getChildrenCount();
                            result4.setText(option4.getText().toString()+" has "+ String.valueOf(count)+" votes");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
                break;

            case R.id.goback:
                onBackPressed();
                break;
        }
    }
}