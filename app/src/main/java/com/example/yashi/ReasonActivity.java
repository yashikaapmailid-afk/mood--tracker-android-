package com.example.yashi;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReasonActivity extends Activity {

    private String mood;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reason);

        mood = getIntent().getStringExtra("mood");
        username = getIntent().getStringExtra("username");

        TextView selectedMood = findViewById(R.id.selectedMood);
        EditText reason = findViewById(R.id.reason);
        Button saveButton = findViewById(R.id.saveButton);
        Button backButton = findViewById(R.id.backButton);

        if (mood == null) mood = "Happy";
        selectedMood.setText(mood);

        saveButton.setOnClickListener(v -> {
            String note = reason.getText().toString().trim();

            String date = new SimpleDateFormat(
                    "dd MMM yyyy, hh:mm a", Locale.getDefault()
            ).format(new Date());

            MoodDatabase database = new MoodDatabase(this);
            long result = database.saveMood(mood, note, date);
            database.close();

            if (result != -1) {
                Intent intent = new Intent(
                        ReasonActivity.this, SuccessActivity.class);
                intent.putExtra("mood", mood);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this,
                        "Could not save. Please try again.",
                        Toast.LENGTH_LONG).show();
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}
