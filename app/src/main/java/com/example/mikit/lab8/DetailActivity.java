package com.example.mikit.lab8;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        int workoutId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);
//        workoutDetailFragment.setWorkout(workoutId);

        WorkoutDetailFragment details = new WorkoutDetailFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        details.setWorkout(workoutId);
        ft.replace(R.id.fragment_container,details);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
