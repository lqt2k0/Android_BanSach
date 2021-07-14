package com.example.baitapandroid;

import java.util.ArrayList;

public class Categories {
    String name;
    ArrayList<Furniture> arrayList;
    int image;

    public Categories(String name, ArrayList<Furniture> arrayList) {
        this.name = name;
        this.arrayList = arrayList;
    }

    public Categories(String name, ArrayList<Furniture> arrayList, int image) {
        this.name = name;
        this.arrayList = arrayList;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Furniture> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Furniture> arrayList) {
        this.arrayList = arrayList;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
