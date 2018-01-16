package com.example.gtg.cineaplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Sessao;

import java.util.List;

public class SessaoAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<Sessao> sessoes;

    public SessaoAdapter(Context context, List<Sessao> sessoes){
        this.context = context;
        this.sessoes = sessoes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.activity_sessoes_adapter, null);
        SessoesViewHolder fvh = new SessoesViewHolder(itemView);
        return fvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Sessao sessao = this.sessoes.get(position);
        SessoesViewHolder sessoesViewHolder = (SessoesViewHolder) holder;
        sessoesViewHolder.btnVip.setImageResource( sessao.isVip() ? android.R.drawable.star_big_on : android.R.drawable.star_big_off );
        sessoesViewHolder.lblSala.setText("Sala " + String.valueOf(sessao.getSala()));
        sessoesViewHolder.lblHorario.setText(sessao.getHorario().getDescricao());
        sessoesViewHolder.btnEditar.setTag(sessao);
    }

    @Override
    public int getItemCount() {
        return this.sessoes.size();
    }
}
