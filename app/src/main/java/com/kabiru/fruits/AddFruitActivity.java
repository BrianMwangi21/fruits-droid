package com.kabiru.fruits;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class AddFruitActivity extends AppCompatActivity {

    ArrayList<Fruits> fruits = new ArrayList<>();
    EditText newNameET, newPriceET;
    Button newPriceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fruit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get fruits from bundle from Intent
        Bundle bundle = getIntent().getExtras();
        fruits = (ArrayList<Fruits>) bundle.getSerializable("fruits");

        // Initialize views
        newNameET = findViewById(R.id.newNameET);
        newPriceET = findViewById(R.id.newPriceET);
        newPriceButton = findViewById(R.id.newFruitButton);

        // Set onclick listener
        newPriceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Check if fields are empty
            if( newNameET.getText().toString().isEmpty() || newPriceET.getText().toString().isEmpty() ) {
                Toast.makeText( AddFruitActivity.this, "Please fill all fields", Toast.LENGTH_SHORT ).show();
                return;
            }

            // Add new fruit to top of list
            fruits.add(0, new Fruits( newNameET.getText().toString(), Integer.parseInt(newPriceET.getText().toString()) ));

            // Return to MainActivity
            Intent intent = new Intent( AddFruitActivity.this, MainActivity.class );
            Bundle bundle = new Bundle();
            bundle.putSerializable("fruits", (Serializable) fruits);
            intent.putExtras( bundle );
            startActivity( intent );
            finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // To avoid coming back to this activity onBackPressed in Main Activity
        finish();
    }
}