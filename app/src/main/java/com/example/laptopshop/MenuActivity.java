package com.example.laptopshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void OpenAboutFragment(View view)
    {
        FragmentManager fm = getSupportFragmentManager();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, AboutUsFragment.class,null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }

    public void OpenFaqFragment(View view)
    {
        FragmentManager fm = getSupportFragmentManager();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, FaqFragment.class,null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }

    public void OpenContactFragment(View view)
    {
        FragmentManager fm = getSupportFragmentManager();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, ContactFragment.class,null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }

    public void OpenShopActivity(View view)
    {
        Intent i = new Intent(MenuActivity.this, ShopActivity.class);
        startActivity(i);
    }

    public void OpenCartActivity(View view)
    {
        Intent i = new Intent(MenuActivity.this, CartActivity.class);
        startActivity(i);
    }
}