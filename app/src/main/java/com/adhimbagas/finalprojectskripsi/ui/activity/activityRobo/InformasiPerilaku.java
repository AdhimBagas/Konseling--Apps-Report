package com.adhimbagas.finalprojectskripsi.ui.activity.activityRobo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.adhimbagas.finalprojectskripsi.R;
import com.adhimbagas.finalprojectskripsi.adapter.ListPerilakuAdapter;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.Perilaku;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.SispakDBHelper;

import java.util.ArrayList;

public class InformasiPerilaku extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_perilaku);

        SispakDBHelper db = SispakDBHelper.getInstance(this);
        ArrayList<Perilaku> perilakuArrayList = db.getListPerilaku();

        RecyclerView r = findViewById(R.id.rv_perilaku);
        ListPerilakuAdapter adapter = new ListPerilakuAdapter(this, perilakuArrayList);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.addItemDecoration(new DividerItemDecoration(this, 1));
        r.setAdapter(adapter);
    }
}