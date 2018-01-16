package com.example.gtg.cineaplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gtg.cineaplication.R;
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
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.activity_horarios_adapter, null);
        HorariosViewHolder hvh = new HorariosViewHolder(itemView);
        return hvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Horario horario = horarios.get(position);
        HorariosViewHolder horariosViewHolder = (HorariosViewHolder) holder;
        horariosViewHolder.lblDescricao.setText(horario.getDescricao().toString());

    }

    @Override
    public int getItemCount() {
        return horarios.size();
    }

    private class HorarioOnClickListener implements View.OnClickListener{
        private List<Horario> horarios;
        private int position;
        public  HorarioOnClickListener(Context context, List<Horario> horarios, int position){
            this.horarios = horarios;
            this.position = position;
        }
        @Override
        public void onClick(View view) {
            Horario horario = horarios.get(position);

        }
    }
}
