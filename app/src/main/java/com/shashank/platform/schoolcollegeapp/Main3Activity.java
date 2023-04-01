package com.shashank.platform.schoolcollegeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main3Activity extends AppCompatActivity {

    TextView back;

    Button Login;

    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);

        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){
            finish();
            return;
        }

        back = findViewById(R.id.back);
        back.setOnClickListener(view -> finish());

        Login = findViewById(R.id.loginAct);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateUser();
            }
        });

    }

    private void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    private void authenticateUser(){
        EditText LoginMail = findViewById(R.id.LoginID);
        EditText LoginPassword = findViewById(R.id.LoginPassword);

        String email = LoginMail.getText().toString();
        String password = LoginPassword.getText().toString();

        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(this,"Please fill all the Entries",Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            openMapsActivity();
                        } else {
                            Toast.makeText(Main3Activity.this,"Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}