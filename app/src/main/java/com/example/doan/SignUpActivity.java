package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.scwang.wave.MultiWaveHeader;

public class SignUpActivity extends AppCompatActivity {

    EditText edtName, edtAccname, edtEmail, edtPass;
    MultiWaveHeader waveHeader, waveFooter;
    TextView tvLoginnow;
    Button btnSignUp;

    FirebaseAuth auth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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

        edtName = findViewById(R.id.edtname);
        edtAccname = findViewById(R.id.edtusername);
        edtEmail = findViewById(R.id.edtemail);
        edtPass = findViewById(R.id.edtpass);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLoginnow = findViewById(R.id.tvloginnow);

        //take firebase
        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Đang tạo tài khoản");
        dialog.setCancelable(false);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name, accname, email, password;

                name = edtName.getText().toString();
                accname = edtAccname.getText().toString();
                email = edtEmail.getText().toString();
                password = edtPass.getText().toString();

                if(name.isEmpty()){
                    edtName.setError("Hãy nhập tên tài khoản");
                    edtName.requestFocus();
                } else if(accname.isEmpty()){
                    edtAccname.setError("Hãy nhập tên đăng nhập");
                    edtAccname.requestFocus();
                } else if(email.isEmpty()){
                    edtEmail.setError("Hãy nhập Email");
                    edtEmail.requestFocus();
                } else if(password.isEmpty()){
                    edtPass.setError("Hãy nhập mật khẩu");
                    edtPass.requestFocus();
                } else {
                    dialog.show();
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                dialog.dismiss();
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                finish();
                            } else {
                                dialog.dismiss();
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
    
            }
        });

        tvLoginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(auth.getCurrentUser() != null) {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }
    }
}