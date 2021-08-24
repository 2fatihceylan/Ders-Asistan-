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
import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Ders> {


    LayoutInflater layoutInflater;

    public SpinnerAdapter(@NonNull Context context, int resource, ArrayList<Ders> list) {
        super(context, resource,list);

        layoutInflater=LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rowView=layoutInflater.inflate(R.layout.custom_spinner,null,true);
        Ders ders=getItem(position);
        TextView textView=(TextView)rowView.findViewById(R.id.textviewspinner);
        textView.setText(ders.getDersadi());
        ImageView imageView=rowView.findViewById(R.id.csimageview);


        return rowView;
    }


    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {

        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.custom_spinner,parent,false);
        }
        Ders ders=getItem(position);
        TextView textView=(TextView)convertView.findViewById(R.id.textviewspinner);
        textView.setText("    "+ders.getDersadi()+"     ");


        return convertView;
    }
}
