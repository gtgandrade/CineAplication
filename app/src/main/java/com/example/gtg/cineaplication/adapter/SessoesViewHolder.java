package com.example.gtg.cineaplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gtg.cineaplication.R;

/**
 * Created by gtg on 27/12/17.
 */

public class SessoesViewHolder extends RecyclerView.ViewHolder {

    final TextView lblSala;
    final TextView lblCinema;
    final TextView lblHorario;
    final Button btnEditar;
    final ImageButton btnVip;

    public SessoesViewHolder(View itemView) {
        super(itemView);
        this.lblSala = (TextView) itemView.findViewById(R.id.sessoes_adapter_lblSala);
        this.lblCinema = (TextView) itemView.findViewById(R.id.sessoes_adapter_cinema);
        this.btnVip = (ImageButton) itemView.findViewById(R.id.btVipSessao);
        this.lblHorario = (TextView) itemView.findViewById(R.id.sessoes_adapter_lblHorario);
        this.btnEditar = (Button) itemView.findViewById(R.id.btEditaSessao);
    }
}
