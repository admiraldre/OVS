package com.example.ovs.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ovs.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    private Button goHome;
    FirebaseDatabase database;
    DatabaseReference ref;
    private List<Polls> pollsList = new ArrayList<Polls>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ArrayList<String> arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(Dashboard.this, android.R.layout.simple_list_item_1, arrayList);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        ref = FirebaseDatabase.getInstance().getReference().child("Polls");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String key = dataSnapshot1.getKey();
                    //Polls polls = dataSnapshot1.getValue(Polls.class);
                    arrayList.add(key);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //startActivity(new Intent(Dashboard.this, ViewPollActivity.class));
                String selectedKey = (String) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(Dashboard.this, ViewPollActivity.class);
                intent.putExtra("key", selectedKey);
                startActivity(intent);


            }
        });

        goHome = (Button) findViewById(R.id.goHome);
        goHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goHome:
                startActivity(new Intent(Dashboard.this,LogInActivity.class));
                break;
        }
    }
}