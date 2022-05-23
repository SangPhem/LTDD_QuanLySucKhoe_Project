package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import android.icu.util.Calendar;

import java.nio.MappedByteBuffer;

public class CreateWeight extends AppCompatActivity {

    EditText mcreateweight, mcreateweightdate;
    FloatingActionButton msaveweight;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_weight);

        msaveweight = findViewById(R.id.saveweight);
        mcreateweight = findViewById(R.id.createweight);
        mcreateweightdate = findViewById(R.id.createweightdate);

        Toolbar toolbar = findViewById(R.id.toolbarweight);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        msaveweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weight = mcreateweight.getText().toString();
                String date = mcreateweightdate.getText().toString();
                if(weight.isEmpty() || date.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DocumentReference documentReference = firebaseFirestore.collection("weights").document(firebaseUser.getUid()).collection("myWeights").document();
                    Map<String, Object> weights = new HashMap<>();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}