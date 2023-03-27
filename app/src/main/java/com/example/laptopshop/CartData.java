package com.example.laptopshop;

public class CartData
{
    private String laptopName;
    private String laptopDescription;
    private float laptopPrice;

    public CartData(String laptopName, String laptopDescription, float laptopPrice)
    {
        this.laptopName = laptopName;
        this.laptopDescription = laptopDescription;
        this.laptopPrice = laptopPrice;
    }

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public String getLaptopDescription() {
        return laptopDescription;
    }

    public void setLaptopDescription(String laptopDescription) {
        this.laptopDescription = laptopDescription;
    }

    public float getLaptopPrice() {
        return laptopPrice;
    }

    public void setLaptopPrice(float laptopPrice) {
        this.laptopPrice = laptopPrice;
    }
}
