package com.shashank.platform.schoolcollegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CardButton3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_button3);
        Button bookslot3 = findViewById(R.id.bookslot3);
        bookslot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentActivity();
            }
        });
    }
    private void openPaymentActivity(){
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }
}