package com.fatih.dersasistan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ActivityGraph extends AppCompatActivity {



    veriKaynagi vk;

    PieChart pieChart;

    RadarChart radarChart;

    HorizontalBarChart barChart;

    ArrayList<DersSure>  sureler;
    ArrayList<DersSure>  surelerhafta;
    ArrayList<Integer> ayliksure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);


        vk=new veriKaynagi(ActivityGraph.this);
        vk.ac();
        skortopla();
        haftaskortopla();
        aylikskor();



        pieChart=findViewById(R.id.pieChart);

        setPieChart();

        radarChart=findViewById(R.id.radarChart);

        setRadarChart();

        barChart=findViewById(R.id.barChart);

        setBarChart();




    }

    public void haftaskortopla(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        long dayinms=1000*60*60*24;
        Date date2=new Date(System.currentTimeMillis()-(7*dayinms));




        ArrayList<Skor> skorlar=(ArrayList<Skor>) vk.skorListele();

        ArrayList<Ders> dersler=(ArrayList<Ders>)vk.listele();

        surelerhafta=new ArrayList<>();


        for (int k=0;k<dersler.size();k++){

            surelerhafta.add(new DersSure(dersler.get(k).getId(),0));

            for (int i=0;i<skorlar.size();i++){




                try {
                    if (date2.compareTo(formatter.parse(skorlar.get(i).getTarih()))<0){

                        if (String.valueOf(skorlar.get(i).getDersid()).equals(dersler.get(k).getId())){

                            for (DersSure d:surelerhafta){
                                if (d.getDersid().equals(dersler.get(k).getId())){
                                    d.setDerstopsure(d.getDerstopsure()+Integer.parseInt(skorlar.get(i).getSure()));
                                }
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }






            }
        }


    }



    public void skortopla(){

        ArrayList<Skor> skorlar=(ArrayList<Skor>) vk.skorListele();

        ArrayList<Ders> dersler=(ArrayList<Ders>)vk.listele();

        sureler=new ArrayList<>();


        for (int k=0;k<dersler.size();k++){

            sureler.add(new DersSure(dersler.get(k).getId(),0));

            for (int i=0;i<skorlar.size();i++){


                if (String.valueOf(skorlar.get(i).getDersid()).equals(dersler.get(k).getId())){

                    for (DersSure d:sureler){
                        if (d.getDersid().equals(dersler.get(k).getId())){
                            d.setDerstopsure(d.getDerstopsure()+Integer.parseInt(skorlar.get(i).getSure()));
                        }
                    }
                }


            }
        }


    }

    public void aylikskor()  {

        ayliksure=new ArrayList<Integer>();
        ayliksure.add(0);ayliksure.add(0);ayliksure.add(0);ayliksure.add(0);ayliksure.add(0);ayliksure.add(0);ayliksure.add(0);ayliksure.add(0);ayliksure.add(0);ayliksure.add(0);ayliksure.add(0);ayliksure.add(0);
        ArrayList<Skor> skorlar=(ArrayList<Skor>) vk.skorListele();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        for (Skor s:skorlar){
            try {
                Date date= formatter.parse(s.getTarih());

                if (date.getMonth()==0){
                    ayliksure.set(0,ayliksure.get(0)+Integer.parseInt(s.getSure()));
                }
                else if (date.getMonth()==1){
                    ayliksure.set(1,ayliksure.get(1)+Integer.parseInt(s.getSure()));
                }
                else if (date.getMonth()==2){
                    ayliksure.set(2,ayliksure.get(2)+Integer.parseInt(s.getSure()));
                }
                else if (date.getMonth()==3){
                    ayliksure.set(3,ayliksure.get(3)+Integer.parseInt(s.getSure()));
                }
                else if (date.getMonth()==4){
                    ayliksure.set(4,ayliksure.get(4)+Integer.parseInt(s.getSure()));
                }
                else if (date.getMonth()==5){
                    ayliksure.set(5,ayliksure.get(5)+Integer.parseInt(s.getSure()));
                }else if (date.getMonth()==6){
                    ayliksure.set(6,ayliksure.get(6)+Integer.parseInt(s.getSure()));
                }else if (date.getMonth()==7){
                    ayliksure.set(7,ayliksure.get(7)+Integer.parseInt(s.getSure()));
                }else if (date.getMonth()==8){
                    ayliksure.set(8,ayliksure.get(8)+Integer.parseInt(s.getSure()));
                }else if (date.getMonth()==9){
                    ayliksure.set(9,ayliksure.get(9)+Integer.parseInt(s.getSure()));
                }
                else if (date.getMonth()==10){
                    ayliksure.set(10,ayliksure.get(10)+Integer.parseInt(s.getSure()));
                }
                else if (date.getMonth()==11){
                    ayliksure.set(11,ayliksure.get(11)+Integer.parseInt(s.getSure()));
                }



            }catch (Exception e){
                e.printStackTrace();
            }


        }


    }

    public void setPieChart(){
        ArrayList<PieEntry>  pieEntries=new ArrayList<>();

        for (DersSure d:sureler){
            Ders ders=vk.getDers(Integer.parseInt(d.getDersid()));
            pieEntries.add(new PieEntry(d.getDerstopsure()/60,ders.getDersadi()));
        }



        PieDataSet pieDataSet=new PieDataSet(pieEntries,"");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData=new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Toplam Süreler");
        pieChart.animate();

    }

    public void setRadarChart(){

        ArrayList<RadarEntry> radarEntries=new ArrayList<>();

        ArrayList<String> label=new ArrayList<>();


        for (DersSure d:surelerhafta){
            Ders ders=vk.getDers(Integer.parseInt(d.getDersid()));
            label.add(ders.getDersadi().toString());
            radarEntries.add(new RadarEntry(d.getDerstopsure()/60));
        }


        RadarDataSet radarDataSet=new RadarDataSet(radarEntries,"-");
        radarDataSet.setColor(Color.RED);
        radarDataSet.setLineWidth(2f);
        radarDataSet.setValueTextColor(Color.RED);
        radarDataSet.setValueTextSize(14f);

        RadarData radarData=new RadarData();
        radarData.addDataSet(radarDataSet);




        XAxis xAxis=radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(label));

        radarChart.getDescription().setEnabled(false);
        radarChart.setData(radarData);


    }


    public void setBarChart(){
        ArrayList<BarEntry> barEntries=new ArrayList<>();
        String[] labels={"-","Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};


        for (int i=0;i<12;i++){

            barEntries.add(new BarEntry(i+1,(ayliksure.get(i)/3600)));

        }


        BarDataSet barDataSet=new BarDataSet(barEntries,"Veriler");

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(0.001f);

        BarData barData=new BarData(barDataSet);
        barData.setBarWidth(0.6f);

        XAxis bottomAxis=barChart.getXAxis();
        bottomAxis.setLabelCount(barEntries.size()-1);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        barChart.setFitBars(false);
        barChart.setData(barData);
        barChart.setDrawBarShadow(true);



    }

}