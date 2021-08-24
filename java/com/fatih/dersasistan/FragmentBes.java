package com.fatih.dersasistan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FragmentBes extends Fragment {




    CalendarView calendarView;
    ListView listView;

    veriKaynagi vk;

    ListAdapter listAdapter;
    SimpleDateFormat formatter;
    ArrayList<Ders> list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View rootView=inflater.inflate(R.layout.fragment_bes_layout,container,false);


        calendarView=rootView.findViewById(R.id.calendarView);
        listView=rootView.findViewById(R.id.listviewtakvim);






        vk=new veriKaynagi(getActivity().getBaseContext());
        vk.ac();

        formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date today=new Date();
        String stoday=formatter.format(today);
        list= (ArrayList<Ders>) vk.tarihegorelistele(stoday);
        listAdapter=new ListAdapter(getActivity().getBaseContext(),list);

        listView.setAdapter(listAdapter);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Date date1 = new Date(year-1900,month,dayOfMonth);
                String date=formatter.format(date1);

               // Toast.makeText(getActivity().getBaseContext(),date,Toast.LENGTH_LONG).show();

                list= (ArrayList<Ders>) vk.tarihegorelistele(date);
                listAdapter=new ListAdapter(getActivity().getBaseContext(),list);

                listView.setAdapter(listAdapter);



            }
        });


        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getActivity().getBaseContext(),ActivityKayitEkleme.class);
                Ders d=list.get(i);
                intent.putExtra("dersid",d.getId());
                startActivity(intent);
                getActivity().finish();

            }
        });





        return rootView;
    }



}
