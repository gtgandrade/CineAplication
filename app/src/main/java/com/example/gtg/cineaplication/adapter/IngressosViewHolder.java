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
    final TextView lblValorTotalM;
    public IngressosViewHolder(View itemView) {
        super(itemView);
        lblTituloFilmeM = (TextView) itemView.findViewById(R.id.ingresssos_adapter_lblTituloFilme);
        lblQtdInteiraM = (TextView) itemView.findViewById(R.id.ingresssos_adapter_lblQtdInteira);
        lblQtdMeiaM = (TextView) itemView.findViewById(R.id.ingresssos_adapter_lblQtdMeia);
        lblQtdPipocaRefriM = (TextView) itemView.findViewById(R.id.ingresssos_adapter_lblQtdPipocaRefri);
        lblPrecoInteiraM = (TextView) itemView.findViewById(R.id.ingresssos_adapter_lblPrecoInteira);
        lblPrecoMeiaM = (TextView) itemView.findViewById(R.id.ingresssos_adapter_lblPrecoMeia);
        lblPrecoPipocaRefriM = (TextView) itemView.findViewById(R.id.ingresssos_adapter_lblPrecoPipocaRefri);
        lblValorTotalM = (TextView) itemView.findViewById(R.id.ingresssos_adapter_lblValorTotal);
    }
}
