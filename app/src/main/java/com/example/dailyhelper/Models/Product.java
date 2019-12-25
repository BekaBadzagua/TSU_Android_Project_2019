package com.example.dailyhelper.Models;

public class Product {
    public int Quontity;
    public String Name;

    Product(){}
    public Product(int quontity, String name) {
        Quontity = quontity;
        Name = name;
    }

    @Override
    public String toString() {
        return Name;
    }
}
