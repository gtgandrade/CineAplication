package com.example.gtg.cineaplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Cinema;
import com.example.gtg.cineaplication.modelo.Sessao;

import java.util.List;

public class CinemaAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<Cinema> cinemas;

    public CinemaAdapter(Context context, List<Cinema> cinemas){
        this.context = context;
        this.cinemas = cinemas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.activity_cinemas_adapter, null);
        CinemasViewHolder fvh = new CinemasViewHolder(itemView);
        return fvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Cinema cinema = this.cinemas.get(position);
        CinemasViewHolder cinemasViewHolder = (CinemasViewHolder) holder;
        cinemasViewHolder.lblCinema.setText(String.valueOf(cinema.getNome()));
        cinemasViewHolder.lblEndereco.setText(String.valueOf(cinema.getEndereco()));
        cinemasViewHolder.btnEditar.setTag(cinema);
    }

    @Override
    public int getItemCount() {
        return this.cinemas.size();
    }
}
