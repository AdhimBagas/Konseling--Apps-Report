package com.adhimbagas.finalprojectskripsi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.adhimbagas.finalprojectskripsi.R;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.Perilaku;
import com.adhimbagas.finalprojectskripsi.ui.activity.activityRobo.DetailPerilaku;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListPerilakuAdapter extends RecyclerView.Adapter<ListPerilakuAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Perilaku> perilakuList;

    public ListPerilakuAdapter(Context context, ArrayList<Perilaku> perilakuList) {
        this.context = context;
        this.perilakuList = perilakuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_perilaku_layout,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int img = perilakuList.get(position).getImagePerilaku();
        Glide.with(context)
                .load(img)
                .fitCenter()
                .into(holder.imgList);

        holder.tvPerilaku.setText(perilakuList.get(position).getNamaPerilaku());
        holder.parent.setOnClickListener(view -> {
            Intent i = new Intent(context, DetailPerilaku.class);
            i.putExtra("EXTRA_KODE_KERUSAKAN", perilakuList.get(position).getKodePerilaku());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return perilakuList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout parent;
        ImageView imgList;
        TextView tvPerilaku;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent_layout);
            imgList = itemView.findViewById(R.id.img_list_perilaku);
            tvPerilaku = itemView.findViewById(R.id.tv_list_perilaku);
        }
    }
}
