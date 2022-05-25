package com.example.doan.FoodRecommend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.widget.TextView;

import com.example.doan.R;
import com.example.doan.WeightTracker.EditWeightActivity;
import com.google.firestore.v1.TargetOrBuilder;
import com.squareup.picasso.Picasso;

public class FoodDetails extends AppCompatActivity {

    AppCompatImageView imageView;
    TextView txttitle;
    TextView txtdescrip;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);


        toolbar = findViewById(R.id.toolbar_fooddetail);
        txttitle = findViewById(R.id.txttitle);
        txtdescrip = findViewById(R.id.txtdes);
        imageView = findViewById(R.id.image_view);

        Intent data = getIntent();

        Bundle extras = data.getExtras();
        String title = extras.getString("title");
        String des = extras.getString("descrip");
        String image= extras.getString("image");


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

        txttitle.setText(title);


        Picasso.get().load(image).into(imageView);
        txtdescrip.setText(des.replace("_b", "\n"));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, FoodRecActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}