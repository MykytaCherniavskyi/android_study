package com.example.mikit.lab8;

public class Workout {

    public static Work[] workouts = new Work[] {
            new Work("Fitness", "Some cool training"),
            new Work("Run", "Cool running"),
            new Work("Hard", "hard musculs fichers"),
            new Work("Musculinum", "other cool hard training")
    };

    public static class Work {

        String work;
        String description;

        Work(String name ,String desc) {
            this.work = name;
            this.description = desc;
        }

        public String getName() {
            return this.work;
        }

        public String getDescription() {
            return this.description;
        }

    }
}
