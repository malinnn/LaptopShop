package com.example.laptopshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView usernameInput = findViewById(R.id.username);
        TextView passwordInput = findViewById(R.id.password);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameInput.getText().toString().equals("user") && passwordInput.getText().toString().equals("admin"))
                {
                    Toast.makeText(MainActivity.this, "Login successful !", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, MenuActivity.class);

                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Login failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}