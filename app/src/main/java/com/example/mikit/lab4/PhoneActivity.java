package com.example.mikit.lab4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PhoneActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telephone);

        btn = findViewById(R.id.btnCall);
        textView = findViewById(R.id.editCall);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String number = textView.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number,null));
        startActivity(intent);
    }
}
