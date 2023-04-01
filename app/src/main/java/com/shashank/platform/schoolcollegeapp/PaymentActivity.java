package com.shashank.platform.schoolcollegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.CheckedOutputStream;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
TextView pstatus;
EditText fprice;
int famount= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Button paybtn = findViewById(R.id.idBtnPay);
        fprice=findViewById(R.id.totaltext);
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price=Integer.parseInt(fprice.getText().toString());
                famount=price;
                fprice.setText(""+famount);
                String samount = String.valueOf(famount);
                int amount = Math.round(Float.parseFloat(samount) * 100);

                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_LhDS4171FNfbfP");
                checkout.setImage(R.drawable.ic_baseline_electric_car_24);

                JSONObject object = new JSONObject();
                try {
                    object.put("name", "Charge and Drive Charging Station");
                    object.put("description", "EV CHARGING Payment");
                    object.put("theme.color", "");
                    object.put("amount", amount);
                    object.put("prefill.contact", "1234567890");
                    object.put("prefill.email", "demo@gmail.com");
                    checkout.open(PaymentActivity.this, object);
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onPaymentSuccess(String s) {
        pstatus.setText("Order Successfully. Transaction No :"+s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        pstatus.setText("Something went wrong"+s);
    }
}