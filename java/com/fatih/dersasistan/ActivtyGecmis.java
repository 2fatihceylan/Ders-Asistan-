package com.fatih.dersasistan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivtyGecmis extends AppCompatActivity {


    ListView listView;

    veriKaynagi vk;

    int sure=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activty_gecmis);

        listView=findViewById(R.id.listviewgecmis);

        vk=new veriKaynagi(ActivtyGecmis.this);
        vk.ac();

        ArrayList<Ders> list= (ArrayList<Ders>) vk.yapilanilistele();

        ArrayList<Skor> skorlist=(ArrayList<Skor>)vk.skorListele();


        ListAdapter listAdapter=new ListAdapter(ActivtyGecmis.this,list);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Ders ders=list.get(i);

                for (Skor skor:skorlist){
                    if (String.valueOf(skor.getDersid()).equals(ders.getId())){
                        sure=sure+Integer.parseInt(skor.getSure());
                    }
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ActivtyGecmis.this);
                builder.setTitle(ders.getDersadi());
                builder.setMessage("Toplam çalışılan süre: "+sure/60+" dk");


                builder.show();





                sure=0;

            }
        });




    }
}