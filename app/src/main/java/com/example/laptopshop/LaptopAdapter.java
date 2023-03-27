package com.example.laptopshop;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.ViewHolder>
{
    LaptopData[] laptopData;
    Context context;

    public LaptopAdapter(LaptopData[] laptopData, ShopActivity activity)
    {
        this.laptopData = laptopData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.laptop_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final LaptopData laptopDataList = laptopData[position];
        holder.textName.setText(laptopDataList.getLaptopName());
        holder.textDescription.setText(laptopDataList.getLaptopDescription());
        holder.laptopImage.setImageResource(laptopDataList.getLaptopImage());
        holder.price = laptopDataList.getLaptopPrice();

        int tempImage = Integer.parseInt(String.valueOf(holder.laptopImage.getId()));

        holder.purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, laptopDataList.getLaptopName() + " was added to your cart.", Toast.LENGTH_SHORT).show();

                //CartActivity.cartData = new CartData[]{ new CartData(laptopDataList.getLaptopName(), laptopDataList.getLaptopDescription(), holder.price) };

                CartData cd = new CartData(laptopDataList.getLaptopName(), laptopDataList.getLaptopDescription(), holder.price);
                CartActivity.cartData2.add(cd);

                ServerClient.sendToServer("Added " + holder.textName.getText() + " in cart.");
            }
        });
    }

    @Override
    public int getItemCount() {
        return laptopData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView laptopImage;
        TextView textName;
        TextView textDescription;
        Button purchaseButton;
        float price;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            laptopImage = itemView.findViewById(R.id.imageView);
            textName = itemView.findViewById(R.id.textName);
            textDescription = itemView.findViewById(R.id.textDescription);
            purchaseButton = itemView.findViewById(R.id.purchaseButton);
        }
    }
}
