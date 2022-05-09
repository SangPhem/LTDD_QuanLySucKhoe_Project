package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WeightTrackerActivity extends AppCompatActivity {

    FloatingActionButton mcreateweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_tracker);

        mcreateweight = findViewById(R.id.createweight);

        getSupportActionBar().setTitle("Bảng lưu cân nặng");

        mcreateweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeightTrackerActivity.this, CreateWeight.class));
            }
        });
    }
}