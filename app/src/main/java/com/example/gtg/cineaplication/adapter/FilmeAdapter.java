package com.example.gtg.cineaplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.activity.CadastroFilmeActivity;
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
        filmesViewHolder.imgFilme.setImageURI(Uri.parse(filme.getCartaz()));
        filmesViewHolder.lblTitulo.setText(filme.getNome());
        filmesViewHolder.lblDuracao.setText(String.valueOf(filme.getDuracao())+" min");
        filmesViewHolder.lblVersao.setText(filme.getVersao());
        filmesViewHolder.lblHabilitado.setText(filme.getHabilitado() == 1?"Em exibição":"Indisponível");
        filmesViewHolder.imgFilme.setOnClickListener(new FilmeOnClickListener(context, this.filmes, position));
    }

    @Override
    public int getItemCount() {
        return this.filmes.size();
    }

    private class FilmeOnClickListener implements View.OnClickListener {
        private List<Filme> filmes;
        private int position;

        public FilmeOnClickListener(Context context, List<Filme> filmes, int position) {
            this.filmes = filmes;
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            Filme filme = this.filmes.get(position);
            Intent intentEditarFilme = new Intent(context, CadastroFilmeActivity.class);
            Bundle parametros = new Bundle();
            parametros.putInt("idFilme", filme.getIdfilme());
            intentEditarFilme.putExtras(parametros);
            context.startActivity(intentEditarFilme);
        }
    }
}
