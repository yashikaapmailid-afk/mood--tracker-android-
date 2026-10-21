package com.example.yashi;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class SuccessActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        String mood = getIntent().getStringExtra("mood");
        String username = getIntent().getStringExtra("username");

        TextView message = findViewById(R.id.successMessage);
        Button homeButton = findViewById(R.id.homeButton);
        Button historyButton = findViewById(R.id.historyButton);

        String greeting = (username == null || username.isEmpty())
                ? "Your feelings have been saved."
                : "Wonderful, " + username + "!";

        message.setText(greeting + "\nToday's mood: " + mood
                + "\nKeep taking care of yourself. ♡");

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    SuccessActivity.this, MoodActivity.class);
            intent.putExtra("username", username);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        historyButton.setOnClickListener(v -> {
            startActivity(new Intent(
                    SuccessActivity.this, HistoryActivity.class));
        });
    }
}
