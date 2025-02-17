package com.example.mucommunity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<Item> itemList;
    private Context context;


    public GridAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the current item
        Item item = itemList.get(position);

        // Bind data to views
        holder.itemName.setText(item.getName());
        holder.itemPrice.setText(String.format("Price: $%.2f", item.getPrice()));
        holder.itemDescription.setText(item.getDescription());

        // Handle Buy button click
        holder.buttonBuy.setOnClickListener(v -> {
            Intent intent = new Intent(context, PaymentMethod.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemDescription;
        Button buttonBuy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views
            itemName = itemView.findViewById(R.id.product_name);
            itemPrice = itemView.findViewById(R.id.product_price);
            itemDescription = itemView.findViewById(R.id.product_description);
            buttonBuy = itemView.findViewById(R.id.buttonBuy);
        }
    }

    public void updateItemList(List<Item> newItemList) {
        itemList = newItemList;
        notifyDataSetChanged();
    }
}
