package com.charlesmarino.tustle;

/**
 * Object holding data for product
 * Created by kirstiebooras on 8/30/15.
 */
public class Product {

    private String mName;
    private String mDescription;
    private String mCost;
    private byte[] mImage;

    public Product() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getCost() {
        return mCost;
    }

    public void setCost(String cost) {
        this.mCost = cost;
    }

    public byte[] getImage() {
        return mImage;
    }

    public void setImage(byte[] image) {
        this.mImage = image;
    }
}
