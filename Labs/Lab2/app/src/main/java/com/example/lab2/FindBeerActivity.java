package com.example.lab2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FindBeerActivity extends AppCompatActivity {

    Spinner spinner;
    TextView text;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        text = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.beer);

    }

    public void findBeer(View v) {
        String selected = spinner.getSelectedItem().toString();
        text.setText(selected);
    }

}
