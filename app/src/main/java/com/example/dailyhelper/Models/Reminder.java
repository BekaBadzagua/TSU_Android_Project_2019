package com.example.dailyhelper.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class Reminder {
    public String text;
    public Date date;


    public Reminder(){}
    public Reminder(String txt){
        text=txt;
        date = new Date();
    }

}
