package com.fatih.dersasistan;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.shape.CornerFamily;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentUcuncu extends Fragment {


    ImageView buttongraph,buttongecmis,buttonayar,imageViewavatar;
    TextView tvgun,tvhafta,tvtop,tvkullaniciadi;


    veriKaynagi vk;

    int bugun=0,hafta=0,toplam=0;

    SharedPreferences sp;
    SharedPreferences.Editor e;




    @Nullable
    //@org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView=inflater.inflate(R.layout.fragment_ucuncu_layout,container,false);

        buttongraph=rootView.findViewById(R.id.imageButtongraph);
        buttongecmis=rootView.findViewById(R.id.imageButtongecmis);
        buttonayar=rootView.findViewById(R.id.imageButtonayar);
        tvgun=rootView.findViewById(R.id.textViewgun);
        tvhafta=rootView.findViewById(R.id.textViewhafta);
        tvtop=rootView.findViewById(R.id.textViewtoplam);
        tvkullaniciadi=rootView.findViewById(R.id.textViewkullaniciadi);
        imageViewavatar=rootView.findViewById(R.id.imageviewavatar);


        buttongraph.setColorFilter(Color.parseColor("#EC9B5D"));
        buttongecmis.setColorFilter(Color.parseColor("#EC9B5D"));
        buttonayar.setColorFilter(Color.parseColor("#EC9B5D"));



        vk=new veriKaynagi(getActivity().getBaseContext());
        vk.ac();


        sp=this.getActivity().getSharedPreferences("Kullanici", Context.MODE_PRIVATE);

        e=sp.edit();

        tvkullaniciadi.setText(sp.getString("kullaniciadi","Kullanıcı Adı"));

        String path=sp.getString("kullaniciavatar","");

        //path ile imageview resim ekle

        String suri=sp.getString("kullaniciavatar","def");



        //path ile imageview resim ekle



        if (path.equals("def")){
            imageViewavatar.setImageResource(R.drawable.ic_baseline_account_circle_24);
        }
        else {
            Uri uri=Uri.parse(suri);
            imageViewavatar.setImageURI(uri);

        }





        ArrayList<Skor> skorlist=(ArrayList<Skor>) vk.skorListele();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new Date();

        String today=formatter.format(date1);


        long dayinms=1000*60*60*24;
        Date date2=new Date(System.currentTimeMillis()-(7*dayinms));


        for (Skor skor:skorlist){
            if (skor.getTarih().equals(today)){
                bugun=bugun+Integer.parseInt(skor.getSure());
            }

            try {
                if (date2.compareTo(formatter.parse(skor.getTarih()))<0){
                    hafta=hafta+Integer.parseInt(skor.getSure());

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }


            toplam=toplam+Integer.parseInt(skor.getSure());

        }







        tvgun.setText((bugun/60)+" dakika");
        tvhafta.setText((hafta/60)+" dakika");
        String top=(toplam/60)/60+" saat "+(toplam/60)%60 +"dakika";
        tvtop.setText(top);









        buttongraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getBaseContext(),ActivityGraph.class);
                startActivity(intent);
            }
        });



        buttongecmis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getBaseContext(),ActivtyGecmis.class);
                startActivity(intent);


            }
        });

        buttonayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getBaseContext(),ActivityAyar.class);
                startActivity(intent);
            }
        });


        return rootView;
    }


}
