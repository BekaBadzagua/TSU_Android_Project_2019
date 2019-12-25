

package com.example.dailyhelper.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dailyhelper.Models.Product;
import com.example.dailyhelper.Models.ProductAdapter;
import com.example.dailyhelper.Models.ProductCollection;
import com.example.dailyhelper.Models.Reminder;
import com.example.dailyhelper.Models.RemindersCollection;
import com.example.dailyhelper.R;
import com.example.dailyhelper.Repository.RemindersRepository;
import com.example.dailyhelper.Repository.ShoppingRepository;

import java.util.ArrayList;


public class AssistantFragment extends Fragment {


    private ShoppingRepository shoppingRepository;
    private ListView listView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_assistant, container, false);
        shoppingRepository = new ShoppingRepository(getContext());

        Button btnClear = (Button)rootView.findViewById(R.id.btn_clear_all);
        Button btnAdd = (Button)rootView.findViewById(R.id.btn_add_product);
        listView = (ListView)rootView.findViewById(R.id.products_list);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmnt_placeholder, new UpdateShoppingListFragment());
                ft.commit();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shoppingRepository.ClearProductCollection();
                LoadList();
                setItemsListeners();
            }
        });



        LoadList();
        setItemsListeners();

        return rootView;
    }


    private void LoadList(){

        ProductCollection productCol = shoppingRepository.GetProducts();

        ArrayList<Product> items = productCol.productList;

        ProductAdapter productAdapter = new ProductAdapter(getContext(), R.layout.product_adapter_row, R.id.name, items);


        listView.setAdapter(productAdapter);
    }


    private  void setItemsListeners(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View arg1,
                                           int position, long id) {

                Product selectedItem = (Product) parent.getItemAtPosition(position);
                shoppingRepository.DeleteProduct(selectedItem);
                Toast.makeText(getContext(),selectedItem.toString() + " Deleted",Toast.LENGTH_LONG).show();
                LoadList();
                return true;
            }
        });
    }


}
