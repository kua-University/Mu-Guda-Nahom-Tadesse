package com.example.mucommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentMethod extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        findViewById(R.id.telebirrButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentMethod.this, TelebirrMethod.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.chappaButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentMethod.this, ChappaMethod .class);
                startActivity(intent);
            }
        });

        findViewById(R.id.santimPayButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentMethod.this, SantimpayMethod .class);
                startActivity(intent);
            }
        });
    }
}
