package com.shashank.platform.schoolcollegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CardButton5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_button5);
        Button bookslot5 = findViewById(R.id.bookslot5);
        bookslot5.setOnClickListener(new View.OnClickListener() {
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