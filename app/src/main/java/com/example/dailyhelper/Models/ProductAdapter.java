package com.example.dailyhelper.Models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dailyhelper.R;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    private ArrayList<Product> data;


    public ProductAdapter(Context context, int resource,
                        int textViewResourceId, ArrayList<Product> objects) {
        super(context, resource, textViewResourceId, objects);
        data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // let the adapter bind the name to the list
        View v = super.getView(position, convertView, parent);
        // find the counter TextView so we can update it's value
        TextView counterTv = (TextView) v.findViewById(R.id.number);
        //get the data from the list for this row.
        Product obj = data.get(position);
        //set the counter value for this row
        counterTv.setText(String.valueOf(obj.Quontity));
        return v;
    }
}
