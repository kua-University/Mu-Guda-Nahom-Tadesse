package com.example.mucommunity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class ChappaMethod extends AppCompatActivity {

    private EditText etSenderPhoneNumber, etAmount;
    private Button btnPay;
    private TextView tvTransactionDetails;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chappa_method);

        db = FirebaseFirestore.getInstance();

        etSenderPhoneNumber = findViewById(R.id.etPhoneNumber2);
        etAmount = findViewById(R.id.etAmount);
        btnPay = findViewById(R.id.btnPay);
        tvTransactionDetails = findViewById(R.id.tvTransactionDetails);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPayment();
            }
        });
    }

    private void confirmPayment() {
        String senderPhoneNumber = etSenderPhoneNumber.getText().toString().trim();
        String amountString = etAmount.getText().toString().trim();

        // Validate inputs
        if (senderPhoneNumber.isEmpty() || amountString.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse amount
        double amount;
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show();
            return;
        }

        processPayment(senderPhoneNumber, amount);
    }

    private void processPayment(String senderPhoneNumber, double amount) {

        Map<String, Object> payment = new HashMap<>();
        payment.put("senderPhoneNumber", senderPhoneNumber);
        payment.put("amount", amount);
        payment.put("timestamp", System.currentTimeMillis());

        db.collection("payments")
                .add(payment)
                .addOnSuccessListener(documentReference -> {
                    // Display transaction details on the screen
                    String transactionDetails = "Transaction Details:\n" +
                            "Sender: " + senderPhoneNumber + "\n" +
                            "Amount: " + amount;

                    tvTransactionDetails.setText(transactionDetails);
                    tvTransactionDetails.setVisibility(View.VISIBLE);

                    Toast.makeText(this, "Payment successful! Thank you for using MU GUDA", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to store payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
