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

public class CinemasViewHolder extends RecyclerView.ViewHolder {

    final TextView lblCinema;
    final TextView lblEndereco;
    final Button btnEditar;

    public CinemasViewHolder(View itemView) {
        super(itemView);
        this.lblCinema = (TextView) itemView.findViewById(R.id.cinemas_adapter_cinema);
        this.lblEndereco = (TextView) itemView.findViewById(R.id.cinemas_adapter_endereco);
        this.btnEditar = (Button) itemView.findViewById(R.id.btEditaCinema);
    }
}
