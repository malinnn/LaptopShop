package com.example.laptopshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    public static List<CartData> cartData2 = new ArrayList<CartData>();

    public static CartData[] cartData = new CartData[50];

    public static TextView totalPriceText;

    Button purchaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalPriceText = findViewById(R.id.totalPriceText);

        RecyclerView rv = findViewById(R.id.recyclerViewCart);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        CartAdapter cartAdapter = new CartAdapter(cartData2, CartActivity.this);
        rv.setAdapter(cartAdapter);

        purchaseButton = findViewById(R.id.purchaseButtonCart);
        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = cartData2.size();
                if (size > 0)
                {
                    for (int i = 0; i < size; i++)
                        cartData2.remove(0);
                    Toast.makeText(CartActivity.this, "Purchase was successful.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(CartActivity.this, "Purchase can't be completed.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}