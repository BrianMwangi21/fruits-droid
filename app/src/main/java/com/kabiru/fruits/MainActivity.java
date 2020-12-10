package com.kabiru.fruits;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FruitListener {

    ArrayList<Fruits> fruits = new ArrayList<>();
    RecyclerView rvFruits;
    FruitsAdapter fruitsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize fruits if in bundle, or createInitList
        Bundle bundle = getIntent().getExtras();
        fruits = (bundle != null) ? (ArrayList<Fruits>) bundle.getSerializable("fruits") : createInitList();

        // Initialize views
        rvFruits = (RecyclerView) findViewById(R.id.mainRecyclerView);

        // Display fruits
        displayFruits();
    }

    private void displayFruits() {
        // Create adapter passing in the sample user data
        fruitsAdapter = new FruitsAdapter(fruits, this, this);
        // Attach the adapter to the recyclerview to populate items
        rvFruits.setAdapter(fruitsAdapter);
        // Set layout manager to position the items
        rvFruits.setLayoutManager(new LinearLayoutManager(this));
        // Set fixed size
        rvFruits.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Bundle bundle = new Bundle();
        bundle.putSerializable("fruits", (Serializable) fruits);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            // Go to add fruit
            Intent intent = new Intent( MainActivity.this, AddFruitActivity.class );
            intent.putExtras( bundle );
            startActivity( intent );
        }else if(id == R.id.action_cart) {
            // Go to cart
            Intent intent = new Intent( MainActivity.this, CartActivity.class );
            intent.putExtras( bundle );
            startActivity( intent );
        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<Fruits> createInitList() {
        String _fruits[] = new String[]{"Mango", "Apple", "Avocado"};
        int _prices[] = new int[]{40,35,25};
        ArrayList<Fruits> fruits = new ArrayList<Fruits>();

        for (int i = 0; i < _fruits.length; i++) {
            fruits.add(new Fruits(_fruits[i], _prices[i]));
        }
        return fruits;
    }

    @Override
    public void addToCartClick(int position) {
        // Add specific fruit
        fruits.get(position).setQuantity( fruits.get(position).getQuantity() + 1 );
        displayFruits();
    }

    @Override
    public void minusFromCartClick(int position) {
        // Minus specific fruit
        if( fruits.get(position).getQuantity() > 0 ) {
            fruits.get(position).setQuantity( fruits.get(position).getQuantity() - 1 );
        }
        displayFruits();
    }
}