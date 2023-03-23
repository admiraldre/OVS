package com.example.ovs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ovs.Adapters.PollPostsAdapter;
import com.example.ovs.Models.PollsModel;
import com.example.ovs.R;

public class AdminDashboard extends AppCompatActivity {

    RecyclerView recyclerView;
    PollPostsAdapter pollPostsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        recyclerView = (RecyclerView) findViewById(R.id.pollsRv);

        setRv();

    }

    private void setRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pollPostsAdapter = new PollPostsAdapter(this);
        recyclerView.setAdapter(pollPostsAdapter);
    }
}