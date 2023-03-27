package com.example.laptopshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>
{
    //CartData[] laptopData;
    List<CartData> laptopData = new ArrayList<CartData>();
    Context context;

    float totalPrice = 0f;

    public CartAdapter(List<CartData> laptopData, CartActivity activity)
    {
        this.laptopData = laptopData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.cart_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        //final CartData laptopDataList = laptopData[position];
        final CartData laptopDataList = laptopData.get(position);

        holder.textName.setText(laptopDataList.getLaptopName());
        holder.textDescription.setText(laptopDataList.getLaptopDescription());

        totalPrice += laptopDataList.getLaptopPrice();
        CartActivity.totalPriceText.setText("Total : " + String.valueOf(totalPrice) + " RON");
    }

    @Override
    public int getItemCount() {
        //return laptopData.length;
        return laptopData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textName;
        TextView textDescription;

        //TextView textTotal;
        //Button purchaseButton;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textName = itemView.findViewById(R.id.textNameCart);
            textDescription = itemView.findViewById(R.id.textDescriptionCart);
            //purchaseButton = itemView.findViewById(R.id.placeOrderButton);
            //textTotal = itemView.findViewById(R.id.totalPriceText);
        }
    }
}
