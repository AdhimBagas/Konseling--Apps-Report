package com.adhimbagas.finalprojectskripsi.model.RoboModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.adhimbagas.finalprojectskripsi.R;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.PakarContract.GejalaTable;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.PakarContract.AturanTabel;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.PakarContract.PerilakuTable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SispakDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pakar.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TEMP_PERILAKU = "temp_perilaku";

    private SQLiteDatabase db;
    private static SispakDBHelper instance;

    private SispakDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized SispakDBHelper getInstance(Context context){
        if(instance == null){
            instance = new SispakDBHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_GEJALA_TABLE = "CREATE TABLE " +
                GejalaTable.TABLE_NAME + "( " +
                GejalaTable.COLUMN_CODE + " INTEGER NOT NULL UNIQUE, " +
                GejalaTable.COLUMN_NAME + " TEXT " + ")";

        final String SQL_CREATE_PERILAKU_TABLE = "CREATE TABLE " +
                PerilakuTable.TABLE_NAME + "( " +
                PerilakuTable.COLUMN_CODE + " INTEGER NOT NULL UNIQUE, " +
                PerilakuTable.COLUMN_NAME + " TEXT, " +
                PerilakuTable.COLUMN_DESKRIPSI + " TEXT, " +
                PerilakuTable.COLUMN_GEJALA_PERILAKU + " TEXT, " +
                PerilakuTable.COLUMN_SOLUSI + " TEXT, " +
                PerilakuTable.COLUMN_IMAGE_URL + " INTEGER NOT NULL " +
                ")";

        final String SQL_CREATE_ATURAN_TABLE = "CREATE TABLE " +
                AturanTabel.TABLE_NAME + "( " +
                AturanTabel.COLUMN_PERILAKU_CODE + " INTEGER, " +
                AturanTabel.COLUMN_GEJALA_CODE + " INTEGER, " +
                AturanTabel.COLUMN_LEVEL + " INTEGER " +
                ")";

        final String SQL_CREATE_TEMP_PERILAKU_TABLE = "CREATE TABLE " +
                TEMP_PERILAKU + "( " +
                PerilakuTable.COLUMN_CODE + " INTEGER NOT NULL " +
                ")";

        db.execSQL(SQL_CREATE_GEJALA_TABLE);
        db.execSQL(SQL_CREATE_PERILAKU_TABLE);
        db.execSQL(SQL_CREATE_ATURAN_TABLE);
        db.execSQL(SQL_CREATE_TEMP_PERILAKU_TABLE);


        fillPerilakuTable();
        fillGejalaTable();
        fillAturanTable();
    }

    private void insertKerusakan(Perilaku perilaku){
        ContentValues cv = new ContentValues();
        cv.put(PerilakuTable.COLUMN_CODE, perilaku.getKodePerilaku());
        cv.put(PerilakuTable.COLUMN_NAME, perilaku.getNamaPerilaku());
        cv.put(PerilakuTable.COLUMN_DESKRIPSI, perilaku.getDeskripsi());
        cv.put(PerilakuTable.COLUMN_GEJALA_PERILAKU, perilaku.getGejalaPerilaku());
        cv.put(PerilakuTable.COLUMN_SOLUSI, perilaku.getSolusi());
        cv.put(PerilakuTable.COLUMN_IMAGE_URL, perilaku.getImagePerilaku());
        db.insert(PerilakuTable.TABLE_NAME, null, cv);
    }
    private void fillPerilakuTable() {
        Perilaku p1 = new Perilaku(
                1,
                "Perilaku Menggoda",
                "Kerusakan pada prosesor bisa disebabkan karena prosesor mengalami panas yang berlebihan atau sering juga disebut overheating, sehingga dapat menyebabkan IC nya terbakar dan kemudian prosesor tidak dapat digunakan lagi. Kerusakan ini juga bisa terjadi karena banyak debu yang menempel pada kipas prosesor yang menyebabkan kinerja dari kipas pendingin prosesor terganggu",
                "1. Suhu prosesor cepat panas. \n2. Komputer sering restart sendiri. \n3. Komputer menyala, tapi tidak tampil di monitor. \n4. Muncul pesan error pada BIOS. \n5. Gagal menjalankan software atau aplikasi.",
                "- Bersihkan kipas prosesor dari debu-debu yang menempel agar kinerja kipas prosesor kembali normal \n\n- Gunakan kipas tambahan untuk membantu menurunkan suhu prosesor \n\n- Berikan Thermal Paste pada permukaan prosesor, karena Thermal Paste berguna menyerap suhu panas yang dihasilkan prosesor \n\n- Jangan paksakan komputer untuk menjalankan aplikasi yang cukup berat \n\n- Atur sirkulasi udara dalam ruangan agar tetap sejuk karena secara tidak langsung mempengaruhi suhu dari prosesor dan komputer.",
                R.drawable.perilaku_menggoda);
        insertKerusakan(p1);

        Perilaku p2 = new Perilaku(
                2,
                "Pelanggaran Seksual",
                "Kerusakan motherboard bisa terjadi karena terjadi kerusakan chipset yang menyebabkan pembacaan perangkat keras yang terhubung menjadi lambat, bahkan terjadi kegagalan dalam hubungan (interkoneksi) antar komponen dalam motherboard dan akibatnya komputer stuck pada tampilan tertentu. Kerusakan motherboard juga bisa terjadi karena adanya tegangan listrik dan juga suplai listrik yang tidak stabil yang bisa menyebabkan motherboard terbakar.",
                "1. Tampilan layar komputer stuck di boot screen. \n2. Komputer sulit untuk dihidupkan. \n3. Komputer mati total. \n4. Lampu indikator pada monitor berkedip-kedip.",
                "- Jauhkan kompenen motherboard dari lokasi berdebu yang bisa menghambat proses pendinginan \n\n- Jangan menjatuhkan motherboard dengan keras \n\n- Selalu periksa apakah setiap komponen sudah tersambung dengan baik pada socketnya \n\n- Gunakan volt stabilizer untuk menstabilkan daya listrik dan juga tegangan listrik agar tidak kurang atau kelebihan.",
                R.drawable.pelanggaran_seksual);
        insertKerusakan(p2);

        Perilaku p3 = new Perilaku(
                3,
                "Pelecehan Gender",
                "Kerusakan memori RAM bisa disebabkan karena pemakaian yang berlebihan, seperti RAM dengan kapasitas hanya 1 GB dipaksa untuk menjalankan aplikasi yang membutuhkan kapasitas RAM yang besar. Ini bisa menyebabkan RAM dapat mengalami malfungsi dan mengalami kerusakan. Hal lainnya yang dapat menyebabkan kerusakan memori RAM adalah slot RAM tertutup debu atau lembab karena air.",
                "1. Komputer terasa lambat atau berat. \n2. Komputer sering restart sendiri. \n3. Terjadi blue screen pada monitor. \n4. Gagal menjalankan software atau aplikasi. \n5. Muncul bunyi beep beberapa kali.",
                "- Upgrade kapasitas RAM sehingga tidak membebani kerja RAM terlalu berat \n\n- Bersihkan slot RAM dari debu-debu yang menempel sampai bersih \n\n- Bersihkan bagian pin RAM menggunakan penghapus karet dengan cara mengosok satu arah pada bagian tembaga/kuningan pada RAM \n\n- Membeli RAM dalam kondisi baru dari merek yang sudah terpercaya.",
                R.drawable.pelecehan_gender);
        insertKerusakan(p3);

        Perilaku p4 = new Perilaku(
                4,
                "Pemaksaan Seksual",
                "Kerusakan pada hardisk bisa terjadi karena tidak sengaja terjatuh atau terbentur karena hardisk merupakan komponen hardware yang berisikan disk/cakram. Benda tersebut memiliki sifat sensitif jika terjatuh atau terbentur karena berpotensi mengalami kerusakan. Instalasi software atau sistem operasi yang berlebihan juga berpotensi mendatangkan kerusakan hardisk. Karena hardisk akan bekerja ekstra bahkan cakram akan berputar cepat saat melakukan instalasi secara berlebihan.",
                "1. Terdengar suara berisik pada bagian Harddisk. \n2. Data file mengalami kerusakan atau hilang. \n3. Terjadi blue screen pada monitor \n4. Selalu muncul scandisk saat booting.",
                "- Melakukan Disk Defragment untuk mengatur seluruh file dan data pada hardisk menjadi berurutan, mengurangi kinerja perputaran disk/cakram didalam hardisk agar lebih awet \n\n- Menggunakan software bernama Software Recovery yang bisa mengembalikan seluruh data pada hardisk yang sebelumnya hilang, terhapus atau terformat \n\n- Lakukan pengecekan ulang pada posisi hardisk. Lalu betulkan hardisk pada posisi yang benar. \n\n- Ganti dengan hardisk yang baru apabila hardisk sudah cukup lama digunakan.",
                R.drawable.pemaksaan_seksual);
        insertKerusakan(p4);

        Perilaku p5 = new Perilaku(
                5,
                "Penyuapan Seksual",
                "Kerusakan pada hardisk bisa terjadi karena tidak sengaja terjatuh atau terbentur karena hardisk merupakan komponen hardware yang berisikan disk/cakram. Benda tersebut memiliki sifat sensitif jika terjatuh atau terbentur karena berpotensi mengalami kerusakan. Instalasi software atau sistem operasi yang berlebihan juga berpotensi mendatangkan kerusakan hardisk. Karena hardisk akan bekerja ekstra bahkan cakram akan berputar cepat saat melakukan instalasi secara berlebihan.",
                "1. Terdengar suara berisik pada bagian Harddisk. \n2. Data file mengalami kerusakan atau hilang. \n3. Terjadi blue screen pada monitor \n4. Selalu muncul scandisk saat booting.",
                "- Melakukan Disk Defragment untuk mengatur seluruh file dan data pada hardisk menjadi berurutan, mengurangi kinerja perputaran disk/cakram didalam hardisk agar lebih awet \n\n- Menggunakan software bernama Software Recovery yang bisa mengembalikan seluruh data pada hardisk yang sebelumnya hilang, terhapus atau terformat \n\n- Lakukan pengecekan ulang pada posisi hardisk. Lalu betulkan hardisk pada posisi yang benar. \n\n- Ganti dengan hardisk yang baru apabila hardisk sudah cukup lama digunakan.",
                R.drawable.penyuapan_seksual);
        insertKerusakan(p5);
    }

    //Setup Gejala Table
    private void insertGejala(Gejala gejala){
        ContentValues cv = new ContentValues();
        cv.put(GejalaTable.COLUMN_CODE, gejala.getKodeGejala());
        cv.put(GejalaTable.COLUMN_NAME, gejala.getNamaGejala());
        db.insert(GejalaTable.TABLE_NAME, null, cv);
    }
    private void fillGejalaTable() {
        //Perilaku Menggoda
        insertGejala(new Gejala(1, "Mengajak melakukan hal yang tidak pantas"));
        insertGejala(new Gejala(2, "menggoda dengan kata yang mengarah seksual sehingga membuat anda resah dan tidak nyaman"));
        insertGejala(new Gejala(3, "memaksa anda untuk melakukan hal yang tidak disukai yang bersifat seksual"));

        //Pelanggaran Seksual
        insertGejala(new Gejala(4, "Melakukan sentuhan di area seksual sehingga menyebabkan rasa tidak nyaman, tersinggung, dan merasa direndahkan"));
        insertGejala(new Gejala(5, "Menyerang secara seksual dengan paksa"));
        insertGejala(new Gejala(6, "Merasakan area seksual anda tanpa sepengetahuan anda"));

        //Pelecehan Gender
        insertGejala(new Gejala(7, "Menghina atau merendahkan sesorang karena jenis kelamin"));
        insertGejala(new Gejala(8, "Melakukan lelucon atau candaan cabul tentang seks yang membuat anda merasa dilecehkan atau direndahkan"));
        insertGejala(new Gejala(9, "Mengirimkan teks, tulisan, atau gambar yang melecehkan dan merendahkan anda atau seseorang"));

        //Pemaksaan Seksual
        insertGejala(new Gejala(10, "Memaksa melakukan sesuatu terkait seks yang mana jika tidak diberikan maka mendapat ancaman hukuman"));
        insertGejala(new Gejala(11, "Mengancam untuk melakukan kegiatan terkait seks yang menyebabkan menjadi ancaman terhadap keselamatan diri ataupun keluarga dan orang terdekat"));
        insertGejala(new Gejala(12, "mengancam untuk melakukan kegiatan terkait seks dengan ancaman penilaian buruk terhadap karir yang sedang dijalankan"));

        //Penyuapan Seksual
        insertGejala(new Gejala(13, "Melakukan permintaan aktivitas seksual dengan janji imbalan yang dilakukan secara terang-terangan"));
        insertGejala(new Gejala(14, "Melakukan permintaan aktivitas seksual dengan imbalan berupa uang atau barang yang mana anda merasa keberatan dengan permintaan tersebut"));
        insertGejala(new Gejala(15, "Melakukan tindakan Seksual karena menganggap anda dapat ditukar dengan uang atau imbalan dalam bentuk lain"));
    }

    //SETUP ATURAN
    private void insertAturan(Aturan aturan){
        ContentValues cv = new ContentValues();
        cv.put(AturanTabel.COLUMN_PERILAKU_CODE, aturan.getAturanKodePerilaku());
        cv.put(AturanTabel.COLUMN_GEJALA_CODE, aturan.getAturanKodeGejala());
        cv.put(AturanTabel.COLUMN_LEVEL, aturan.getLevel());
        db.insert(AturanTabel.TABLE_NAME, null, cv);
    }
    private void fillAturanTable() {

        //Aturan Perilaku Menggoda
        insertAturan(new Aturan(1,1,1));
        insertAturan(new Aturan(1,2,2));
        insertAturan(new Aturan(1,3,3));

        //Pelanggaran Seksual
        insertAturan(new Aturan(2,4,1));
        insertAturan(new Aturan(2,5,2));
        insertAturan(new Aturan(2,6,3));

        //Pelecehan Gender
        insertAturan(new Aturan(3,7,1));
        insertAturan(new Aturan(3,8,2));   // Swap
        insertAturan(new Aturan(3,9,3));

        //Pemaksaan Seksual
        insertAturan(new Aturan(4,10,1));
        insertAturan(new Aturan(4,11,2));
        insertAturan(new Aturan(4,12,3));

        //Penyuapan Seksual
        insertAturan(new Aturan(5,13,1));
        insertAturan(new Aturan(5,14,2));
        insertAturan(new Aturan(5,15,3));
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + GejalaTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PerilakuTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AturanTabel.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TEMP_PERILAKU);
        onCreate(db);
    }

    public Gejala getGejalaWhereCode (int kodeGejala){
        Gejala gejala = new Gejala();
        db = getReadableDatabase();

        String[] selectionArgs = {String.valueOf(kodeGejala)};
        Cursor c = db.rawQuery("SELECT * FROM " + GejalaTable.TABLE_NAME +
                        " WHERE " + GejalaTable.COLUMN_CODE + " = ?",
                selectionArgs);

        if(c.moveToFirst()){
            gejala.setKodeGejala(c.getInt(c.getColumnIndex(GejalaTable.COLUMN_CODE)));
            gejala.setNamaGejala(c.getString(c.getColumnIndex(GejalaTable.COLUMN_NAME)));
        }
        c.close();
        return gejala;
    }

    public Perilaku getPerilakuWhereCode (int kodePerilaku){
        Perilaku perilaku = new Perilaku();
        db = getReadableDatabase();

        String[] selectionArgs = {String.valueOf(kodePerilaku)};
        Cursor c = db.rawQuery("SELECT * FROM " + PerilakuTable.TABLE_NAME +
                " WHERE " + PerilakuTable.COLUMN_CODE +
                " = ?", selectionArgs);

        if(c.moveToFirst()){
            perilaku.setKodePerilaku(c.getInt(c.getColumnIndex(PerilakuTable.COLUMN_CODE)));
            perilaku.setNamaPerilaku(c.getString(c.getColumnIndex(PerilakuTable.COLUMN_NAME)));
            perilaku.setDeskripsi(c.getString(c.getColumnIndex(PerilakuTable.COLUMN_DESKRIPSI)));
            perilaku.setGejalaPerilaku(c.getString(c.getColumnIndex(PerilakuTable.COLUMN_GEJALA_PERILAKU)));
            perilaku.setSolusi(c.getString(c.getColumnIndex(PerilakuTable.COLUMN_SOLUSI)));
            perilaku.setImagePerilaku(c.getInt(c.getColumnIndex(PerilakuTable.COLUMN_IMAGE_URL)));
        }

        c.close();
        return perilaku;
    }

    public ArrayList<Perilaku> getListPerilaku(){
        ArrayList<Perilaku> perilakuList = new ArrayList<>();
        db = getReadableDatabase();

        String[] columns = {PerilakuTable.COLUMN_CODE, PerilakuTable.COLUMN_NAME, PerilakuTable.COLUMN_IMAGE_URL};

        Cursor c = db.query(
                PerilakuTable.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                PerilakuTable.COLUMN_CODE + " ASC"
        );

        if(c.moveToFirst()){
            do{
                Perilaku perilaku = new Perilaku();
                perilaku.setKodePerilaku(c.getInt(c.getColumnIndex(PerilakuTable.COLUMN_CODE)));
                perilaku.setNamaPerilaku(c.getString(c.getColumnIndex(PerilakuTable.COLUMN_NAME)));
                perilaku.setImagePerilaku(c.getInt(c.getColumnIndex(PerilakuTable.COLUMN_IMAGE_URL)));
                perilakuList.add(perilaku);
            } while (c.moveToNext());

        }
        c.close();
        return perilakuList;
    }

    
    public ArrayList<Aturan> getAturanWhereLevel (int level){
        ArrayList<Aturan> aturanList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = AturanTabel.COLUMN_LEVEL + "=?";
        String [] selectionArgs = {String.valueOf(level)};

        Cursor c = db.query(
                AturanTabel.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                AturanTabel.COLUMN_GEJALA_CODE + " ASC"
        );

        if(c.moveToFirst()){
            do {
                Aturan aturan = new Aturan();
                aturan.setAturanKodeGejala(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_GEJALA_CODE)));
                aturan.setAturanKodePerilaku(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_PERILAKU_CODE)));
                aturan.setLevel(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_LEVEL)));
                aturan.setGejalaAturan(this.getGejalaWhereCode(aturan.getAturanKodeGejala()));
                aturanList.add(aturan);
            }while (c.moveToNext());
        }

        c.close();
        return aturanList;
    }

    public ArrayList<Aturan> newAturan(int level){
        ArrayList<Aturan> aturanList = new ArrayList<>();
        db = getReadableDatabase();


        String selection = AturanTabel.COLUMN_LEVEL + " = ?" +
                " AND " + AturanTabel.COLUMN_PERILAKU_CODE + " IN (" +
                "SELECT " + PerilakuTable.COLUMN_CODE + " FROM " + TEMP_PERILAKU + ")";
        String[] selectionArgs = new String[]{String.valueOf(level)};

        Cursor c = db.query(
                AturanTabel.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                AturanTabel.COLUMN_GEJALA_CODE,
                null,
                AturanTabel.COLUMN_GEJALA_CODE + " ASC"
        );

        if(c.moveToFirst()){
            do{
                Aturan aturan = new Aturan();
                aturan.setAturanKodeGejala(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_GEJALA_CODE)));
                aturan.setAturanKodePerilaku(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_PERILAKU_CODE)));
                aturan.setLevel(c.getInt(c.getColumnIndex(AturanTabel.COLUMN_LEVEL)));
                aturan.setGejalaAturan(this.getGejalaWhereCode(aturan.getAturanKodeGejala()));
                aturanList.add(aturan);
            }while (c.moveToNext());
        }
        c.close();
        return aturanList;
    }

    public void setCandidate (int kodeGejala, int level){
        String statement;
        if (level == 1){
            db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TEMP_PERILAKU);

            statement = "INSERT INTO " + TEMP_PERILAKU + "(" + PerilakuTable.COLUMN_CODE + ") " +
                    "SELECT " + AturanTabel.COLUMN_PERILAKU_CODE + " FROM " + AturanTabel.TABLE_NAME + " WHERE " +
                    AturanTabel.COLUMN_GEJALA_CODE + " = " + kodeGejala + " AND " +
                    AturanTabel.COLUMN_LEVEL + " = " + level;

            db = getWritableDatabase();
            db.execSQL(statement);
        } else {
            statement = "SELECT " + AturanTabel.COLUMN_PERILAKU_CODE + " FROM " + AturanTabel.TABLE_NAME + " WHERE " +
                    AturanTabel.COLUMN_GEJALA_CODE + " = " + kodeGejala + " AND " +
                    AturanTabel.COLUMN_LEVEL + " = " + level;
            statement = "DELETE FROM " + TEMP_PERILAKU + " WHERE " +
                    PerilakuTable.COLUMN_CODE + " NOT IN " +
                    "(" + statement + ")";

            db = getWritableDatabase();
            db.execSQL(statement);
        }
    }

}
