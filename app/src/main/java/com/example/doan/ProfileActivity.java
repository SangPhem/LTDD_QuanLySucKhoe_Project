package com.example.doan;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    ImageView imageView;
    TextView username,useruid;

    private FirebaseUser user;
    String userID, userEmail;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        imageView = findViewById(R.id.avatar);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        userEmail = user.getEmail();

        username = findViewById(R.id.username);
        useruid = findViewById(R.id.useruid);

        username.setText(userEmail);
        useruid.setText(userID);
    }
}
