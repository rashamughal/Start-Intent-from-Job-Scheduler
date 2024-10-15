package com.example.startintentfromjobscheduler;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TargetActivity extends AppCompatActivity {

    private TextView timerText;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_target2);
        Log.d("TargetActivity", "onCreate: Activity launched");

        timerText = findViewById(R.id.timerText);

        // Create a countdown timer that counts down from 10 seconds //countdown starts from 1...1000...2000... miliseconds
        countDownTimer = new CountDownTimer(10000, 1000) {//1000 miliseconds is the time interval
            //for each tick, after each 1000 mi which is 1 second, the tick method will be called
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the UI each second
                timerText.setText("Countdown: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // Timer is finished, update the text
                timerText.setText("Countdown finished!");
            }
        };

        countDownTimer.start(); // Start the countdown timer
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}