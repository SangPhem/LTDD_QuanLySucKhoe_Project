package com.example.doan.WaterTracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.doan.MainActivity;
import com.example.doan.R;
import com.example.doan.WeightTracker.EditWeightActivity;
import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

public class WaterTrackerActivity extends AppCompatActivity {
    private int progress = 100, progressreverse = 0, target = 2000;
    Button button50,button100,button150,button200,button250;
    Button buttonReset;
    ProgressBar progressBar;
    TextView textView, txtremaining;

    WaterGS waterGS;

    Toolbar toolbar;

    CircularFillableLoaders circularFillableLoaders;

    public static final String SHARED_PREFERENCES = "sharedprefs";
    public static final String TEXT = "text";
    public static final String REMAINING = "remaining";
    public static final String PROGRESS = "progress";
    public static final String PROGRESSREV = "progressrev";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tracker);

        buttonReset = findViewById(R.id.btnReset);
        button50 = findViewById(R.id.btn50);
        button100 = findViewById(R.id.btn100);
        button150 = findViewById(R.id.btn150);
        button200 = findViewById(R.id.btn200);
        button250 = findViewById(R.id.btn250);
        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.text_view_progress);
        txtremaining = findViewById(R.id.txtRemaining);
        circularFillableLoaders = findViewById(R.id.watertracker);

//        textView.setText(String.valueOf("0 ml"));
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);

        progressBar.setProgress(sharedPreferences.getInt(PROGRESS, 1));
        circularFillableLoaders.setProgress(sharedPreferences.getInt(PROGRESS, 1));

        txtremaining.setText(target - (sharedPreferences.getInt(PROGRESSREV, 0) * 25) + "ml");
        textView.setText(sharedPreferences.getInt(PROGRESSREV, 0) * 25 + "ml");


        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bảng lượng nước tiêu thụ");


        button50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getInt(PROGRESS, 1) <= 100) {
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                    int prog = sharedPreferences.getInt(PROGRESS, 1);
                    int progrv = sharedPreferences.getInt(PROGRESSREV, 1);
                    progress = prog;
                    progressreverse = progrv;
                    progress -= 2;
                    progressreverse += 2;
                    updateProgressBar();
                }
            }
        });

        button100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getInt(PROGRESS, 1) <= 100) {
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                    int prog = sharedPreferences.getInt(PROGRESS, 1);
                    int progrv = sharedPreferences.getInt(PROGRESSREV, 1);
                    progress = prog;
                    progressreverse = progrv;
                    progress -= 4;
                    progressreverse += 4;
                    updateProgressBar();
                }
            }
        });

        button150.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getInt(PROGRESS, 1) <= 100) {
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                    int prog = sharedPreferences.getInt(PROGRESS, 1);
                    int progrv = sharedPreferences.getInt(PROGRESSREV, 1);
                    progress = prog;
                    progressreverse = progrv;
                    progress -= 6;
                    progressreverse += 6;
                    updateProgressBar();
                }
            }
        });

        button200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getInt(PROGRESS, 1) <= 100) {
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                    int prog = sharedPreferences.getInt(PROGRESS, 1);
                    int progrv = sharedPreferences.getInt(PROGRESSREV, 1);
                    progress = prog;
                    progressreverse = progrv;
                    progress -= 8;
                    progressreverse += 8;
                    updateProgressBar();
                }
            }
        });

        button250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getInt(PROGRESS, 1) <= 100) {
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                    int prog = sharedPreferences.getInt(PROGRESS, 1);
                    int progrv = sharedPreferences.getInt(PROGRESSREV, 1);
                    progress = prog;
                    progressreverse = progrv;
                    progress -= 10;
                    progressreverse += 10;
                    updateProgressBar();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getInt(PROGRESS, 1) >= -100) {
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                    int prog = sharedPreferences.getInt(PROGRESS, 1);
                    int progrv = sharedPreferences.getInt(PROGRESSREV, 1);
                    progress = prog;
                    progressreverse = progrv;
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
        textView.setText(String.valueOf(progressreverse * 25 + "ml"));
        txtremaining.setText(String.valueOf(target - (progressreverse * 25) + "ml"));

//        waterGS = new WaterGS(progress, progressreverse);
//        waterGS.setProgress(progress);
//        waterGS.setProgressrev(progressreverse);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(PROGRESSREV, progressreverse);
        editor.putInt(PROGRESS, progress);
        editor.putString(TEXT, textView.getText().toString());
        editor.putString(REMAINING, txtremaining.getText().toString());
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}