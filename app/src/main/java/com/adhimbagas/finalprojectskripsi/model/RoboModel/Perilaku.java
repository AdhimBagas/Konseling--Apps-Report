package com.adhimbagas.finalprojectskripsi.model.RoboModel;

public class Perilaku {

    private int kodePerilaku;
    private String namaPerilaku;
    private String deskripsi;
    private String gejalaPerilaku;
    private String solusi;
    private int imagePerilaku;

    public Perilaku(){

    }

    public Perilaku(int kodePerilaku, String namaPerilaku, String deskripsi, String gejalaPerilaku, String solusi, int imagePerilaku) {
        this.kodePerilaku = kodePerilaku;
        this.namaPerilaku = namaPerilaku;
        this.deskripsi = deskripsi;
        this.gejalaPerilaku = gejalaPerilaku;
        this.solusi = solusi;
        this.imagePerilaku = imagePerilaku;
    }

    public int getKodePerilaku() {
        return kodePerilaku;
    }

    public void setKodePerilaku(int kodePerilaku) {
        this.kodePerilaku = kodePerilaku;
    }

    public String getNamaPerilaku() {
        return namaPerilaku;
    }

    public void setNamaPerilaku(String namaPerilaku) {
        this.namaPerilaku = namaPerilaku;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGejalaPerilaku() {
        return gejalaPerilaku;
    }

    public void setGejalaPerilaku(String gejalaPerilaku) {
        this.gejalaPerilaku = gejalaPerilaku;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
    }

    public int getImagePerilaku() {
        return imagePerilaku;
    }

    public void setImagePerilaku(int imagePerilaku) {
        this.imagePerilaku = imagePerilaku;
    }
}
