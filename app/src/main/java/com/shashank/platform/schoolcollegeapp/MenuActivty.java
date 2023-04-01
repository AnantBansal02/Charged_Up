package com.shashank.platform.schoolcollegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activty);
        getSupportActionBar().hide();
        ImageButton btn = findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivty.super.onBackPressed();
            }
        });

        TextView txt = findViewById(R.id.txt1);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCardButtonActivity();
            }
        });
TextView txt2 = findViewById(R.id.txt2);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCardButton2Activity();
            }
        });

        TextView txxt3 = findViewById(R.id.txxt3);
        txxt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCardButton3Activity();
            }
        });
        TextView txt4 = findViewById(R.id.txt4);
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCardButton4Activity();
            }
        });
        TextView txt5 = findViewById(R.id.txt5);
        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCardButton5Activity();
            }
        });

    }
    private void openCardButtonActivity(){
        Intent intent = new Intent(this, CardButton1.class);
        startActivity(intent);
    }
    private void openCardButton2Activity(){
        Intent intent = new Intent(this, CardButton2.class);
        startActivity(intent);
    }
    private void openCardButton3Activity(){
        Intent intent = new Intent(this, CardButton3.class);
        startActivity(intent);
    }
    private void openCardButton4Activity(){
        Intent intent = new Intent(this, CardButton4.class);
        startActivity(intent);
    }
    private void openCardButton5Activity(){
        Intent intent = new Intent(this, CardButton5.class);
        startActivity(intent);
    }
}