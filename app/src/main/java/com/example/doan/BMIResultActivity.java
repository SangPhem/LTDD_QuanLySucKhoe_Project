package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BMIResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);

        Intent intent = getIntent();

        float BMI  = intent.getFloatExtra("BMI", 0);
        String age_value = intent.getStringExtra("age");

        TextView your_bmi = findViewById(R.id.your_bmi);
        your_bmi.setText(String.valueOf(BMI));

        TextView age = findViewById(R.id.age);
        age.setText(age_value);
        //Test
//        TextView category = findViewById(R.id.category);
//        Category category1 = new Category();
//        category.setText(category1.getCategory(BMI));

        TextView condition = findViewById(R.id.condition);
//        Condition condition1 = new Condition();
//        condition.setText(condition1.getCategory(BMI));

        if (BMI < 18.5) {
            condition.setText("Thiếu cân");
        } else if (BMI >= 18.5 && BMI < 25) {
            condition.setText("Khỏe mạnh");
        } else if (BMI >= 25 && BMI < 30){
            condition.setText("Thừa cân");
        } else {
            condition.setText("Béo phì");
        }

        Button recalculate = findViewById(R.id.recalculate);
        recalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
            }
        });

    }

    private void updateUI() {
        Intent intent1 = new Intent(BMIResultActivity.this,BMITrackerActivity.class);
        finish();
        startActivity(intent1);
        fileList();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateUI();
    }
}