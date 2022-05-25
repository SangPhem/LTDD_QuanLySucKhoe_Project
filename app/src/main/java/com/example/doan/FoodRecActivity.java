package com.example.doan;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class FoodRecActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mfirestoreList;
    private FirestoreRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_food_rec);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mfirestoreList = findViewById(R.id.firestore_list);

        //Query
        Query query = firebaseFirestore.collection("foodrec");
        //RecyclerOptions
        FirestoreRecyclerOptions<FoodsModel> options = new FirestoreRecyclerOptions.Builder<FoodsModel>().setQuery(query, FoodsModel.class).build();
         adapter = new FirestoreRecyclerAdapter<FoodsModel, FoodsViewHolder>(options) {
            @NonNull
            @Override
            public FoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new FoodsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FoodsViewHolder holder, int position, @NonNull FoodsModel model) {

                holder.list_title.setText(model.getTitle());
                holder.list_descrip.setText(model.getDescrip() + "");
                holder.list_image.setText(model.getImage());
//                Picasso.with(FoodRecActivity.this).load("https://pastaxi-manager.onepas.vn/content/uploads/articles/2amthuc/amthuccuocsong/thucdonhangngay/thuc-don-hang-ngay-anh1.jpg")
//                        .into(holder.list_image);
                String test = model.getTitle();
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), FoodDetails.class);
                        intent.putExtra("title", model.getTitle());
                        intent.putExtra("descript", model.getDescrip());
                        intent.putExtra("image", model.getImage());

                        view.getContext().startActivities(new Intent[]{intent});
                        //Toast.makeText(view.getContext(), test,Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };

         mfirestoreList.setHasFixedSize(true);
         mfirestoreList.setLayoutManager(new LinearLayoutManager(this));
         mfirestoreList.setAdapter(adapter);



    }

    private class FoodsViewHolder extends RecyclerView.ViewHolder{

        private TextView list_title;
        private TextView list_descrip;
        private TextView list_image;

        public FoodsViewHolder(@NonNull View itemView) {
            super(itemView);

            list_title = itemView.findViewById(R.id.list_title);
            list_descrip = itemView.findViewById(R.id.list_descrip);
            list_image = itemView.findViewById(R.id.list_image);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
