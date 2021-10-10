package com.adhimbagas.finalprojectskripsi.model.RoboModel;

public class Gejala {

    private int kodeGejala;
    private String namaGejala;
    public Gejala(){
    }

    public Gejala(int kodeGejala, String namaGejala) {
        this.kodeGejala = kodeGejala;
        this.namaGejala = namaGejala;
    }

    public int getKodeGejala() {
        return kodeGejala;
    }

    public void setKodeGejala(int kodeGejala) {
        this.kodeGejala = kodeGejala;
    }

    public String getNamaGejala() {
        return namaGejala;
    }

    public void setNamaGejala(String namaGejala) {
        this.namaGejala = namaGejala;
    }
}
