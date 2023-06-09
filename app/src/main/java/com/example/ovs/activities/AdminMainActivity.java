package com.example.ovs.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ovs.R;

public class AdminMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button createPoll, viewDash, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        createPoll = (Button) findViewById(R.id.createpoll);
        createPoll.setOnClickListener(this);

        viewDash = (Button) findViewById(R.id.viewdash);
        viewDash.setOnClickListener(this);

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createpoll:
                startActivity(new Intent(AdminMainActivity.this, CreatePollActivity.class));
                break;
            case R.id.viewdash:
                startActivity(new Intent(AdminMainActivity.this, Dashboard.class));
                break;
            case R.id.logout:
                startActivity(new Intent(AdminMainActivity.this,AdminHome.class));
                break;
        }
    }
}