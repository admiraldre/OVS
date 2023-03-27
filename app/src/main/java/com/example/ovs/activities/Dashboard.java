package com.example.ovs.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ovs.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ListView listView;
    private Button goHome;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    Polls polls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        polls = new Polls();
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.user_info,R.id.userInfo,arrayList);

        goHome = (Button) findViewById(R.id.goHome);
        goHome.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Polls");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    polls = ds.getValue(Polls.class);
                    arrayList.add(polls.getPollName().toString());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goHome:
                startActivity(new Intent(Dashboard.this,LogInActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
}