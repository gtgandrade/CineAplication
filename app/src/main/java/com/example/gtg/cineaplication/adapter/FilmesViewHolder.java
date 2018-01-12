package com.example.gtg.cineaplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.gtg.cineaplication.R;

/**
 * Created by gtg on 30/12/17.
 */

public class FilmesViewHolder extends RecyclerView.ViewHolder {
    final ImageView imgFilme;
    public FilmesViewHolder(View itemView) {
        super(itemView);
        this.imgFilme = (ImageView)itemView.findViewById(R.id.adapter_filme_imgFilme);
    }
}
