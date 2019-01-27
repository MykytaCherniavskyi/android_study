package com.example.myfirstapp;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btnStart;
    Button btnStop;
    Timer mTimer;
    MyTimerTask mMyTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);
        btnStart = (Button) findViewById(R.id.buttonStart);
        btnStop = (Button) findViewById(R.id.buttonStop);


        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mTimer != null) {
                    mTimer.cancel();
                }

                mTimer = new Timer();
                mMyTimerTask = new MyTimerTask();

                mTimer.schedule(mMyTimerTask, 1000, 1000);

//                CountDownTimer cDT = new CountDownTimer(30000, 1000) {
//
//                    public void onTick(long millisUntilFinished) {
//                        textView.setText("seconds remaining: " + millisUntilFinished / 1000);
//                    }
//
//                    public void onFinish() {
//                        textView.setText("done!");
//                    }
//                }.start();
            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                }
            }
        });

    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());
            final String strDate = simpleDateFormat.format(calendar.getTime());

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    textView.setText(strDate);
                }
            });
        }
    }

}
