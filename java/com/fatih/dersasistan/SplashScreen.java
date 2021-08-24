package com.fatih.dersasistan;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SplashScreen extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}
