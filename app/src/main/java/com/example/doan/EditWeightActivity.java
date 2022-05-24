package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

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

public class EditWeightActivity extends AppCompatActivity {

    Intent data;
    EditText meditweight, meditdate;
    FloatingActionButton msaveeditweight;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_weight);

        meditweight = findViewById(R.id.editweight);
        meditdate = findViewById(R.id.editweightdate);
        msaveeditweight = findViewById(R.id.saveeditweight);

        data = getIntent();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Toolbar toolbar = findViewById(R.id.toolbarweightedit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        msaveeditweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newweight = meditweight.getText().toString();
                String newdate = meditdate.getText().toString();
                if(newweight.isEmpty() || newdate.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    DocumentReference documentReference = firebaseFirestore.collection("weights").document(firebaseUser.getUid()).collection("myWeights").document(data.getStringExtra("weightId"));
                    Map <String,Object> cannang = new HashMap<>();
                    cannang.put("weight", newweight);
                    cannang.put("date", newdate);
                    documentReference.set(cannang).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(),"Lưu chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditWeightActivity.this, WeightTrackerActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Lưu chỉnh sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        String weighttitle = data.getStringExtra("weights");
        String weightdate = data.getStringExtra("date");
        meditweight.setText(weighttitle);
        meditdate.setText(weightdate);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}