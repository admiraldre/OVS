package com.example.votingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class votefile extends AppCompatActivity {

    TextView cokeVote, spriteVote, pepsiVote, MountainDewVote;
    Button cokeButton, spriteButton, pepsiButton, mountainDewButton, button, refreshButton;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.votingscreen);

        auth = FirebaseAuth.getInstance();
        button =  findViewById(R.id.btn_logout);
        textView = findViewById(R.id.userdetails);
        user = auth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), loginfile.class);
            startActivity(intent);
            finish();
        }
        else{
            textView.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), loginfile.class);
                startActivity(intent);
                finish();
            }
        });

        cokeVote = findViewById(R.id.votedA);
        spriteVote = findViewById(R.id.VotedB);
        pepsiVote = findViewById(R.id.VotedC);
        MountainDewVote = findViewById(R.id.VotedD);

        refreshButton = findViewById(R.id.btn_refresh);
        cokeButton = findViewById(R.id.VoteA);
        spriteButton = findViewById(R.id.VoteB);
        pepsiButton = findViewById(R.id.VoteC);
        mountainDewButton = findViewById(R.id.VoteD);
    }

    public void onCokeClicked(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String email = user.getEmail();

        myRef.child("users").child("email").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(votefile.this, "Already voted", Toast.LENGTH_SHORT).show();
                } else {
                    voter Voter = new voter(email);
                    myRef.child("users").child("email").push().setValue(Voter);
                    myRef.child("votes").child("coke").push().setValue(1);
                    Toast.makeText(votefile.this, "Vote counted", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    public void onSpriteClicked(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String email = user.getEmail();

        myRef.child("users").child("email").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(votefile.this, "Already voted", Toast.LENGTH_SHORT).show();
                } else {
                    voter Voter = new voter(email);
                    myRef.child("users").child("email").push().setValue(Voter);
                    myRef.child("votes").child("sprite").push().setValue(1);
                    Toast.makeText(votefile.this, "Vote counted", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    public void onPepsiClicked(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String email = user.getEmail();

        myRef.child("users").child("email").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(votefile.this, "Already voted", Toast.LENGTH_SHORT).show();
                } else {
                    voter Voter = new voter(email);
                    myRef.child("users").child("email").push().setValue(Voter);
                    myRef.child("votes").child("pepsi").push().setValue(1);
                    Toast.makeText(votefile.this, "Vote counted", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    public void onDewClicked(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String email = user.getEmail();

        myRef.child("users").child("email").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(votefile.this, "Already voted", Toast.LENGTH_SHORT).show();
                } else {
                    voter Voter = new voter(email);
                    myRef.child("users").child("email").push().setValue(Voter);
                    myRef.child("votes").child("mDew").push().setValue(1);
                    Toast.makeText(votefile.this, "Vote counted", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    public void onRefreshClicked(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("votes");

        myRef.child("coke").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    long count = dataSnapshot.getChildrenCount();
                    cokeVote.setText(String.valueOf(count));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        myRef.child("sprite").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    long count = dataSnapshot.getChildrenCount();
                    spriteVote.setText(String.valueOf(count));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        myRef.child("pepsi").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    long count = dataSnapshot.getChildrenCount();
                    pepsiVote.setText(String.valueOf(count));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        myRef.child("mDew").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    long count = dataSnapshot.getChildrenCount();
                    MountainDewVote.setText(String.valueOf(count));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        Toast.makeText(votefile.this, "Page Refreshed", Toast.LENGTH_SHORT).show();
    }
}