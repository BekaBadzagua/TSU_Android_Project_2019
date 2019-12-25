package com.example.dailyhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.example.dailyhelper.Fragments.AssistantFragment;
import com.example.dailyhelper.Fragments.ReminderFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);


    }

    public void LoadFragment(View view) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (view.getId()){
            case R.id.menu_reminder: ft.replace(R.id.fragmnt_placeholder, new ReminderFragment()); break;
            case R.id.menu_shopAssistant: ft.replace(R.id.fragmnt_placeholder, new AssistantFragment()); break;
        }

        ft.commit();
    }




}
