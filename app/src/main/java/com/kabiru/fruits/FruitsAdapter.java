package com.kabiru.fruits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitsAdapter extends RecyclerView.Adapter<FruitsAdapter.ViewHolder> {

    // Store a member variable for the fruits
    private List<Fruits> fruits;
    private Context context;
    final private FruitListener fruitListener;

    // Pass in the fruit array into the constructor
    public FruitsAdapter(List<Fruits> fruits, Context context, FruitListener fruitListener) {
        this.fruits = fruits;
        this.context = context;
        this.fruitListener = fruitListener;
    }

    @NonNull
    @Override
    public FruitsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View fruitView = inflater.inflate(R.layout.item_fruits, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(fruitView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FruitsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Fruits fruit = fruits.get(position);

        // Set item views based on your views and data model
        holder.nameTV.setText( fruit.getName() );
        holder.priceTV.setText( "Ksh. " + fruit.getPrice() + "/=" );


        // Check if quantity is > 1 and display price
        if( fruit.getQuantity() > 0 ) {
            holder.totalTV.setText( ( fruit.getPrice() * fruit.getQuantity() ) + "/=" );
        }
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTV, priceTV, totalTV;
        public Button addToCart, minusFromCart;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTV = (TextView) itemView.findViewById(R.id.nameTV);
            priceTV = (TextView) itemView.findViewById(R.id.priceTV);
            totalTV = (TextView) itemView.findViewById(R.id.totalTV);
            addToCart = (Button) itemView.findViewById(R.id.addToCart);
            minusFromCart = (Button) itemView.findViewById(R.id.minusFromCart);

            // Set onClick actions
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    fruitListener.addToCartClick(position);
                }
            });
            minusFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    fruitListener.minusFromCartClick(position);
                }
            });
        }
    }
}
