package com.adhimbagas.finalprojectskripsi.ui.activity.activityRobo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.adhimbagas.finalprojectskripsi.R;
import com.adhimbagas.finalprojectskripsi.model.RoboModel.Perilaku;
import com.bumptech.glide.Glide;

public class DialogHasilDiagnosa extends DialogFragment {

    private static final String ARG_KODE_PERILAKU = "argKodePerilaku";
    private static final String ARG_NAMA_PERILAKU = "argNamaPerilaku";
    private static final String ARG_IMAGE_PERILAKU = "argImagePerilaku";


    private TextView tvNamePerilaku;
    private ImageView imgPerilaku;
    private Button btnDetail;

    private int kodePerilaku;
    private String namaPerilaku;
    private int imageResPerilaku;

    private OnFragmentInteraction mListener;

    public static DialogHasilDiagnosa newInstance(Perilaku perilaku){
        DialogHasilDiagnosa fragment = new DialogHasilDiagnosa();
        Bundle args = new Bundle();
        args.putInt(ARG_KODE_PERILAKU, perilaku.getKodePerilaku());
        args.putString(ARG_NAMA_PERILAKU, perilaku.getNamaPerilaku());
        args.putInt(ARG_IMAGE_PERILAKU, perilaku.getImagePerilaku());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            kodePerilaku = getArguments().getInt(ARG_KODE_PERILAKU);
            namaPerilaku = getArguments().getString(ARG_NAMA_PERILAKU);
            imageResPerilaku = getArguments().getInt(ARG_IMAGE_PERILAKU);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_hasil_diagnosa,container, false);
        tvNamePerilaku = view.findViewById(R.id.tv_jenis_perilaku);
        imgPerilaku = view.findViewById(R.id.img_perilaku);
        btnDetail = view.findViewById(R.id.btn_detail);

        Glide.with(getActivity())
                .load(imageResPerilaku)
                .into(imgPerilaku);

        tvNamePerilaku.setText(namaPerilaku);

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPress(kodePerilaku);
            }
        });
        return view;
    }

    public void onButtonPress(int kodePerilaku) {
        if ((mListener != null)) {
            mListener.fragmentInteraction(kodePerilaku);
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteraction){
            mListener = (OnFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement OnFragmentInteraction");

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteraction{
        void fragmentInteraction(int kodePerilaku);
    }
}
