package com.adhimbagas.finalprojectskripsi.ui.activity.activityRobo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.adhimbagas.finalprojectskripsi.R;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.Aturan;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.Perilaku;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.SispakDBHelper;

import java.util.ArrayList;

public class PertanyaanGejala extends AppCompatActivity implements DialogHasilDiagnosa.OnFragmentInteraction{

    private TextView tvKodeGejala;
    private TextView  tvPertanyaan;

    private SispakDBHelper db;
    private Aturan lastAturan;

    private ArrayList<Aturan> currentAturan;
    private int currentLevel;
    private int currentIndeksAturan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan_gejala);

        tvKodeGejala = findViewById(R.id.tv_kode_soal);
        tvPertanyaan = findViewById(R.id.tv_pertanyaan);
//        imageViewGejala = findViewById(R.id.imgPertanyaan);
        Button btnYes = findViewById(R.id.btn_yes);
        Button btnNo = findViewById(R.id.btn_no);


        currentIndeksAturan = 0;
        currentLevel = 1;

        db = SispakDBHelper.getInstance(this);
        currentAturan = db.getAturanWhereLevel(currentLevel);
        loadQuestion() ;

        btnYes.setOnClickListener(view -> jawabYa());
        btnNo.setOnClickListener(view -> jawabNo());
    }


    private void jawabNo() {
        if(currentIndeksAturan != currentAturan.size() - 1){
            ++currentIndeksAturan;
            loadQuestion();
            return;
        }

        if(currentAturan.size() != 1){
            dialogTidakDitemukan();
            return;
        }

        db.setCandidate(currentAturan.get(currentIndeksAturan).getAturanKodeGejala(), currentLevel);
        currentIndeksAturan = 0;
        ++currentLevel;
        currentAturan = db.newAturan(currentLevel);
        if(currentAturan.size() > 0){
            loadQuestion();
            return;
        }
        hasilDiagnosa(lastAturan.getAturanKodePerilaku());

    }



    private void jawabYa() {

        db.setCandidate(currentAturan.get(currentIndeksAturan).getAturanKodeGejala(), currentLevel);
        currentIndeksAturan = 0;
        ++currentLevel;
        currentAturan = db.newAturan(currentLevel);

        if (currentAturan.size() > 0){
            loadQuestion();
            return;
        }
        hasilDiagnosa(lastAturan.getAturanKodePerilaku());

    }
    private void hasilDiagnosa(int lastKodePerilaku) {
        Perilaku perilaku = db.getPerilakuWhereCode(lastKodePerilaku);
        DialogHasilDiagnosa fragment = DialogHasilDiagnosa.newInstance(perilaku);
        fragment.setCancelable(false);
        fragment.show(getSupportFragmentManager(),"dialog");
    }


    private void dialogTidakDitemukan() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Pesan")
                .setMessage("Maaf, data diagnosa tidak ditemukan. Silahkan Coba Lagi !")
                .setPositiveButton("ok", (dialogInterface, i) -> finish());
        dialog.show();
    }

    private void loadQuestion() {
        lastAturan = currentAturan.get(currentIndeksAturan);
        String kode_gejala = "P" + currentAturan.get(currentIndeksAturan).getGejalaAturan().getKodeGejala();
        tvKodeGejala.setText(kode_gejala);
        tvPertanyaan.setText(currentAturan.get(currentIndeksAturan).getGejalaAturan().getNamaGejala());

    }

    @Override
    public void fragmentInteraction(int kodePerilaku) {
        Intent i = new Intent(PertanyaanGejala.this, DetailPerilaku.class);
        i.putExtra("EXTRA_KODE_KERUSAKAN", kodePerilaku);
        startActivity(i);
        finish();
    }

}