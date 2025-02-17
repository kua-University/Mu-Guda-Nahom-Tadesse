package com.example.mucommunity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridAdapter adapter;
    private List<Item> itemList;
    private List<Item> filteredList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        } else {
            Log.d("AuthState", "User is logged in: " + user.getEmail());
        }

        db = FirebaseFirestore.getInstance();

        EditText searchField = findViewById(R.id.searchField);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns
        itemList = new ArrayList<>();
        filteredList = new ArrayList<>();
        adapter = new GridAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

        fetchDataFromFirestore();

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // No action required here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String query = charSequence.toString().trim().toLowerCase();
                filterItems(query);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchDataFromFirestore() {
        db.collection("items")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        itemList.clear(); // Clear existing data
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String name = document.getString("name");
                            if (name == null) {
                                name = "Unnamed Item";
                            }

                            Double price = document.getDouble("price");
                            if (price == null) {
                                price = 0.0;
                            }

                            String description = document.getString("description");
                            if (description == null) {
                                description = "No description available";
                            }

                            // Log the fetched data
                            Log.d("FirestoreData", "Name: " + name + ", Price: " + price + ", Description: " + description);

                            Item item = new Item(name, price, description);
                            itemList.add(item);
                        }
                        filteredList.clear();
                        filteredList.addAll(itemList);
                        adapter.updateItemList(filteredList);
                    } else {
                        Toast.makeText(this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                        Log.e("FirestoreError", "Error fetching data", task.getException());
                    }
                });
    }

    private void filterItems(String query) {
        filteredList.clear();
        for (Item item : itemList) {
            if (item.getName().toLowerCase().contains(query)) {
                filteredList.add(item);
            }
        }
        adapter.updateItemList(filteredList);
    }
}