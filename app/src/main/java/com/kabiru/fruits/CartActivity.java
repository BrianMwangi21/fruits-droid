package com.kabiru.fruits;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ArrayList<Fruits> fruits = new ArrayList<>();
    ArrayList<Fruits> filteredFruits = new ArrayList<>();
    RecyclerView rvFruits;
    TextView cartTotalTV;
    CartAdapter cartAdapter;
    private int cartTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get fruits from bundle from Intent
        Bundle bundle = getIntent().getExtras();
        fruits = (ArrayList<Fruits>) bundle.getSerializable("fruits");

        // Initialize views
        rvFruits = (RecyclerView) findViewById(R.id.cartRecycleView);
        cartTotalTV = (TextView) findViewById(R.id.cartTotalTV);

        // Filter
        filterFruits();
    }

    public void filterFruits() {
        // Filter only the fruits where quantity > 0, while incrementing the cartTotal
        for( int i = 0; i < fruits.size(); ++i ) {
            if( fruits.get(i).getQuantity() > 0 ) {
                filteredFruits.add( fruits.get(i) );
                cartTotal += fruits.get(i).getQuantity() * fruits.get(i).getPrice();
            }
        }

        // Display fruits if filtered are > 0
        if( filteredFruits.size() > 0 ) {
            displayFruits();
        }else {
            cartTotalTV.setText("Cart is empty");
        }
    }

    private void displayFruits() {
        // Display also total
        cartTotalTV.setText( "Ksh. " + cartTotal );

        // Create adapter passing in the sample user data
        cartAdapter = new CartAdapter(filteredFruits);
        // Attach the adapter to the recyclerview to populate items
        rvFruits.setAdapter(cartAdapter);
        // Set layout manager to position the items
        rvFruits.setLayoutManager(new LinearLayoutManager(this));
        // Set fixed size
        rvFruits.setHasFixedSize(true);
    }

    @Override
    public void onBackPressed() {
        // To avoid coming back to this activity onBackPressed in Main Activity
        finish();
    }
}