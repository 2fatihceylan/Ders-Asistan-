package com.fatih.dersasistan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityAyar extends AppCompatActivity {


    SharedPreferences sp;
    SharedPreferences.Editor e;
    veriKaynagi vk;

    Button buttonsil;

    EditText editTextkadi;
    ImageView imageViewavatar,back,save;

    Uri imageUri;
    String theme;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayar);

        editTextkadi=findViewById(R.id.editTextkullaniciadi);
        imageViewavatar=findViewById(R.id.imageViewavatar);
        back=findViewById(R.id.imagegeri);
        save=findViewById(R.id.imagekayit);
        buttonsil=findViewById(R.id.buttonsil);

        vk=new veriKaynagi(ActivityAyar.this);
        vk.ac();

        sp=getSharedPreferences("Kullanici", Context.MODE_PRIVATE);
        e=sp.edit();


        editTextkadi.setText(sp.getString("kullaniciadi",""));

        String path=sp.getString("kullaniciavatar","def");

        if (path.equals("def")){
            imageViewavatar.setImageResource(R.drawable.ic_baseline_account_circle_24);
        }
        else {
            Uri uri=Uri.parse(path);
            imageViewavatar.setImageURI(uri);

        }



        buttonsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAyar.this);
                builder.setTitle("Silme İşlemi");
                builder.setMessage("Tüm ders verilerini silmek istediğinizden emin misiniz?");
                builder.setNegativeButton("Hayır", null);
                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        vk.dersprogramSil();
                        vk.dersskorSil();

                    }
                });
                builder.show();

            }
        });


        imageViewavatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gallery = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, 100);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                e.putString("kullaniciadi",editTextkadi.getText().toString());
                e.commit();

                Intent intentgeri=new Intent(ActivityAyar.this,MainActivity.class);
                startActivity(intentgeri);
                finish();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentgeri=new Intent(ActivityAyar.this,MainActivity.class);
                startActivity(intentgeri);
                finish();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100){
            imageUri = data.getData();
            imageViewavatar.setImageURI(imageUri);

            String stringUri=imageUri.toString();

            e.putString("kullaniciavatar",stringUri);

            e.commit();
        }
    }
}