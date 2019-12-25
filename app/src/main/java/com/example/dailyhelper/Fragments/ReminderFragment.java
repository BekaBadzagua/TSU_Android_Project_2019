

package com.example.dailyhelper.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailyhelper.Models.Reminder;
import com.example.dailyhelper.Models.RemindersCollection;
import com.example.dailyhelper.R;
import com.example.dailyhelper.Repository.RemindersRepository;

import java.util.ArrayList;


public class ReminderFragment extends Fragment {

    private RemindersRepository remindersRepository;
    private ListView listView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_reminder, container, false);
        remindersRepository = new RemindersRepository(getContext());

        Button btnAdd = (Button)rootView.findViewById(R.id.btn_add_reminder);
        listView = (ListView)rootView.findViewById(R.id.reminders_list);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmnt_placeholder, new UpdateReminderFragment());
                ft.commit();
            }
        });




        // Populate ListView
        LoadList();
        setItemsListeners();

        return rootView;
    }



    private void LoadList(){

        RemindersCollection remindersCol = remindersRepository.GetReminders();

        ArrayList<String> arrayList = new ArrayList<>();

        for (Reminder reminder: remindersCol.reminderList
             ) {
            arrayList.add(reminder.text);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);
    }


    private  void setItemsListeners(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View arg1,
                                           int position, long id) {

                String selectedItem = (String) parent.getItemAtPosition(position);
                Reminder reminder = new Reminder(selectedItem);

                remindersRepository.DeleteReminder(reminder);
                Toast.makeText(getContext(),"Reminder Deleted",Toast.LENGTH_LONG).show();
                LoadList();
                return true;
            }
        });
    }


}
