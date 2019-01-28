package com.example.mikit.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateMessageActivity extends AppCompatActivity {


    private Button btnCend;
    private EditText editText;
    private String message;
    final String TAG = "States";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        btnCend = findViewById(R.id.sendBtn);
        editText = findViewById(R.id.createText);

        btnCend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (savedInstanceState != null) {
                    message = savedInstanceState.getString("message");
                } else {
                    message = editText.getText().toString();
                }
                Intent intent = new Intent(CreateMessageActivity.this, ReceiveMessageActivity.class);
                intent.setType("text/plain");
                intent.putExtra("messageSend",message);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message",message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"My activity: onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"My activity: onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"My activity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"My activity: onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"My activity: onRestart");
    }
}
