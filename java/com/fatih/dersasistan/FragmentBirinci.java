package com.fatih.dersasistan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentBirinci extends Fragment {

    veriKaynagi vk;

    ListView listView;
    Button buttonekle;
    Activity a;




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        vk = new veriKaynagi(activity);
        a=activity;
    }


    @Nullable
    //@org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView=inflater.inflate(R.layout.fragment_birinci_layout,container,false);



        listView=rootView.findViewById(R.id.listview);
        buttonekle=rootView.findViewById(R.id.buttonekle);



        vk.ac();






        ArrayList<Ders> list= (ArrayList<Ders>) vk.listele();


        ListAdapter listAdapter=new ListAdapter(getActivity().getBaseContext(),list);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getActivity().getBaseContext(),ActivityKayitEkleme.class);
                intent.putExtra("position",i);
                startActivity(intent);
                getActivity().finish();

            }
        });



        buttonekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity().getBaseContext(),ActivityKayitEkleme.class);
                startActivity(intent);

                getActivity().finish();

                //Ders ders=new Ders("Matematik","birşeyler","10:00","12:00","hayir","bugün","false");
                //vk.dersOlustur(ders);
            }
        });






        return rootView;
    }

    public void onResume(){
        vk.ac();
        super.onResume();
    }

    public void onPause(){
        vk.kapat();
        super.onPause();
    }
}
