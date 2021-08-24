package com.fatih.dersasistan;

public class Ders {

    private String dersadi,aciklama,bassaat,bitsaat,animsat,tarih,yapildimi,id,renk;

    public Ders(){

    }

    public Ders(String dersadi, String aciklama, String bassaat, String bitsaat, String animsat, String tarih, String yapildimi,String renk) {
        this.dersadi = dersadi;
        this.aciklama = aciklama;
        this.bassaat = bassaat;
        this.bitsaat = bitsaat;
        this.animsat = animsat;
        this.tarih = tarih;
        this.yapildimi = yapildimi;
        this.renk=renk;
    }

    public Ders(String id,String dersadi, String aciklama, String bassaat, String bitsaat, String animsat, String tarih, String yapildimi,String renk) {
        this.dersadi = dersadi;
        this.aciklama = aciklama;
        this.bassaat = bassaat;
        this.bitsaat = bitsaat;
        this.animsat = animsat;
        this.tarih = tarih;
        this.yapildimi = yapildimi;
        this.id=id;
        this.renk=renk;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDersadi() {
        return dersadi;
    }

    public void setDersadi(String dersadi) {
        this.dersadi = dersadi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getBassaat() {
        return bassaat;
    }

    public void setBassaat(String bassaat) {
        this.bassaat = bassaat;
    }

    public String getBitsaat() {
        return bitsaat;
    }

    public void setBitsaat(String bitsaat) {
        this.bitsaat = bitsaat;
    }

    public String getAnimsat() {
        return animsat;
    }

    public void setAnimsat(String animsat) {
        this.animsat = animsat;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getYapildimi() {
        return yapildimi;
    }

    public void setYapildimi(String yapildimi) {
        this.yapildimi = yapildimi;
    }
}
