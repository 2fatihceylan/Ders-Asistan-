package com.fatih.dersasistan;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Ders> {


    public ListAdapter(Context context, ArrayList<Ders> dersArrayList){
        super(context,R.layout.list_item,dersArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Ders ders=getItem(position);

        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }


        TextView textViewtarih=convertView.findViewById(R.id.textViewtarih);
        TextView textViewbassaat=convertView.findViewById(R.id.textViewbassaat);
        TextView textViewbitsaat=convertView.findViewById(R.id.textViewbitsaat);
        TextView textViewicerik=convertView.findViewById(R.id.textViewicerik);
        ImageView imageViewalarm=convertView.findViewById(R.id.imageviewalarm);
        TextView textViewdersinadi=convertView.findViewById(R.id.textViewdedersinadi);
        ImageView imageViewyap=convertView.findViewById(R.id.imageviewyap);
        ImageView imageViewrenk=convertView.findViewById(R.id.imageviewrenk);


        textViewtarih.setText(ders.getTarih());
        textViewbassaat.setText("  "+ders.getBassaat());
        textViewbitsaat.setText("  "+ders.getBitsaat());
        textViewdersinadi.setText(ders.getDersadi());
        textViewicerik.setText(ders.getAciklama());

        imageViewyap.setVisibility(View.INVISIBLE);



        imageViewrenk.setImageResource(R.drawable.ic_baseline_lens_24);
        int renk=Integer.parseInt(ders.getRenk());
        imageViewrenk.setColorFilter(renk);

        imageViewalarm.setColorFilter(Color.parseColor("#FF8C00"));

        if (ders.getYapildimi().equals("true")){
            imageViewyap.setImageResource(R.drawable.ic_baseline_thumb_up_24);
            imageViewyap.setVisibility(View.VISIBLE);
        }
        else {
           // imageViewyap.setImageResource(R.drawable.ic_baseline_thumb_down_24);
        }


        if(ders.getAnimsat().equals("bildirim")){
            imageViewalarm.setImageResource(R.drawable.ic_baseline_notifications_active_24);
            imageViewalarm.setColorFilter(Color.parseColor("#FFA500"));
        }
        else if(ders.getAnimsat().equals("hatirlatma")){
            imageViewalarm.setImageResource(R.drawable.ic_baseline_notifications_off_24);
            imageViewalarm.setColorFilter(Color.parseColor("#6495ED"));
        }
        else{

        }


        return convertView;
    }
}
