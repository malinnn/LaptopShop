package com.example.laptopshop;

public class LaptopData
{
    private String laptopName;
    private String laptopDescription;
    private Integer laptopImage;
    private float laptopPrice;

    public LaptopData(String laptopName, String laptopDescription, Integer laptopImage, float laptopPrice)
    {
        this.laptopName = laptopName;
        this.laptopDescription = laptopDescription;
        this.laptopImage = laptopImage;
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

    public Integer getLaptopImage() {
        return laptopImage;
    }

    public void setLaptopImage(Integer laptopImage) {
        this.laptopImage = laptopImage;
    }

    public float getLaptopPrice() {
        return laptopPrice;
    }

    public void setLaptopPrice(float laptopPrice) {
        this.laptopPrice = laptopPrice;
    }
}
