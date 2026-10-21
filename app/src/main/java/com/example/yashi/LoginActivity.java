package com.example.yashi;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String name = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (name.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this,
                        "Please enter your name and password",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(
                    LoginActivity.this, MoodActivity.class);
            intent.putExtra("username", name);
            startActivity(intent);
        });
    }
}
