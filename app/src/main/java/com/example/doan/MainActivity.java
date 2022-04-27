package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;

import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcvDashboard;
    List<String> titles;
    List<Integer> images;
    MainAdapter mainAdapter;

    Button btnLogout;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvDashboard = findViewById(R.id.rcvDashboard);
        btnLogout = findViewById(R.id.btnLogout);

        auth = FirebaseAuth.getInstance();

        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("Lượng nước");
        titles.add("Bước chân");
        titles.add("Nhịp tim");
        titles.add("Theo dõi BMI");

        images.add(R.drawable.blank);
        images.add(R.drawable.stepcounter);
        images.add(R.drawable.heartrate);
        images.add(R.drawable.bmiicon);

        mainAdapter = new MainAdapter(this, titles, images);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        rcvDashboard.setLayoutManager(gridLayoutManager);
        rcvDashboard.setAdapter(mainAdapter);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));


            }
        });

    }
}

