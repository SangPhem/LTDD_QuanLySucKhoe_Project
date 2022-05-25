package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcvDashboard;
    List<String> titles;
    List<Integer> images;
    MainAdapter mainAdapter;


    FirebaseAuth auth;


    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mDrawerLayout = findViewById(R.id.drawer_layout);

        rcvDashboard = findViewById(R.id.rcvDashboard);

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
        //
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_main);
        findViewById(R.id.image_btn_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                if (id == R.id.nav_profile)
                {
                    startActivity(new Intent(MainActivity.this,ProfileFragment.class));
                }
                else if (id == R.id.nav_hisactive)
                {
                    startActivity(new Intent(MainActivity.this,HisActiveFragment.class));

                }else if (id == R.id.nav_note)
                {
                    startActivity(new Intent(MainActivity.this,NoteFragment.class));

                }else if (id == R.id.nav_logout)
                {
                    auth.signOut();
                    finish();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                return false;
            }
        });


        mainAdapter = new MainAdapter(this, titles, images);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        rcvDashboard.setLayoutManager(gridLayoutManager);
        rcvDashboard.setAdapter(mainAdapter);

    }
}

