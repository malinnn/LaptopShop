package com.example.laptopshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.net.NetworkInterface;

public class MainActivity extends AppCompatActivity implements ConnectionReceiver.ReceiverListener {
    TextView textTimer;
    Button connButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTimer = findViewById(R.id.textTimer);
        startService(new Intent(MainActivity.this, MyTimerService.class));

        TextView usernameInput = findViewById(R.id.username);
        TextView passwordInput = findViewById(R.id.password);

        Button loginButton = findViewById(R.id.loginButton);

        connButton = findViewById(R.id.connectionButton);
        connButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkConnection();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameInput.getText().toString().equals("user") && passwordInput.getText().toString().equals("admin"))
                {
                    Toast.makeText(MainActivity.this, "Login successful !", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(i);

                    ServerClient.sendToServer("\"" + "user" + "\"" + " Logged in successfully !");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Login failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateText(Intent intent)
    {
        if (intent.getExtras() != null)
        {
            String hours = intent.getStringExtra("hours");
            String minutes = intent.getStringExtra("minutes");
            String seconds = intent.getStringExtra("seconds");
            textTimer.setText(hours + ":" + minutes + ":" + seconds);
        }
    }

    private BroadcastReceiver broadcast = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateText(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcast, new IntentFilter(MyTimerService.STOPWATCH));
        checkConnection();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(broadcast);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, MyTimerService.class));
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkConnection();
    }

    private void checkConnection()
    {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(new ConnectionReceiver(), intentFilter);

        ConnectionReceiver.listener = this;

        ConnectivityManager manager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        showSnackBar(isConnected);
    }

    private void showSnackBar(boolean isConnected)
    {
        String message;
        int color;

        if(isConnected)
        {
            message = "Connected to internet.";
            color = Color.WHITE;
        }
        else
        {
            message = "Not connected to the internet !";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar.make(findViewById(R.id.connectionButton),message,Snackbar.LENGTH_LONG);
        View view = snackbar.getView();

        TextView textView = findViewById(R.id.snackbarText);
        textView.setTextColor(color);

        snackbar.show();
    }

    @Override
    public void onNetworkChange(boolean isConnected)
    {
        showSnackBar(isConnected);
    }
}