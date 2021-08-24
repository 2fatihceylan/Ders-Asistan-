package com.fatih.dersasistan;

public class DersSure {

    String dersid;
    int derstopsure=0;

    public DersSure(){

    }

    public DersSure(String dersid, int derstopsure) {
        this.dersid = dersid;
        this.derstopsure = derstopsure;
    }

    public String getDersid() {
        return dersid;
    }

    public void setDersid(String dersid) {
        this.dersid = dersid;
    }

    public int getDerstopsure() {
        return derstopsure;
    }

    public void setDerstopsure(int derstopsure) {
        this.derstopsure = derstopsure;
    }
}
