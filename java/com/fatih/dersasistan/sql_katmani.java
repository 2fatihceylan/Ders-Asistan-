package com.fatih.dersasistan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sql_katmani extends SQLiteOpenHelper {

    public sql_katmani(Context c){
        super(c,"program",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table programtablo (id integer primary key autoincrement,dersadi text,aciklama text,bassaat text,bitsaat text,animsat text,tarih text,yapildimi text,renk text)";

        String sql2="create table skortablo (skorid integer primary key autoincrement,dersid integer,tarih text,aciklama text,sure text)";

        String sql3="create table notlar (notid integer primary key autoincrement,noticerik text, notkonu text,tarih text)";

        sqLiteDatabase.execSQL(sql);

        sqLiteDatabase.execSQL(sql2);

        sqLiteDatabase.execSQL(sql3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {



        sqLiteDatabase.execSQL("drop table if exists programtablo");
        sqLiteDatabase.execSQL("drop table if exists skortablo");
        sqLiteDatabase.execSQL("drop table if exists notlar");

    }
}
