package com.example.mikit.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ReceiveMessageActivity extends AppCompatActivity {

    TextView recieveText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_receive_message);
        recieveText = findViewById(R.id.receiveMessageText);


        Intent intent = getIntent();
        String message = intent.getStringExtra("messageSend");

        recieveText.setText(message);
    }
}
