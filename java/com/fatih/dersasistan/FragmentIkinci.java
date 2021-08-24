package com.fatih.dersasistan;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentIkinci extends Fragment {


    Button buttonbaslat,buttondurdur,buttonkaydet;
    Spinner spinner;
    Chronometer chronometer;

    private boolean running;
    private long pauseOffset;


    veriKaynagi vk;
    Ders ders;
    ArrayList<Ders> list;
    String id;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView=inflater.inflate(R.layout.fragment_ikinci_layout,container,false);


        buttonbaslat=rootView.findViewById(R.id.buttonstart);
        buttondurdur=rootView.findViewById(R.id.buttonstop);
        buttonkaydet=rootView.findViewById(R.id.buttonkayitet);
        chronometer=rootView.findViewById(R.id.chronometer);
        spinner=rootView.findViewById(R.id.spinnertheme);

        vk=new veriKaynagi(getActivity().getBaseContext());
        vk.ac();






        list= (ArrayList<Ders>) vk.listele();

        ArrayList<String> dersadilist=new ArrayList<>();

     //   for (Ders ders:list){
       //     dersadilist.add(ders.getDersadi());
       // }

        SpinnerAdapter adapter=new SpinnerAdapter(getActivity().getBaseContext(),R.layout.custom_spinner,list);


       // ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, dersadilist);

        //spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);


        spinner.setAdapter(adapter);






        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ders=list.get(i);
                //Toast.makeText(getActivity().getBaseContext(), String.valueOf(i),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Bundle bundle=this.getArguments();
        if (bundle!=null){

            id=bundle.getString("id","");
            ders=vk.getDers(Integer.parseInt(id));

            for (int i=0;i<list.size();i++){
                if (list.get(i).getId().equals(id)){
                    spinner.setSelection(i);
                }
            }


        }


        buttonbaslat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!running){
                    chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
                    chronometer.start();
                    running=true;
                    buttonkaydet.setVisibility(View.GONE);
                    buttonbaslat.setBackgroundColor(Color.GREEN);
                    buttondurdur.setBackgroundColor(Color.parseColor("#EC5D5D"));

                }

            }
        });


        buttondurdur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running){
                    chronometer.stop();
                    pauseOffset=SystemClock.elapsedRealtime()-chronometer.getBase();
                    running=false;
                    buttonkaydet.setVisibility(View.VISIBLE);
                    buttonbaslat.setText("Devam Et");
                    buttonbaslat.setBackgroundColor(Color.parseColor("#EC5D5D"));
                    buttondurdur.setBackgroundColor(Color.GREEN);
                }
            }
        });


        buttonkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(getActivity().getBaseContext(),String.valueOf(pauseOffset/1000),Toast.LENGTH_SHORT).show();



                //veri tabanına skor kayıt işlemi

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date1 = new Date();

                String today=formatter.format(date1);








                Skor skor=new Skor(Integer.parseInt(ders.getId()),today,"-",String.valueOf(pauseOffset/1000));


                vk.skorOlustur(skor);

                ders.setYapildimi("true");

                vk.dersGuncelle(ders);




                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset=0;
                buttonkaydet.setVisibility(View.GONE);
                buttonbaslat.setText("Başlat");
                buttondurdur.setBackgroundColor(Color.parseColor("#EC5D5D"));
                buttonbaslat.setBackgroundColor(Color.parseColor("#EC5D5D"));

            }
        });


        return rootView;
    }
}
