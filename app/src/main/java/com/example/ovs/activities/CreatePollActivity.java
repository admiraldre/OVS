package com.example.ovs.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ovs.R;

public class CreatePollActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText pollname,option1,option2,option3,option4;
    private Button createpoll;
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
    }

    @Override
    public void onClick(View v) {

    }
}