package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

public class WaterTrackerActivity extends AppCompatActivity {
    private int progress = 100, progressreverse = 0;
    Button buttonIncrement;
    Button buttonReset;
    ProgressBar progressBar;
    TextView textView;

    CircularFillableLoaders circularFillableLoaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tracker);

        buttonReset = findViewById(R.id.btnReset);
        buttonIncrement = findViewById(R.id.btnincr);
        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.text_view_progress);
        circularFillableLoaders = findViewById(R.id.watertracker);
        textView.setText(String.valueOf("0 ml"));

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress <= 100) {
                    progress -= 10;
                    progressreverse += 10;
                    updateProgressBar();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress >= -100) {
                    progress = 100;
                    progressreverse = 0;
                    updateProgressBar();
                }
            }
        });
    }

    private void updateProgressBar() {
        progressBar.setProgress(progress);
        circularFillableLoaders.setProgress(progress);
        textView.setText(String.valueOf(progressreverse * 20 + "ml"));
    }
}