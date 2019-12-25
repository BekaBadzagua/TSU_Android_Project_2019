package com.example.dailyhelper.Repository;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.dailyhelper.Models.Product;
import com.example.dailyhelper.Models.ProductCollection;
import com.google.gson.Gson;

public class ShoppingRepository {

    private final static String Shopping_Key = "Shopping_Key";

    private Gson gson = new Gson();
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;

    public ShoppingRepository(Context _context){
        sharedPref = _context.getSharedPreferences(Shopping_Key,Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
    }


    public void AddProduct(Product product){

        ProductCollection productCollection = GetProducts();

        boolean prodAlreadyExists = false;
        for (Product prod : productCollection.productList) {
          if(prod.Name.equals(product.Name)){
              prod.Quontity += product.Quontity;
              prodAlreadyExists=true;
          }
        }

        if(!prodAlreadyExists){
            productCollection.productList.add(product);
        }

        SaveProductCollection(productCollection);
    }

    public void DeleteProduct(Product product){
        ProductCollection productCollection = GetProducts();

        for (Product prod : productCollection.productList) {
            if(prod.Name.equals(product.Name) ){
                productCollection.productList.remove(prod);
                break;
            }
        }
        SaveProductCollection(productCollection);
    }

    public ProductCollection GetProducts(){
        String jsonString = sharedPref.getString(Shopping_Key,null);
        ProductCollection productCollection = gson.fromJson(jsonString, ProductCollection.class);

        if(productCollection == null){
            productCollection = new ProductCollection();
        }

        return productCollection;
    }

    public void ClearProductCollection(){
        sharedPrefEditor.putString(Shopping_Key,"");
        sharedPrefEditor.commit();
    }

    private void SaveProductCollection(ProductCollection productCollection){
        String jsonString = gson.toJson(productCollection);
        sharedPrefEditor.putString(Shopping_Key,jsonString);
        sharedPrefEditor.commit();
    }

}
