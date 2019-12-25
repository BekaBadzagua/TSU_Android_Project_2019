

package com.example.dailyhelper.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dailyhelper.Models.Product;
import com.example.dailyhelper.Models.Reminder;
import com.example.dailyhelper.R;
import com.example.dailyhelper.Repository.RemindersRepository;
import com.example.dailyhelper.Repository.ShoppingRepository;


public class UpdateShoppingListFragment extends Fragment {

    private ShoppingRepository shoppingRepository;
    private EditText ET_product;
    private EditText ET_quontity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  =  inflater.inflate(R.layout.fragment_shoppinglist_update, container, false);
        shoppingRepository = new ShoppingRepository(getContext());

        ET_product = (EditText) view.findViewById(R.id.ET_product);
        ET_quontity = (EditText) view.findViewById(R.id.ET_number);

        Button btnSave = view.findViewById(R.id.btn_save_reminder);

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SaveProduct();
                ReturnToShoppingList();
            }
        });

        return view;
    }



    private void SaveProduct(){
        String text = ET_product.getText().toString();
        int number = Integer.parseInt(ET_quontity.getText().toString());
        Product product = new Product(number,text);

        shoppingRepository.AddProduct(product);
    }



    private void ReturnToShoppingList(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmnt_placeholder, new AssistantFragment());
        ft.commit();


}


    public UpdateShoppingListFragment(){}
}
