package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WeightDetails extends AppCompatActivity {

    private TextView mweightdetail, mdatedetail;
    FloatingActionButton mgotoeditweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_details);

        mweightdetail = findViewById(R.id.weightdetails);
        mdatedetail = findViewById(R.id.weightdatedetails);
        mgotoeditweight = findViewById(R.id.gotoeditweight);

        Toolbar toolbar = findViewById(R.id.toolbarweightdetails);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent data = getIntent();

        mgotoeditweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditWeightActivity.class);
                intent.putExtra("weights", data.getStringExtra("weights"));
                intent.putExtra("date", data.getStringExtra("date"));
                intent.putExtra("weightId", data.getStringExtra("weightId"));
                v.getContext().startActivity(intent);
                Log.d("test", "Check " + intent);
            }
        });

        mdatedetail.setText(data.getStringExtra("date"));
        mweightdetail.setText(data.getStringExtra("weights"));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}