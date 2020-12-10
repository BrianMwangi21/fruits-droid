package com.kabiru.fruits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    // Store a member variable for the fruits
    private List<Fruits> fruits;

    // Pass in the fruit array into the constructor
    public CartAdapter(List<Fruits> fruits) {
        this.fruits = fruits;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View cartView = inflater.inflate(R.layout.item_carts, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(cartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Fruits fruit = fruits.get(position);

        // Set item views based on your views and data model
        holder.cartQuantityTV.setText(Integer.toString( fruit.getQuantity() ));
        holder.cartNameTV.setText( fruit.getName() );
        holder.cartPriceTV.setText( (fruit.getPrice() * fruit.getQuantity()) + "/=" );
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView cartQuantityTV, cartNameTV, cartPriceTV;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            cartQuantityTV = (TextView) itemView.findViewById(R.id.cartQuantityTV);
            cartNameTV = (TextView) itemView.findViewById(R.id.cartNameTV);
            cartPriceTV = (TextView) itemView.findViewById(R.id.cartPriceTV);
        }
    }
}
