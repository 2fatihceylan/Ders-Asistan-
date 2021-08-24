package com.fatih.dersasistan;

public class Not {

    private String notid,noticerik,notkonu,nottarih;

    public Not(){

    }

    public Not(String noticerik, String notkonu, String nottarih) {
        this.noticerik = noticerik;
        this.notkonu = notkonu;
        this.nottarih = nottarih;
    }

    public Not(String notid, String noticerik, String notkonu, String nottarih) {
        this.notid = notid;
        this.noticerik = noticerik;
        this.notkonu = notkonu;
        this.nottarih = nottarih;
    }

    public String getNotid() {
        return notid;
    }

    public void setNotid(String notid) {
        this.notid = notid;
    }

    public String getNoticerik() {
        return noticerik;
    }

    public void setNoticerik(String noticerik) {
        this.noticerik = noticerik;
    }

    public String getNotkonu() {
        return notkonu;
    }

    public void setNotkonu(String notkonu) {
        this.notkonu = notkonu;
    }

    public String getNottarih() {
        return nottarih;
    }

    public void setNottarih(String nottarih) {
        this.nottarih = nottarih;
    }
}
