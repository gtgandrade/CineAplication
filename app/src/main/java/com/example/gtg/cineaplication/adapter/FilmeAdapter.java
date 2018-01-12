package com.example.gtg.cineaplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Filme;

import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<Filme> filmes;

    public FilmeAdapter(Context context, List<Filme> filmes){
        this.context = context;
        this.filmes = filmes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.activity_filmes_adapter, null);
        FilmesViewHolder fvh = new FilmesViewHolder(itemView);
        return fvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Filme filme = this.filmes.get(position);
        FilmesViewHolder filmesViewHolder = (FilmesViewHolder)holder;
        filmesViewHolder.imgFilme.setImageResource(filme.getCodigo());
    }

    @Override
    public int getItemCount() {
        return this.filmes.size();
    }
}
