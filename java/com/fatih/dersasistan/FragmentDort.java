package com.fatih.dersasistan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentDort  extends Fragment {


    ListView listView;
    Button btnnotekle;
    veriKaynagi vk;


    private Fragment tempFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {

        final View rootView=inflater.inflate(R.layout.fragment_dort_layout,container,false);

        listView=rootView.findViewById(R.id.listviewnot);
        btnnotekle=rootView.findViewById(R.id.buttonnotekle);

        vk=new veriKaynagi(getActivity().getBaseContext());
        vk.ac();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date today=new Date();
        String stoday=formatter.format(today);





        ArrayList<Not> list= (ArrayList<Not>) vk.notlistele();


        ListAdapter2 listAdapter=new ListAdapter2(getActivity().getBaseContext(),list);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Not not=list.get(i);


                Intent intent=new Intent(getActivity().getBaseContext(),ActivityNotEkleme.class);
                intent.putExtra("notid", not.getNotid());
                startActivity(intent);



            }
        });



        btnnotekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getBaseContext(),ActivityNotEkleme.class);

                startActivity(intent);
            }
        });




        return rootView;
    }
}
