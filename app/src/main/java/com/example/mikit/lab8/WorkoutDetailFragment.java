package com.example.mikit.lab8;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {

    private long workoutId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.workout_detail_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //получение корневого обьекта View фрагмента
        View view = getView();
        if (view != null) {
            TextView title = view.findViewById(R.id.textTitle);
            TextView description = view.findViewById(R.id.textDescription);

            Workout.Work workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            description.setText(workout.getDescription());
        }
    }

    public void setWorkout(long id) {
        this.workoutId = id;
    }
}
