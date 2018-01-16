package com.example.gtg.cineaplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.gtg.cineaplication.modelo.Horario;

import java.util.List;

/**
 * Created by gtg on 15/01/18.
 */

public class HorarioAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Horario> horarios;
    public HorarioAdapter(Context context, List<Horario> horarios){
        this.context = context;
        this.horarios = horarios;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
