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
        switch (v.getId()){
            case R.id.createpoll:
                sendPoll();
                break;
        }
    }

    private void sendPoll() {
        String pollName = pollname.getText().toString().trim();
        String optionOne = option1.getText().toString().trim();
        String optionTwo = option2.getText().toString().trim();
        String optionThree = option3.getText().toString().trim();
        String optionFour = option4.getText().toString().trim();

        if(pollName.isEmpty()){
            pollname.setError("Poll Name is required!");
            pollname.requestFocus();
            return;
        }
        if(optionOne.isEmpty()){
            option1.setError("Minimum of two options are required!");
            option1.requestFocus();
            return;
        }
        if(optionTwo.isEmpty()){
            option2.setError("Minimum of two options are required!");
            option2.requestFocus();
            return;
        }
    }
}