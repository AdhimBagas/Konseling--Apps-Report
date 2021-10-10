package com.adhimbagas.finalprojectskripsi.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import com.adhimbagas.finalprojectskripsi.R;
import com.adhimbagas.finalprojectskripsi.ui.activity.activityRobo.PertanyaanGejala;

public class RoboHome extends AppCompatActivity {

    CardView cvDiagnosa, cvInforPerilaku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robo_home);

        cvDiagnosa = findViewById(R.id.cv_diagnosa);
        cvDiagnosa.setOnClickListener(view -> {
            Intent i = new Intent(RoboHome.this, PertanyaanGejala.class);
            startActivity(i);
        });
    }
}