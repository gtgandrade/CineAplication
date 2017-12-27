package com.example.gtg.cineaplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.gtg.cineaplication.R;

/**
 * Created by gtg on 27/12/17.
 */

public class IngressosViewHolder extends RecyclerView.ViewHolder {

    final TextView lblTituloFilmeM;
    final TextView lblQtdInteiraM;
    final TextView lblQtdMeiaM;
    final TextView lblQtdPipocaRefriM;
    final TextView lblPrecoInteiraM;
    final TextView lblPrecoMeiaM;
    final TextView lblPrecoPipocaRefriM;

    public IngressosViewHolder(View itemView) {
        super(itemView);
        lblTituloFilmeM = (TextView) itemView.findViewById(R.id.lblTituloFilmeM);
        lblQtdInteiraM = (TextView) itemView.findViewById(R.id.lblQtdInteiraM);
        lblQtdMeiaM = (TextView) itemView.findViewById(R.id.lblQtdMeiaM);
        lblQtdPipocaRefriM = (TextView) itemView.findViewById(R.id.lblQtdPipocaRefriM);
        lblPrecoInteiraM = (TextView) itemView.findViewById(R.id.lblPrecoInteiraM);
        lblPrecoMeiaM = (TextView) itemView.findViewById(R.id.lblPrecoMeiaM);
        lblPrecoPipocaRefriM = (TextView) itemView.findViewById(R.id.lblPrecoPipocaRefriM);
    }
}
