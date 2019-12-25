

package com.example.dailyhelper.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dailyhelper.Models.Reminder;
import com.example.dailyhelper.R;
import com.example.dailyhelper.Repository.RemindersRepository;


public class UpdateReminderFragment extends Fragment {

    private RemindersRepository remindersRepository;
    private EditText ET_reminder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  =  inflater.inflate(R.layout.fragment_reminder_update, container, false);
        remindersRepository = new RemindersRepository(getContext());

        ET_reminder = (EditText) view.findViewById(R.id.ET_reminder);

        Button btnSave = view.findViewById(R.id.btn_save_reminder);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SaveReminder();
                ReturnToReminders();

            }
        });



        return view;
    }



    private void SaveReminder(){

        String text = ET_reminder.getText().toString();
        if(checkValidity(text)){
            Reminder reminder = new Reminder(text);
            remindersRepository.AddReminder(reminder);
        }
    }

    private void ReturnToReminders(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmnt_placeholder, new ReminderFragment());
        ft.commit();
    }

    private boolean checkValidity(String st){
        if(st == null || st.equals("")){
            Toast.makeText(getContext(), "Reminder Can not be Empty", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }

    public UpdateReminderFragment(){}
}
