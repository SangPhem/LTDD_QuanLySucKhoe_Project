package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.AppCompatImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FoodDetails extends AppCompatActivity {

    AppCompatImageView imageView;
    TextView txttitle;
    TextView txtdescrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        txttitle = findViewById(R.id.txttitle);
        txtdescrip = findViewById(R.id.txtdes);
        imageView = findViewById(R.id.image_view);

        Intent data = getIntent();

        Bundle extras = data.getExtras();
        String title = extras.getString("title");
        String des = extras.getString("descript");
        String image= extras.getString("image");
        txttitle.setText(title);


        Picasso.get().load(image).into(imageView);
        txtdescrip.setText(des.replace("_b", "\n"));

    }
}