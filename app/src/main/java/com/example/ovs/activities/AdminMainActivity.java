package com.example.ovs.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ovs.R;

public class AdminMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button createPoll, viewDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        createPoll = (Button) findViewById(R.id.createpoll);
        createPoll.setOnClickListener(this);

        viewDash = (Button) findViewById(R.id.viewdash);
        viewDash.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createpoll:
                startActivity(new Intent(AdminMainActivity.this, CreatePollActivity.class));

            case R.id.viewdash:
                startActivity(new Intent(AdminMainActivity.this,AdminDashboard.class));
        }
    }
}