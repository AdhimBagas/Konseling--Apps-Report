package com.adhimbagas.finalprojectskripsi.ui.activity.activityRobo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhimbagas.finalprojectskripsi.R;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.Perilaku;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.SispakDBHelper;
import com.bumptech.glide.Glide;

public class DetailPerilaku extends AppCompatActivity {

    private TextView tvPerilaku, tvDeskripsi, tvCiriPerilaku, tvSolusiPerilaku;
    private ImageView imgPerilaku;
    private Perilaku perilaku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_perilaku);

        tvPerilaku = findViewById(R.id.tv_perilaku_detail);
        tvDeskripsi = findViewById(R.id.tv_deskripsi_detail);
        tvCiriPerilaku = findViewById(R.id.tv_ciri_perilaku);
        tvSolusiPerilaku = findViewById(R.id.tv_solusi_detail);
        imgPerilaku = findViewById(R.id.img_perilaku_detail);

        Intent result = getIntent();
        int extraKode = result.getIntExtra("EXTRA_KODE_KERUSAKAN", 0);

        SispakDBHelper db = SispakDBHelper.getInstance(this);
        perilaku = db.getPerilakuWhereCode(extraKode);

        Glide.with(this)
                .load(perilaku.getImagePerilaku())
                .into(imgPerilaku);


        tvPerilaku.setText(perilaku.getNamaPerilaku());
        tvDeskripsi.setText(perilaku.getDeskripsi());
        tvCiriPerilaku.setText(perilaku.getGejalaPerilaku());
        tvSolusiPerilaku.setText(perilaku.getSolusi());
    }
}