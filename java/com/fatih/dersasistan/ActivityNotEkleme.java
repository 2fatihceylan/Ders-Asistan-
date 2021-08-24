package com.fatih.dersasistan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityNotEkleme extends AppCompatActivity {


    EditText editTextkonu,editTextnot;
    TextView textnot;


    veriKaynagi vk;

    Date today;
    boolean notvarmi=false;
    public Not not;
    ImageView back,delete,save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ekleme);

        editTextkonu=findViewById(R.id.editTextkonu);
        editTextnot=findViewById(R.id.editTextnot);
        textnot=findViewById(R.id.textnot);
        back=findViewById(R.id.imageback);
        delete=findViewById(R.id.imagedelete);
        save=findViewById(R.id.imagesave);


        vk=new veriKaynagi(ActivityNotEkleme.this);
        vk.ac();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if (bundle!=null) {
            String notid = bundle.getString("notid");

            not=vk.getNot(Integer.parseInt(notid));

            editTextnot.setText(not.getNoticerik());
            editTextkonu.setText(not.getNotkonu());

            notvarmi=true;

        }















        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                today=new Date();
                String stoday=formatter.format(today);


                if (notvarmi){
                    not.setNotkonu(editTextkonu.getText().toString());
                    not.setNoticerik(editTextnot.getText().toString());
                    not.setNottarih(stoday);
                    vk.notGuncelle(not);
                }else {
                    vk.notOlustur(new Not(editTextnot.getText().toString(),editTextkonu.getText().toString(),stoday));

                }

                Intent intentgeri=new Intent(ActivityNotEkleme.this,MainActivity.class);
                startActivity(intentgeri);
                finish();


            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vk.notSil(not);

                Intent intentgeri=new Intent(ActivityNotEkleme.this,MainActivity.class);
                startActivity(intentgeri);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentgeri=new Intent(ActivityNotEkleme.this,MainActivity.class);
                startActivity(intentgeri);
                finish();
            }
        });
    }




}