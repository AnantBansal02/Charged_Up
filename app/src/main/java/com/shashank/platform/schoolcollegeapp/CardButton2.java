package com.shashank.platform.schoolcollegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CardButton2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_button2);
        Button bookslot2 = findViewById(R.id.bookslot2);
        bookslot2.setOnClickListener(new View.OnClickListener() {
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
