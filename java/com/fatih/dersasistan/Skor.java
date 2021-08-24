package com.fatih.dersasistan;

public class Skor {

    private int skorid,dersid;
    private String tarih,aciklama,sure;

    public Skor(){

    }

    public Skor(int dersid, String tarih, String aciklama, String sure) {
        this.dersid = dersid;
        this.tarih = tarih;
        this.aciklama = aciklama;
        this.sure = sure;
    }

    public Skor(int skorid, int dersid, String tarih, String aciklama, String sure) {
        this.skorid = skorid;
        this.dersid = dersid;
        this.tarih = tarih;
        this.aciklama = aciklama;
        this.sure = sure;
    }

    public int getSkorid() {
        return skorid;
    }

    public void setSkorid(int skorid) {
        this.skorid = skorid;
    }

    public int getDersid() {
        return dersid;
    }

    public void setDersid(int dersid) {
        this.dersid = dersid;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getSure() {
        return sure;
    }

    public void setSure(String sure) {
        this.sure = sure;
    }
}
