package com.example.yashi;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class MoodActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        String username = getIntent().getStringExtra("username");
        TextView greeting = findViewById(R.id.greeting);

        if (username != null && !username.trim().isEmpty()) {
            greeting.setText("Hi, " + username + "! 🌼");
        }

        int[] ids = {
                R.id.happy, R.id.calm, R.id.loved,
                R.id.sad, R.id.anxious, R.id.angry
        };

        String[] moods = {
                "Happy", "Calm", "Loved",
                "Sad", "Anxious", "Angry"
        };

        for (int i = 0; i < ids.length; i++) {
            final String selectedMood = moods[i];
            Button button = findViewById(ids[i]);

            button.setOnClickListener(v -> {
                Intent intent = new Intent(
                        MoodActivity.this, ReasonActivity.class);
                intent.putExtra("mood", selectedMood);
                intent.putExtra("username", username);
                startActivity(intent);
            });
        }

        findViewById(R.id.historyButton).setOnClickListener(v -> {
            startActivity(new Intent(
                    MoodActivity.this, HistoryActivity.class));
        });
    }
}
