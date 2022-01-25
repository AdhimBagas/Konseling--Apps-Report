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
                "Merupakan perilaku seksual yang menyinggung, tidak pantas, dan tidak diinginkan. ",
                "1. Menggoda dengan omongan yang sensual yang menyebabkan rasa tersinggung kepada lawan bicara nya \n" +
                        "2. Mengirimkan surat dan panggilan telepon yang tak henti-henti meski sudah ditolak dengan tujuan untuk menggoda. \n" +
                        "3. Mengulangi ajakan seksual secara terus menerus dan tidak diinginkan.",
                "- Dengan menolak setiap ajakan dengan tegas dan berani. \n" +
                        "- Jika terus dilakukan segera hubungi pihak berwajib untuk laporan melakukan hal yang tidak mengenakan. \n" +
                        "- Memberitahukan bahwa apa yang dilakukannya adalah hal yang salah dan tidak baik.",
                R.drawable.perilaku_menggoda);
        insertKerusakan(p1);

        Perilaku p2 = new Perilaku(
                2,
                "Pelanggaran Seksual",
                "Merupakan tindakan pelanggaran seksual berat seperti menyentuh, merasakan, atau meraih secara paksa atau melakukan penyerangan seksual.",
                "1. Melakukan tindakan seksual secara langsung. \n" +
                        "2. Melakukan pelanggaran seksual yang dapat merugikan korban. \n" +
                        "3. Memberikan suatu tindakan yang berakibat melecehkan harga diri korban secara seksual.",
                "- Memberitahu seseorang atas peristiwa yang menimpa anda, jangan menyimpannya untuk diri sendiri. \n" +
                        "- Mencari tahu siapa yang bertanggung jawab untuk menangani pelecehan di daerah atau wilayah tersebut.\n" +
                        "- Jika anda mengalami tekanan psikologis yang parah, anda mungkin dapat berkonsultasi pada psikolog atau terapis profesional akan kesehatan mental anda.",
                R.drawable.pelanggaran_seksual);
        insertKerusakan(p2);

        Perilaku p3 = new Perilaku(
                3,
                "Pelecehan Gender",
                "Merupakan tindakan seksual lewat sentuhan fisik maupun non-fisik dengan sasaran organ seksual atau seksualitas korban. Termasuk juga menggunakan siulan, main mata, ucapan bernuansa seksual, mempertunjukkan materi pornografi dan keinginan seksual, colekan atau sentuhan di bagian tubuh, gerakan atau isyarat yang  bersifat seksual sehingga mengakibatkan rasa tidak nyaman, tersinggung, merasa direndahkan martabatnya, dan mungkin sampai menyebabkan masalah kesehatan dan keselamatan",
                "1. Melakukan sentuhan fisik di area organ seksual atau seksualitas korban. \n2. Melakukan siulan, main mata, ataupun ucapan bernuansa seksual yang mengakibatkan rasa tidak nyaman, tersinggung, dan merasa direndahkan martabatnya. \n3. Sentuhan yang tidak diinginkan. Jika seseorang menyentuh bagian tubuh perempuan, padahal dia tidak mengizinkannya, maka hal tersebut merupakan pelecehan. Situasi ini pun serupa jika terjadi pada pria. \n4. Perilaku yang tidak diinginkan. Jika seseorang memaksamu untuk bersikap intim padahal Kamu tidak menginginkannya, atau jika seseorang menguntit lalu masuk ke ruangan pribadimu dengan tindakan mengancam, maka hal tersebut juga disebut pelecehan seksual. \n5. Pelecehan secara online. Bila seseorang mengirim e-mail, foto, teks, atau konten apa pun yang mengaitkanmu dengan situasi seksual, maka hal tersebut juga disebut pelecehan. Kamu harusnya tidak tinggal diam.",
                "- Berbicara secara terus terang \nJika Anda adalah korban pelecehan, langkah pertama yang harus dilakukan adalah dengan membiarkan pihak yang bersalah tahu bahwa Anda menemukan perilaku mereka sebagai perilaku ofensif. Dalam banyak kasus ini dapat menyelesaikan masalah. Jika hal ini tidak menyelesaikan masalah, setidaknya pelaku tahu bahwa Anda merasa perilakunya sangat mengganggu. \n- Memberitahu Pelaku untuk berhenti \n" +
                        "Dengan memberitahukan pelaku tentang perbuatannya, maka hal ini adalah langkah defense pertama anda terhadap pelaku. Sehingga pelaku akan berpikir 2x lagi untuk melanjutkan perbuatannya. \n" +
                        "- Mendokumentasikan Perilaku Pelecehan \nJika anda sempat dan memiliki kesempatan serta keberanian lebih, maka tidak ada salahnya untuk mendokumentasikan perilaku pelaku untuk dijadikan barang bukti ketika di proses di pengadilan nanti \n" +
                        "- Memproses ke Pengadilan \nJika lembaga pemerintah mengeluarkan surat hak untuk menuntut, Anda dapat membawa gugatan perdata atas cedera yang Anda derita akibat pelecehan seksual. Anda tidak perlu menunjukkan luka fisik. Cedera yang paling umum dalam kasus pelecehan seksual adalah luka secara emosional yang diderita oleh korban.",
                R.drawable.pelecehan_gender);
        insertKerusakan(p3);

        Perilaku p4 = new Perilaku(
                4,
                "Pemaksaan Seksual",
                "Suatu aktivitas pemaksaan seksual atau  perilaku terkait seks lainnya dengan menggunakan ancaman.\n" +
                        "Contohnya seperti evaluasi kerja yang negatif, pencabutan promosi kerja, dan ancaman pembunuhan",
                "1. Melakukan pemaksaan untuk melakukan aktivitas seksual disertai dengan berbagai ancaman. \n" +
                        "2. Memaksa korban untuk memenuhi kemauan pelaku tanpa adanya kesepakatan bersama. \n" +
                        "3. Direndahkan nya martabat seseorang akibat perilaku ini.",
                "- Menolak dengan tegas serta memberikan ancaman akan melaporkan kejadian tersebut ke pihak berwajib.\n" +
                        "- Berlari menyelamatkan diri.\n" +
                        "- Melawan dengan tetap memperhatikan keselamatan diri.",
                R.drawable.pemaksaan_seksual);
        insertKerusakan(p4);

        Perilaku p5 = new Perilaku(
                5,
                "Penyuapan Seksual",
                "Perilaku ini berupa permintaan aktivitas seksual dengan janji imbalan yang dilakukan secara terang-terangan. Misalnya seorang wanita/pria mengajak seorang anak melakukan hubungan intim dengan iming-iming uang, asalkan ia tidak memberitahukannya kepada orang lain.",
                "1. Situasi dimana perempuan mengalami tipu daya, ancaman, maupun kekerasan untuk menjadi pekerja seks. \n" +
                        "2. Biasanya keadaan ini terjadi di masa rekrutmen, dimana korban menginginkan hasil tertentu dan pelaku memanfaatkan hal tersebut untuk melakukan penyuapan seksual. \n" +
                        "3. Mengiming-imingi perkawinan untuk memperoleh layanan seksual dari perempuan tersebut dengan imbalan baik uang, jabatan,ataupun kepastian pernikahan dengan pemaksaan ataupun dapat menyebabkan rasa tidak nyaman dan terlecehkan dari korban.",
                "- Jika kamu sedang berada didalam ruangan ataupun sedang interview untuk suatu pekerjaan, dan tiba-tiba ada tawaran mengenai penyuapan seksual dan kamu tidak menerima kejadian tersebut, kamu bisa langsung keluar ruangan dan meninggalkan apa hal yang sedang kamu perjuangkan selama ini. Yakinlah masih banyak kesempatan lain diluar sana. \n" +
                        "- Jangan takut untuk melaporkan kejadian yang kamu alami. Laporkan kejadian tersebut ke lembaga yang menaungi kasus kekerasan dan pelecehan seksual. \n" +
                        "- Tuliskan kejadian yang kamu alami di media sosial untuk di viralkan. Namun tetap memperhatikan kaidah tak bersalah dari sudut pandang pelaku.",
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
