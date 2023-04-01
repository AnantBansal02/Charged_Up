package com.shashank.platform.schoolcollegeapp;

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
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            finish();
            return;
        }


        back = findViewById(R.id.back);
        back.setOnClickListener(view -> finish());

        Button signup = findViewById(R.id.Sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void openMapsactivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    private void registerUser(){
        EditText FullName = findViewById(R.id.name);
        EditText Mail= findViewById(R.id.email);
        EditText Passcode = findViewById(R.id.password);

        String name = FullName.getText().toString();
        String email = Mail.getText().toString();
        String password = Passcode.getText().toString();

        if(name.isEmpty()||email.isEmpty()||password.isEmpty()){
            Toast.makeText(this,"Please fill all the details",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(name,email);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            openMapsactivity();
                                        }
                                    });
                        } else {
                            Toast.makeText(Main2Activity.this,"Authentication failed",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}