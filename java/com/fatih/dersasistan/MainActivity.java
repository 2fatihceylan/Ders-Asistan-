package com.fatih.dersasistan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        bottomNavigationView=findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_tutucu,new FragmentBes()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                if (item.getItemId()==R.id.action_dort){
                    tempFragment=new FragmentDort();
                }
                if (item.getItemId()==R.id.action_bes){
                    tempFragment=new FragmentBes();
                }
                if (item.getItemId()==R.id.action_birinci){
                    tempFragment=new FragmentBirinci();
                }
                if (item.getItemId()==R.id.action_ikinci){
                    tempFragment=new FragmentIkinci();
                }
                if (item.getItemId()==R.id.action_ucuncu){
                    tempFragment=new FragmentUcuncu();
                }


                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,tempFragment).commit();

                return true;
            }
        });
    }


}