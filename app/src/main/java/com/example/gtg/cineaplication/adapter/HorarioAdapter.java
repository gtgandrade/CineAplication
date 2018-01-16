package com.example.gtg.cineaplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Horario;

import java.util.List;

/**
 * Created by gtg on 15/01/18.
 */

public class HorarioAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Horario> horarios;
    private ItemClickListener itemClickListener;

    public HorarioAdapter(Context context, List<Horario> horarios){
        this.context = context;
        this.horarios = horarios;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.activity_horarios_adapter, null);
        HorariosViewHolder hvh = new HorariosViewHolder(itemView);
        return hvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Horario horario = horarios.get(position);
        HorariosViewHolder horariosViewHolder = (HorariosViewHolder) holder;
        horariosViewHolder.lblDescricao.setText(horario.getDescricao().toString());
        horariosViewHolder.lblDescricao.setOnClickListener(new SelecaoHorarioOnClickListener(position, "SELECAO"));
        horariosViewHolder.imgbtEditar.setOnClickListener(new SelecaoHorarioOnClickListener(position, "EDICAO"));
        horariosViewHolder.imgbtExcluir.setOnClickListener(new SelecaoHorarioOnClickListener(position, "EXCLUSAO"));
    }

    @Override
    public int getItemCount() {
        return horarios.size();
    }

    private class SelecaoHorarioOnClickListener implements View.OnClickListener{
        private int position;
        private String action;
        public  SelecaoHorarioOnClickListener(int position, String action){
            this.position = position;
            this.action = action;
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(position, action);
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
