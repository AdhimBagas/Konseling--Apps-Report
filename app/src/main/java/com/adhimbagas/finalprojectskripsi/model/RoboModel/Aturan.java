package com.adhimbagas.finalprojectskripsi.model.RoboModel;

public class Aturan {

    private Gejala gejalaAturan;
    private int aturanKodePerilaku;
    private int aturanKodeGejala;
    private int level;

    public Aturan() {
    }

    public Aturan(int aturanKodePerilaku, int aturanKodeGejala, int level) {
        this.aturanKodePerilaku = aturanKodePerilaku;
        this.aturanKodeGejala = aturanKodeGejala;
        this.level = level;
    }

    public Gejala getGejalaAturan() {
        return gejalaAturan;
    }

    public void setGejalaAturan(Gejala gejalaAturan) {
        this.gejalaAturan = gejalaAturan;
    }

    public int getAturanKodePerilaku() {
        return aturanKodePerilaku;
    }

    public void setAturanKodePerilaku(int aturanKodePerilaku) {
        this.aturanKodePerilaku = aturanKodePerilaku;
    }

    public int getAturanKodeGejala() {
        return aturanKodeGejala;
    }

    public void setAturanKodeGejala(int aturanKodeGejala) {
        this.aturanKodeGejala = aturanKodeGejala;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
