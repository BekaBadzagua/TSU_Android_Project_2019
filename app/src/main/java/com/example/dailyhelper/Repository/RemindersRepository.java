package com.example.dailyhelper.Repository;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.dailyhelper.Models.Reminder;
import com.example.dailyhelper.Models.RemindersCollection;
import com.google.gson.Gson;

public class RemindersRepository  {

    private final static String Reminders_Key = "Reminders_Key";

    private Gson gson = new Gson();
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;

    public RemindersRepository(Context _context){
        sharedPref = _context.getSharedPreferences(Reminders_Key,Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
    }


    public void AddReminder(Reminder reminder){

        RemindersCollection remindersCollection = GetReminders();
        remindersCollection.reminderList.add(reminder);

        SaveCollection(remindersCollection);
    }

    public void DeleteReminder(Reminder reminder){

        RemindersCollection remindersCollection = GetReminders();

        for (Reminder _reminder : remindersCollection.reminderList) {
            if(_reminder.text.equals(reminder.text) ) {
                remindersCollection.reminderList.remove(_reminder);
                break;
            }
        }

        SaveCollection(remindersCollection);
    }

    public RemindersCollection GetReminders(){

        String jsonString = sharedPref.getString(Reminders_Key,null);
        RemindersCollection remindersCollection = gson.fromJson(jsonString, RemindersCollection.class);

        if(remindersCollection == null){
            remindersCollection = new RemindersCollection();
        }

        return remindersCollection;
    }

    private void SaveCollection(RemindersCollection remindersCollection){

        String jsonString = gson.toJson(remindersCollection);
        sharedPrefEditor.putString(Reminders_Key,jsonString);
        sharedPrefEditor.commit();
    }


}
