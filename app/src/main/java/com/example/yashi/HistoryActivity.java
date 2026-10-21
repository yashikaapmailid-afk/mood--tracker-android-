package com.example.yashi;

import android.app.Activity;
import android.os.Bundle;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryActivity extends Activity {

    private MoodDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView historyList = findViewById(R.id.historyList);
        Button backButton = findViewById(R.id.backButton);

        database = new MoodDatabase(this);
        loadHistory(historyList);

        backButton.setOnClickListener(v -> finish());
    }

    private void loadHistory(ListView historyList) {
        ArrayList<String> entries = new ArrayList<>();

        try (Cursor cursor = database.getAllMoods()) {
            while (cursor.moveToNext()) {
                String mood = cursor.getString(
                        cursor.getColumnIndexOrThrow("mood"));
                String reason = cursor.getString(
                        cursor.getColumnIndexOrThrow("reason"));
                String date = cursor.getString(
                        cursor.getColumnIndexOrThrow("entry_date"));

                String emoji = "💜";
                switch (mood) {
                    case "Happy": emoji = "😊"; break;
                    case "Calm": emoji = "😌"; break;
                    case "Loved": emoji = "🥰"; break;
                    case "Sad": emoji = "😢"; break;
                    case "Anxious": emoji = "😰"; break;
                    case "Angry": emoji = "😠"; break;
                }

                entries.add(emoji + "  " + mood
                        + "\n" + date
                        + "\n" + (reason.isEmpty()
                        ? "No reason added." : reason));
            }
        }

        if (entries.isEmpty()) {
            entries.add("🌷 No moods saved yet.\nChoose a mood to begin!");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                entries
        );

        historyList.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        if (database != null) {
            database.close();
        }
        super.onDestroy();
    }
}
