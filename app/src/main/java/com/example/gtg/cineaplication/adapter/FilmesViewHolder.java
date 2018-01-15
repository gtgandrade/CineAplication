package com.example.gtg.cineaplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtg.cineaplication.R;

/**
 * Created by gtg on 30/12/17.
 */

public class FilmesViewHolder extends RecyclerView.ViewHolder {
    final ImageView imgFilme;
    final TextView lblTitulo;
    final TextView lblDuracao;
    final TextView lblVersao;
    final TextView lblHabilitado;
    public FilmesViewHolder(View itemView) {
        super(itemView);
        this.imgFilme = (ImageView)itemView.findViewById(R.id.adapter_filme_imgFilme);
        this.lblTitulo = (TextView) itemView.findViewById(R.id.adapter_filme_lblTitulo);
        this.lblDuracao = (TextView) itemView.findViewById(R.id.adapter_filme_lblDuracao);
        this.lblVersao = (TextView) itemView.findViewById(R.id.adapter_filme_lblVersao);
        this.lblHabilitado = (TextView) itemView.findViewById(R.id.adapter_filme_lblHabilitado);
    }
}
