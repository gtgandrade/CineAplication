package com.example.gtg.cineaplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Ingresso;

import java.util.List;

/**
 * Created by gtg on 27/12/17.
 */


public class IngressosAdapter extends RecyclerView.Adapter{
   private Context context;
   private List<Ingresso> ingressos;

    public IngressosAdapter(Context context, List<Ingresso> ingressos){
        this.context = context;
        this.ingressos = ingressos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(this.context).inflate(R.layout.activity_ingressos_adapter, null);
        IngressosViewHolder ingressosViewHolder = new IngressosViewHolder(viewItem);
        return ingressosViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Ingresso ingresso = this.ingressos.get(position);
        IngressosViewHolder ivh = (IngressosViewHolder) holder;
        ivh.lblTituloFilmeM.setText(ingresso.getSessao().getFilme().getNome());
        ivh.lblQtdInteiraM.setText(String.valueOf(ingresso.getQtdinteira()));
        ivh.lblQtdMeiaM.setText(String.valueOf(ingresso.getQtdmeia()));
        ivh.lblQtdPipocaRefriM.setText(String.valueOf(ingresso.getPipocarefrigerante()));
        ivh.lblPrecoInteiraM.setText(String.valueOf(ingresso.getQtdinteira()*ingresso.getPrecoingresso()));
        ivh.lblPrecoMeiaM.setText(String.valueOf(ingresso.getQtdmeia()*(ingresso.getPrecoingresso()/2)));
        ivh.lblPrecoPipocaRefriM.setText(String.valueOf(ingresso.getPipocarefrigerante()*ingresso.getPrecopipocarefrigerante()));
    }

    @Override
    public int getItemCount() {
        return this.ingressos.size();
    }
}
