package com.adhimbagas.finalprojectskripsi.model.RoboModel;

public class PakarContract {
    private PakarContract(){

    }


    public static class GejalaTable {
        public static final String TABLE_NAME = "tabel_gejala";
        public static final String COLUMN_CODE = "kode_gejala";
        public static final String COLUMN_NAME = "nama_gejala";
    }

    public static class PerilakuTable {
        public static final String TABLE_NAME = "tabel_perilaku";
        public static final String COLUMN_CODE = "kode_perilaku";
        public static final String COLUMN_NAME = "nama_perilaku";
        public static final String COLUMN_DESKRIPSI = "deskripsi";
        public static final String COLUMN_GEJALA_PERILAKU = "gejala_perilaku";
        public static final String COLUMN_SOLUSI = "solusi";
        public static final String COLUMN_IMAGE_URL = "image_perilaku";
    }

    public static class AturanTabel{
        public static final String TABLE_NAME = "tabel_aturan";
        public static final String COLUMN_PERILAKU_CODE = "aturan_kode_perilaku";
        public static final String COLUMN_GEJALA_CODE = "aturan_kode_gejala";
        public static final String COLUMN_LEVEL = "level";
    }
}
