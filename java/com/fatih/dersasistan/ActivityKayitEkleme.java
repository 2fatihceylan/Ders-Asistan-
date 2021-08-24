package com.fatih.dersasistan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ActivityKayitEkleme extends AppCompatActivity {


    veriKaynagi vk;
    private Button buttontarihsec,buttonbassaat,buttonbitsaat,buttonrenksec;
    EditText editTextdersadi,editTextaciklama;

    TextView tvtarih,tvbassaat,tvbitsaat;
    RadioButton rdbildirim,rdhatirlatma;
    RadioGroup radioanimsatici;
    ImageView back,delete,save;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private int year,month,dayofmonth;

    String tarih,bassaat,bitsaat,animsat;

    boolean dersvarmi=false;



    Ders d;

    String renk="white";
    int mDefaultColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ekleme);

        buttontarihsec=findViewById(R.id.buttontarihsec);
        buttonbassaat=findViewById(R.id.buttonbassaat);
        buttonbitsaat=findViewById(R.id.buttonbitsaat);
        buttonrenksec=findViewById(R.id.buttonrenksec);
        editTextdersadi=findViewById(R.id.editdersadi);
        editTextaciklama=findViewById(R.id.editaciklama);
        tvtarih=findViewById(R.id.tvtarih);
        tvbassaat=findViewById(R.id.tvbassaat);
        tvbitsaat=findViewById(R.id.tvbitsaat);

        rdbildirim=findViewById(R.id.rdbildirim);
        rdhatirlatma=findViewById(R.id.rdhatirlatma);
        back=findViewById(R.id.imageviewback);
        delete=findViewById(R.id.imageviewdelete);
        save=findViewById(R.id.imageviewsave);

        radioanimsatici=findViewById(R.id.radioanimsatici);






        vk=new veriKaynagi(this);
        vk.ac();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();




        if (bundle!=null){
            int position=bundle.getInt("position");
            String id=bundle.getString("dersid");
            //Toast.makeText(this, String.valueOf(position),Toast.LENGTH_LONG).show();


            if (id==null){
                ArrayList<Ders> list= (ArrayList<Ders>) vk.listele();

                d=list.get(position);
                //dersid=d.getId();

                editTextdersadi.setText(d.getDersadi());
                editTextaciklama.setText(d.getAciklama());
                tvtarih.setText(d.getTarih());

                tvbassaat.setText(d.getBassaat());
                tvbitsaat.setText(d.getBitsaat());


                if(d.getAnimsat().equals("bildirim")){
                    rdbildirim.setChecked(true);
                    animsat="bildirim";
                }

                else if(d.getAnimsat().equals("hatirlatma")){
                    rdhatirlatma.setChecked(true);
                    animsat="hatirlatma";
                }
                else{

                }
                dersvarmi=true;
            }
            else {

                d=vk.getDers(Integer.parseInt(id));

                editTextdersadi.setText(d.getDersadi());
                editTextaciklama.setText(d.getAciklama());
                tvtarih.setText(d.getTarih());

                tvbassaat.setText(d.getBassaat());
                tvbitsaat.setText(d.getBitsaat());


                if(d.getAnimsat().equals("bildirim")){
                    rdbildirim.setChecked(true);
                    animsat="bildirim";
                }

                else if(d.getAnimsat().equals("hatirlatma")){
                    rdhatirlatma.setChecked(true);
                    animsat="hatirlatma";
                }
                else{

                }
                dersvarmi=true;


            }



        }




        rdbildirim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    animsat="bildirim";
                }
            }
        });
        rdhatirlatma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    animsat="hatirlatma";
                }
            }
        });




        buttontarihsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(ActivityKayitEkleme.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
                                Date date=new Date((year-1900),month,day);


                               tarih=String.valueOf(formatter.format(date));
                               tvtarih.setText(tarih);

                            }
                        }, year, month, dayofmonth);
                datePickerDialog.show();

            }

        });
        buttonbassaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //timepicker

                final Calendar takvim = Calendar.getInstance();
                int saat = takvim.get(Calendar.HOUR_OF_DAY);
                int dakika = takvim.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(ActivityKayitEkleme.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                                SimpleDateFormat formatter=new SimpleDateFormat("HH:mm");
                                Date time=new Date();
                                time.setHours(hourOfDay);
                                time.setMinutes(minute);





                                bassaat=String.valueOf(formatter.format(time));



                                tvbassaat.setText(bassaat);
                            }
                        }, saat, dakika, true);

                tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Seç", tpd);
                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "İptal", tpd);
                tpd.show();

            }
        });

        buttonbitsaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //timepicker


                final Calendar takvim = Calendar.getInstance();
                int saat = takvim.get(Calendar.HOUR_OF_DAY);
                int dakika = takvim.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(ActivityKayitEkleme.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                SimpleDateFormat formatter=new SimpleDateFormat("HH:mm");
                                Date time=new Date();
                                time.setHours(hourOfDay);
                                time.setMinutes(minute);


                                bitsaat=String.valueOf(formatter.format(time));

                                tvbitsaat.setText(bitsaat);
                            }
                        }, saat, dakika, true);

                tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Seç", tpd);
                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "İptal", tpd);
                tpd.show();

            }
        });

        mDefaultColor= ContextCompat.getColor(ActivityKayitEkleme.this,R.color.colorOfapp);
        renk=String.valueOf(mDefaultColor);

        buttonrenksec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AmbilWarnaDialog colorPicker=new AmbilWarnaDialog(ActivityKayitEkleme.this, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {

                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        mDefaultColor=color;
                        renk=String.valueOf(mDefaultColor);

                    }
                });
                colorPicker.show();
            }
        });





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ders ders=new Ders(editTextdersadi.getText().toString(),editTextaciklama.getText().toString(),tvbassaat.getText().toString(),tvbitsaat.getText().toString(),animsat,tvtarih.getText().toString(),"false",renk);



                if (!editTextdersadi.getText().toString().equals("")&&
                !editTextaciklama.getText().toString().equals("")&&
                tvbassaat.getText().toString()!=""&&
                tvbitsaat.getText().toString()!=""&&
                animsat!=null&&
                tvtarih.getText().toString()!=""){


                    if (dersvarmi){
                        ders.setId(d.getId());
                        Toast.makeText(ActivityKayitEkleme.this,"ders var",Toast.LENGTH_LONG).show();
                        vk.dersGuncelle(ders);
                    }
                    else {

                        vk.dersOlustur(ders);
                    }

                    //////////////////////////////////////////////////////////////////////////////////////////////






                    if (rdbildirim.isChecked()){

                        Toast.makeText(ActivityKayitEkleme.this,"bildirim",Toast.LENGTH_LONG).show();


                        Calendar calander=Calendar.getInstance();

                        calander.clear();


                        try {

                            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(ders.getTarih());
                            Date date2=new SimpleDateFormat("HH:mm").parse(ders.getBassaat());


                            String dateArray[]=ders.getTarih().split("/");
                            String timeArray[]=ders.getBassaat().split(":");


                            calander.set(Integer.parseInt(dateArray[2]),Integer.parseInt(dateArray[1])-1,Integer.parseInt(dateArray[0]),Integer.parseInt(timeArray[0]),Integer.parseInt(timeArray[1]),0);



                            AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);

                            Intent intentalarm=new Intent(ActivityKayitEkleme.this,NotificationManager.class);

                            intentalarm.putExtra("dersadi",ders.getDersadi().toString());
                            intentalarm.putExtra("dersaciklama",ders.getAciklama());


                            SharedPreferences sp=getSharedPreferences("Bildirim",MODE_PRIVATE);
                            SharedPreferences.Editor e=sp.edit();

                            int id=sp.getInt("id",0)+1;
                            e.putInt("id",id);

                            e.commit();


                            PendingIntent pendingIntent=PendingIntent.getBroadcast(ActivityKayitEkleme.this,id,intentalarm,0);

                            alarmManager.set(AlarmManager.RTC_WAKEUP,calander.getTimeInMillis(),pendingIntent);


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }




                    }

                    else if (ders.getAnimsat().equals("hatirlatma")){


                    }








                    ///////////////////////////////////////////////////////////////////////////////////////////////

                    Intent intentgeri=new Intent(ActivityKayitEkleme.this,MainActivity.class);
                    startActivity(intentgeri);
                    finish();


                }
                else {
                    Toast.makeText(ActivityKayitEkleme.this,"Boş Bırakılan Alan Var ",Toast.LENGTH_SHORT).show();
                }



            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vk.dersSil(d);

                Intent intentgeri=new Intent(ActivityKayitEkleme.this,MainActivity.class);
                startActivity(intentgeri);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentgeri=new Intent(ActivityKayitEkleme.this,MainActivity.class);
                startActivity(intentgeri);
                finish();
            }
        });



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