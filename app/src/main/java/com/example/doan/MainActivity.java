package com.example.doan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import com.example.doan.CovidTracker.CovidTrackerActivity;
import com.example.doan.WeightTracker.CreateWeight;
import com.example.doan.WeightTracker.FirebaseModel;
import com.example.doan.WeightTracker.WeightTrackerActivity;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.scwang.wave.MultiWaveHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import retrofit2.http.Tag;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcvDashboard;
    List<String> titles;
    List<Integer> images;
    MainAdapter mainAdapter;
    MultiWaveHeader waveHeader, waveFooter;

    ImageView covidimg;

    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;

    GraphView graphView;

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waveHeader = findViewById(R.id.wave_header);
        waveFooter = findViewById(R.id.wave_footer);

        waveHeader.setVelocity(5);
        waveHeader.setProgress(1);
        waveHeader.isRunning();
        waveHeader.setGradientAngle(45);
        waveHeader.setWaveHeight(40);
        waveHeader.setStartColor(Color.RED);
        waveHeader.setCloseColor(Color.CYAN);

        waveFooter.setVelocity(5);
        waveFooter.setProgress(1);
        waveFooter.isRunning();
        waveFooter.setGradientAngle(45);
        waveFooter.setWaveHeight(40);
        waveFooter.setStartColor(Color.MAGENTA);
        waveFooter.setCloseColor(Color.YELLOW);

//        mDrawerLayout = findViewById(R.id.drawer_layout);

        rcvDashboard = findViewById(R.id.rcvDashboard);

        auth = FirebaseAuth.getInstance();

//        toolbar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);

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

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        graphView = findViewById(R.id.weight_graph);

        List<DataPoint> datapoints = new ArrayList();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("weights").document(auth.getUid()).collection("myWeights").orderBy("date",Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException error) {
                if(error != null){
                    Log.d("Error", error.getMessage());
                }
                for(DocumentChange doc : documentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){
                        String weights = doc.getDocument().getString("weight");
                        String date = doc.getDocument().getString("date");
                        Log.d("Check", "Weight: " + weights);
                        Log.d("Check", "Date: " + date);
//                        datapoints.add(weights,date);
                    }
                }
            }
        });

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        graphView.addSeries(series);

        covidimg = findViewById(R.id.covidimg);
        covidimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CovidTrackerActivity.class);
                startActivity(intent);
            }
        });












        //Nav Drawer
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
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                }
                else if (id == R.id.nav_hisactive)
                {
                    startActivity(new Intent(MainActivity.this, WeightTrackerActivity.class));

                }
                else if (id == R.id.nav_note)
                {
                    startActivity(new Intent(MainActivity.this, CreateWeight.class));

                }
                else if (id == R.id.nav_covid)
                {
                    startActivity(new Intent(MainActivity.this, CovidTrackerActivity.class));

                }
                else if (id == R.id.nav_hisactive)
                {
                    startActivity(new Intent(MainActivity.this, WeightTrackerActivity.class));

                }
                else if (id == R.id.nav_food)
                {
//                    startActivity(new Intent(MainActivity.this, FoodRecActivity.class));

                }
                else if (id == R.id.nav_logout)
                {
                    auth.signOut();
                    finish();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                return false;
            }
        });


    }

}

