package com.example.doan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import 	android.icu.util.Calendar;

import java.nio.MappedByteBuffer;

public class CreateWeight extends AppCompatActivity {

    EditText mcreateweight, mcreateweightdate;
    FloatingActionButton msaveweight;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    DatePickerDialog.OnDateSetListener setListener;

    ProgressBar mcreateprogressbar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_weight);

        msaveweight = findViewById(R.id.saveweight);
        mcreateweight = findViewById(R.id.createweight);
        mcreateweightdate = findViewById(R.id.createweightdate);

        mcreateprogressbar = findViewById(R.id.createweight_progressbar);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        mcreateweightdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateWeight.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        mcreateweightdate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

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
                if(date.isEmpty() || weight.isEmpty())
                {
                    Toast.makeText(CreateWeight.this,"Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mcreateprogressbar.setVisibility(View.VISIBLE);

                    DocumentReference documentReference = firebaseFirestore.collection("weights").document(firebaseUser.getUid()).collection("myWeights").document();
                    Map<String, Object> cannang = new HashMap<>();
                    cannang.put("weight", weight + " kg");
                    cannang.put("date",date);

                    documentReference.set(cannang).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                            mcreateprogressbar.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(CreateWeight.this, WeightTrackerActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                            mcreateprogressbar.setVisibility(View.INVISIBLE);
                        }
                    });


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