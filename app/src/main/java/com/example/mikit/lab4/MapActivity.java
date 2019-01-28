package com.example.mikit.lab4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMap;
    EditText editCoord;
    private final static String TAG = "MAP";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        btnMap = findViewById(R.id.btnMap);
        editCoord = findViewById(R.id.editCoord);
        btnMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:" + editCoord.getText().toString()));
        startActivity(intent);
    }
}
