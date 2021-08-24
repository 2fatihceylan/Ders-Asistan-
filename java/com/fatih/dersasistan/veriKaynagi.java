package com.fatih.dersasistan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class veriKaynagi {

    SQLiteDatabase db;
    sql_katmani bdb;

    public veriKaynagi(Context context){
        bdb=new sql_katmani(context);
    }

    public void ac(){
        db=bdb.getWritableDatabase();
    }
    public void kapat(){
        bdb.close();
    }


    public void dersOlustur(Ders ders){
        ContentValues values=new ContentValues();
        values.put("dersadi",ders.getDersadi());
        values.put("aciklama",ders.getAciklama());
        values.put("bassaat",ders.getBassaat());
        values.put("bitsaat",ders.getBitsaat());
        values.put("animsat",ders.getAnimsat());
        values.put("tarih",ders.getTarih());
        values.put("yapildimi",ders.getYapildimi());
        values.put("renk",ders.getRenk());

        db.insert("programtablo",null,values);

    }

    public void dersGuncelle(Ders ders){

        ContentValues values=new ContentValues();
        values.put("dersadi",ders.getDersadi());
        values.put("aciklama",ders.getAciklama());
        values.put("bassaat",ders.getBassaat());
        values.put("bitsaat",ders.getBitsaat());
        values.put("animsat",ders.getAnimsat());
        values.put("tarih",ders.getTarih());
        values.put("yapildimi",ders.getYapildimi());
        values.put("renk",ders.getRenk());

        db.update("programtablo",values,"id=?",new String[]{ders.getId()});

    }
    public void dersSil(Ders ders){    //buna bak
        if (ders!=null){
            db.delete("programtablo","id="+ders.getId(),null);
        }
    }

    public List<Ders> listele(){

        String kolonlar[]={"id","dersadi","aciklama","bassaat","bitsaat","animsat","tarih","yapildimi","renk"};
        List<Ders> liste=new ArrayList<Ders>();

        Cursor cursor=db.query("programtablo",kolonlar,null,null,null,null,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int id=cursor.getInt(0);
            String dersadi=cursor.getString(1);
            String aciklama=cursor.getString(2);
            String bassaat=cursor.getString(3);
            String bitsaat=cursor.getString(4);
            String animsat=cursor.getString(5);
            String tarih=cursor.getString(6);
            String yapildimi=cursor.getString(7);
            String renk=cursor.getString(8);

            Ders d=new Ders(String.valueOf(id),dersadi,aciklama,bassaat,bitsaat,animsat,tarih,yapildimi,renk);

            liste.add(d);

            cursor.moveToNext();
        }
        cursor.close();

        return liste;

    }



    public List<Ders> tarihegorelistele(String trh){

        String kolonlar[]={"id","dersadi","aciklama","bassaat","bitsaat","animsat","tarih","yapildimi","renk"};
        List<Ders> liste=new ArrayList<Ders>();

        Cursor cursor=db.query("programtablo",kolonlar,null,null,null,null,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int id=cursor.getInt(0);
            String dersadi=cursor.getString(1);
            String aciklama=cursor.getString(2);
            String bassaat=cursor.getString(3);
            String bitsaat=cursor.getString(4);
            String animsat=cursor.getString(5);
            String tarih=cursor.getString(6);
            String yapildimi=cursor.getString(7);
            String renk=cursor.getString(8);



            Ders d=new Ders(String.valueOf(id),dersadi,aciklama,bassaat,bitsaat,animsat,tarih,yapildimi,renk);

            if (trh.equals(tarih)&&yapildimi.equals("false")){
                liste.add(d);
            }


            cursor.moveToNext();
        }
        cursor.close();

        return liste;

    }

    public List<Ders> yapilanilistele(){

        String kolonlar[]={"id","dersadi","aciklama","bassaat","bitsaat","animsat","tarih","yapildimi","renk"};
        List<Ders> liste=new ArrayList<Ders>();

        Cursor cursor=db.query("programtablo",kolonlar,null,null,null,null,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int id=cursor.getInt(0);
            String dersadi=cursor.getString(1);
            String aciklama=cursor.getString(2);
            String bassaat=cursor.getString(3);
            String bitsaat=cursor.getString(4);
            String animsat=cursor.getString(5);
            String tarih=cursor.getString(6);
            String yapildimi=cursor.getString(7);
            String renk=cursor.getString(8);



            Ders d=new Ders(String.valueOf(id),dersadi,aciklama,bassaat,bitsaat,animsat,tarih,yapildimi,renk);

            if (yapildimi.equals("true")){
                liste.add(d);
            }


            cursor.moveToNext();
        }
        cursor.close();

        return liste;

    }





    public Ders getDers(int dersid){
        String kolonlar[]={"id","dersadi","aciklama","bassaat","bitsaat","animsat","tarih","yapildimi","renk"};


        Cursor cursor=db.query("programtablo",kolonlar,null,null,null,null,null);
        cursor.moveToFirst();
        Ders d=new Ders("","","","","","","","");

        while (!cursor.isAfterLast()){
            int id=cursor.getInt(0);
            String dersadi=cursor.getString(1);
            String aciklama=cursor.getString(2);
            String bassaat=cursor.getString(3);
            String bitsaat=cursor.getString(4);
            String animsat=cursor.getString(5);
            String tarih=cursor.getString(6);
            String yapildimi=cursor.getString(7);
            String renk=cursor.getString(8);

            if (dersid==id){
                d=new Ders(String.valueOf(id),dersadi,aciklama,bassaat,bitsaat,animsat,tarih,yapildimi,renk);

            }

            cursor.moveToNext();
        }
        cursor.close();

        return d;

    }










    public List<Ders> bugunun_dersleri(){
        String kolonlar[]={"id","dersadi","aciklama","bassaat","bitsaat","animsat","tarih","yapildimi","renk"};
        List<Ders> liste=new ArrayList<Ders>();

        //tarihe göre listele işlemleri

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new Date();

        String today=formatter.format(date1);

        SimpleDateFormat formatter2=new SimpleDateFormat("HH:mm");
        Date time=new Date();

        String now= formatter2.format(time);

        Cursor cursor=db.query("programtablo",kolonlar,null,null,null,null,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int id=cursor.getInt(0);
            String dersadi=cursor.getString(1);
            String aciklama=cursor.getString(2);
            String bassaat=cursor.getString(3);
            String bitsaat=cursor.getString(4);
            String animsat=cursor.getString(5);
            String tarih=cursor.getString(6);
            String yapildimi=cursor.getString(7);
            String renk=cursor.getString(8);

            Ders d=new Ders(String.valueOf(id),dersadi,aciklama,bassaat,bitsaat,animsat,tarih,yapildimi,renk);

            if (today.equals(tarih)&&now.equals(bassaat)){
                liste.add(d);
            }



            cursor.moveToNext();
        }
        cursor.close();

        return liste;
    }




    public void skorOlustur(Skor skor){
        ContentValues values=new ContentValues();
        values.put("dersid",skor.getDersid());
        values.put("tarih",skor.getTarih());
        values.put("aciklama",skor.getAciklama());
        values.put("sure",skor.getSure());
        db.insert("skortablo",null,values);
    }

    public List<Skor> skorListele(){
        String kolonlar[]={"skorid","dersid","tarih","aciklama","sure"};
        List<Skor> liste=new ArrayList<Skor>();

        Cursor cursor=db.query("skortablo",kolonlar,null,null,null,null,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int skorid=cursor.getInt(0);
            int dersid=cursor.getInt(1);
            String tarih=cursor.getString(2);
            String aciklama=cursor.getString(3);
            String sure=cursor.getString(4);

            Skor skor=new Skor(skorid,dersid,tarih,aciklama,sure);
            liste.add(skor);

            cursor.moveToNext();
        }
        cursor.close();

        return liste;

    }

    public void notOlustur(Not not){
        ContentValues values=new ContentValues();
        values.put("noticerik",not.getNoticerik());
        values.put("notkonu",not.getNotkonu());
        values.put("tarih",not.getNottarih());


        db.insert("notlar",null,values);

    }
    public void notGuncelle(Not not){

        ContentValues values=new ContentValues();
        values.put("noticerik",not.getNoticerik());
        values.put("notkonu",not.getNotkonu());
        values.put("tarih",not.getNottarih());


        db.update("notlar",values,"notid=?",new String[]{not.getNotid()});

    }
    public void notSil(Not not){    //buna bak
        if (not!=null){
            db.delete("notlar","notid="+not.getNotid(),null);
        }
    }
    public List<Not> notlistele(){

        String kolonlar[]={"notid","noticerik","notkonu","tarih"};
        List<Not> liste=new ArrayList<Not>();

        Cursor cursor=db.query("notlar",kolonlar,null,null,null,null,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int notid=cursor.getInt(0);
            String noticerik=cursor.getString(1);
            String notkonu=cursor.getString(2);
            String tarih=cursor.getString(3);

            Not not=new Not(String.valueOf(notid),noticerik,notkonu,tarih);


            liste.add(not);

            cursor.moveToNext();
        }
        cursor.close();

        return liste;

    }

    public Not getNot(int notid){
        String kolonlar[]={"notid","noticerik","notkonu","tarih"};


        Cursor cursor=db.query("notlar",kolonlar,null,null,null,null,null);
        cursor.moveToFirst();
        Not not=new Not();

        while (!cursor.isAfterLast()){
            int id=cursor.getInt(0);
            String noticerik=cursor.getString(1);
            String notkonu=cursor.getString(2);
            String nottarih=cursor.getString(3);


            if (notid==id){
                not.setNotid(String.valueOf(id));
                not.setNotkonu(notkonu);
                not.setNoticerik(noticerik);
                not.setNottarih(nottarih);

            }

            cursor.moveToNext();
        }
        cursor.close();

        return not;

    }



    public void dersprogramSil(){    //buna bak

        db.delete("programtablo",null,null);

    }
    public void dersskorSil(){    //buna bak

        db.delete("skortablo",null,null);

    }


}
