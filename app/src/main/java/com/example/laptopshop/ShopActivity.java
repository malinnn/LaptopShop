package com.example.laptopshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        LaptopData[] laptopData = new LaptopData[] {
                new LaptopData("Huawei Matebook", "Specs : Ryzen 7 3700U, 16GB RAM, 256GB SSD, 13'' 2K\nPrice : 2599,99 RON", R.drawable.matebook, 2599.99f),
                new LaptopData("ASUS ROG Strix G17", "Specs : Ryzen 7 4800H, 32GB RAM, 512GB SSD, 17.3'' FHD\nPrice : 3999,99 RON", R.drawable.asus_rog, 3999.99f),
                new LaptopData("LENOVO Legion7Pro", "Specs : i9-13900HX, 32GB RAM, 1TB SSD, 16'' WQXGA\nPrice : 16.499,90 RON", R.drawable.lenovo7, 16499.90f),
                new LaptopData("ASUS ROG Strix G15", "Specs : Ryzen 9 6900HX, 64GB RAM, 1TB SSD, 15.6'' FHD\nPrice : 8.499,90 lei", R.drawable.asus_rog15, 8499.90f),
        };

        LaptopAdapter laptopAdapter = new LaptopAdapter(laptopData, ShopActivity.this);
        rv.setAdapter(laptopAdapter);
    }
}