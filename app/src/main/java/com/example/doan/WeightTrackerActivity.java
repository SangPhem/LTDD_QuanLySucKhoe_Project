package com.example.doan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class WeightTrackerActivity extends AppCompatActivity {

    FloatingActionButton mcreateweight;
    private FirebaseAuth firebaseAuth;
    ImageView popupbtn;

    RecyclerView mrecyclerView;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    FirestoreRecyclerAdapter<FirebaseModel,WeightViewHolder> weightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_tracker);

        mcreateweight = findViewById(R.id.createweight);
        firebaseAuth = FirebaseAuth.getInstance();

//        getSupportActionBar().setTitle("Bảng lưu cân nặng");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        mcreateweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeightTrackerActivity.this, CreateWeight.class));
            }
        });

        //truy van toi du lieu tuong ung khi khai bao ben CreateWeight "weights" vs "myWeights"
        Query query = firebaseFirestore.collection("weights").document(firebaseUser.getUid()).collection("myWeights").orderBy("weight",Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<FirebaseModel> alluserweights = new FirestoreRecyclerOptions.Builder<FirebaseModel>().setQuery(query, FirebaseModel.class).build();

        weightAdapter = new FirestoreRecyclerAdapter<FirebaseModel, WeightViewHolder>(alluserweights) {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onBindViewHolder(@NonNull WeightViewHolder weightViewHolder, int i, @NonNull FirebaseModel firebaseModel) {

                popupbtn = weightViewHolder.itemView.findViewById(R.id.menupopbtn);

                weightViewHolder.weight_title.setText(firebaseModel.getWeight());
                weightViewHolder.weight_date.setText(firebaseModel.getDate());

                String docId = weightAdapter.getSnapshots().getSnapshot(i).getId();

                weightViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), WeightDetails.class);
                        intent.putExtra("weights", firebaseModel.getWeight());
                        intent.putExtra("date", firebaseModel.getDate());
                        intent.putExtra("weightId", docId);
                        v.getContext().startActivity(intent);
                    }
                });

                popupbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                        popupMenu.setGravity(Gravity.END);
                        popupMenu.getMenu().add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                Intent intent = new Intent(v.getContext(), EditWeightActivity.class);
                                intent.putExtra("weights", firebaseModel.getWeight());
                                intent.putExtra("date", firebaseModel.getDate());
                                intent.putExtra("weightId", docId);

                                v.getContext().startActivity(intent);
                                return false;
                            }
                        });

                        popupMenu.getMenu().add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                //Toast.makeText(v.getContext(),"Xóa thành công", Toast.LENGTH_SHORT).show();
                                DocumentReference documentReference = firebaseFirestore.collection("weights").document(firebaseUser.getUid()).collection("myWeights").document(docId);
                                documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(v.getContext(),"Xóa thành công", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(v.getContext(),"Xóa không thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                return false;
                            }
                        });

                        popupMenu.show();
                    }
                });

            }

            @NonNull
            @Override
            public WeightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_layout, parent, false);
                return new WeightViewHolder(view);
            }
        };

        mrecyclerView = findViewById(R.id.rcvweight);
        mrecyclerView.setHasFixedSize(true);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mrecyclerView.setAdapter(weightAdapter);

    }

    public class WeightViewHolder extends RecyclerView.ViewHolder {
        private TextView weight_title, weight_date;
        LinearLayout mweight;

        public WeightViewHolder(@NonNull View itemView) {
            super(itemView);
            weight_title = itemView.findViewById(R.id.weight_title);
            weight_date = itemView.findViewById(R.id.weight_date);
            mweight = itemView.findViewById(R.id.weightlayout);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        weightAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(weightAdapter != null)
        {
            weightAdapter.stopListening();
        }
    }
}