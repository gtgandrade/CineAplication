package com.example.gtg.cineaplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gtg.cineaplication.R;

/**
 * Created by gtg on 15/01/18.
 */

public class HorariosViewHolder extends RecyclerView.ViewHolder {

    final TextView lblDescricao;
    final ImageButton imgbtEditar;
    final ImageButton imgbtExcluir;

    public HorariosViewHolder(View itemView) {
        super(itemView);
        this.lblDescricao = itemView.findViewById(R.id.adapter_horarios_lblDescricao);
        this.imgbtEditar = itemView.findViewById(R.id.adapter_horarios_imgbtEditar);
        this.imgbtExcluir = itemView.findViewById(R.id.adapter_horarios_imgbtExcluir);
    }
}
