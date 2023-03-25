package com.example.ovs.activities;

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

public class ViewPollActivity extends AppCompatActivity implements View.OnClickListener {

    private Button option1,option2,option3,option4,refresh,goback;
    private TextView result1,result2,result3,result4;
    FirebaseUser user;
    FirebaseAuth auth;

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

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String email = user.getEmail();

   /*     switch (view.getId()){
            case R.id.option1_vote:
                myRef.child("Polls").child(pollName).child("voters").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(ViewPollActivity.this, "Already voted", Toast.LENGTH_SHORT).show();
                        } else {
                            Voter Voter = new Voter(email);
                            myRef.child("Polls").child(pollName).child("voters").push().setValue(Voter);
                            myRef.child("Polls").child(pollName).child("votes").child(optionONE).push().setValue(1);
                            Toast.makeText(ViewPollActivity.this, "Vote counted", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                break;
            case R.id.option2_vote:
                myRef.child("Polls").child(pollName).child("voters").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(ViewPollActivity.this, "Already voted", Toast.LENGTH_SHORT).show();
                        } else {
                            Voter Voter = new Voter(email);
                            myRef.child("Polls").child(pollName).child("voters").push().setValue(Voter);
                            myRef.child("Polls").child(pollName).child("votes").child(optionTWO).push().setValue(1);
                            Toast.makeText(ViewPollActivity.this, "Vote counted", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                break;
            case R.id.option3_vote:
                myRef.child("Polls").child(pollName).child("voters").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(ViewPollActivity.this, "Already voted", Toast.LENGTH_SHORT).show();
                        } else {
                            Voter Voter = new Voter(email);
                            myRef.child("Polls").child(pollName).child("voters").push().setValue(Voter);
                            myRef.child("Polls").child(pollName).child("votes").child(optionTHREE).push().setValue(1);
                            Toast.makeText(ViewPollActivity.this, "Vote counted", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                break;

            case R.id.option4_vote:
                myRef.child("Polls").child(pollName).child("voters").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(ViewPollActivity.this, "Already voted", Toast.LENGTH_SHORT).show();
                        } else {
                            Voter Voter = new Voter(email);
                            myRef.child("Polls").child(pollName).child("voters").push().setValue(Voter);
                            myRef.child("Polls").child(pollName).child("votes").child(optionFOUR).push().setValue(1);
                            Toast.makeText(ViewPollActivity.this, "Vote counted", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                break;

            case R.id.refresh:
                myRef.child("Polls").child(pollName).child(optionONE).addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            long count = dataSnapshot.getChildrenCount();
                            result1.setText(optionONE+" has "+String.valueOf(count)+" votes");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                myRef.child("Polls").child(pollName).child(optionTWO).addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            long count = dataSnapshot.getChildrenCount();
                            result2.setText(optionTWO+" has "+String.valueOf(count)+" votes");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                myRef.child("Polls").child(pollName).child(optionTHREE).addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            long count = dataSnapshot.getChildrenCount();
                            result3.setText(optionTHREE+" has "+String.valueOf(count)+" votes");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                myRef.child("Polls").child(pollName).child(optionFOUR).addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            long count = dataSnapshot.getChildrenCount();
                            result4.setText(optionFOUR+" has "+String.valueOf(count)+" votes");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
            case R.id.goback:
                onBackPressed();
                break;
        }
*/
    }
}