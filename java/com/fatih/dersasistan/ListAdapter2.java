package com.fatih.dersasistan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter2 extends ArrayAdapter<Not> {


    public ListAdapter2(Context context, ArrayList<Not> notArrayList){
        super(context,R.layout.list_item2,notArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Not not=getItem(position);

        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item2,parent,false);
        }


        TextView textViewsira=convertView.findViewById(R.id.textViewSira);
        TextView textViewkonu=convertView.findViewById(R.id.textViewnotkonu);
        TextView textViewnot=convertView.findViewById(R.id.textViewnot);
        TextView textViewtarih=convertView.findViewById(R.id.textViewtarih);



        textViewsira.setText(not.getNotid());
        textViewkonu.setText(not.getNotkonu());
        textViewnot.setText(not.getNoticerik());
        textViewtarih.setText(not.getNottarih());



        return convertView;
    }
}
