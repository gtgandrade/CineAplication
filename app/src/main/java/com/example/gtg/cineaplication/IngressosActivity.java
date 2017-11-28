package com.example.gtg.cineaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gtg.cineaplication.DB.IngressoBD;
import com.example.gtg.cineaplication.modelo.Ingresso;

import java.util.List;

public class IngressosActivity extends AppCompatActivity {
    private TextView lblTituloFilmeM;
    private int indiceIngresso = -1;
    private List<Ingresso> ingressos;
    private Ingresso ingresso;
    private IngressoBD ingressoBD;
    private TextView lblQtdInteira;
    private TextView lblPrecoInteira;
    private TextView lblQtdMeia;
    private TextView lblPrecoMeia;
    private TextView lblQtdPipocaRefri;
    private TextView lblPrecoPipocaRefri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingressos);
        lblTituloFilmeM = findViewById(R.id.lblTituloFilmeM);
        ingressoBD = new IngressoBD(this);
        ingressos = ingressoBD.findAll();
        ingresso = ingressos.get(++indiceIngresso);
        lblTituloFilmeM.setText(ingresso.getSessao().getFilme().getNome());
        lblQtdInteira = findViewById(R.id.lblQtdInteiraM);
        lblPrecoInteira = findViewById(R.id.lblPrecoInteiraM);
        lblQtdMeia = findViewById(R.id.lblQtdMeiaM);
        lblPrecoMeia = findViewById(R.id.lblPrecoMeiaM);
        lblQtdPipocaRefri = findViewById(R.id.lblQtdPipocaRefriM);
        lblPrecoPipocaRefri = findViewById(R.id.lblPrecoPipocaRefriM);

        lblTituloFilmeM.setText(ingresso.getSessao().getFilme().getNome());
        lblQtdInteira.setText(String.valueOf(ingresso.getQtdinteira()));
        lblPrecoInteira.setText(String.valueOf(ingresso.getQtdinteira()*ingresso.getPrecoingresso()));
        lblQtdMeia.setText(String.valueOf(ingresso.getQtdmeia()));
        lblPrecoMeia.setText(String.valueOf(ingresso.getQtdmeia()*(ingresso.getPrecoingresso()/2)));
        lblQtdPipocaRefri.setText(String.valueOf(ingresso.getPipocarefrigerante()));
        lblPrecoPipocaRefri.setText(String.valueOf(ingresso.getPrecopipocarefrigerante()*ingresso.getPipocarefrigerante()));
    }

    public void irParaIngressoProximo(View view){
        indiceIngresso = (indiceIngresso +1)% ingressos.size();
        ingresso = ingressos.get(indiceIngresso);
        lblTituloFilmeM.setText(ingresso.getSessao().getFilme().getNome());
        lblQtdInteira.setText(String.valueOf(ingresso.getQtdinteira()));
        lblPrecoInteira.setText(String.valueOf(ingresso.getQtdinteira()*ingresso.getPrecoingresso()));
        lblQtdMeia.setText(String.valueOf(ingresso.getQtdmeia()));
        lblPrecoMeia.setText(String.valueOf(ingresso.getQtdmeia()*(ingresso.getPrecoingresso()/2)));
        lblQtdPipocaRefri.setText(String.valueOf(ingresso.getPipocarefrigerante()));
        lblPrecoPipocaRefri.setText(String.valueOf(ingresso.getPrecopipocarefrigerante()*ingresso.getPipocarefrigerante()));
    }
    public void irParaIngressoAnterior(View view){
        if(indiceIngresso == 0)
            indiceIngresso = ingressos.size();
        indiceIngresso = (indiceIngresso -1)% ingressos.size();
        ingresso = ingressos.get(indiceIngresso);
        lblTituloFilmeM.setText(ingresso.getSessao().getFilme().getNome());
        lblQtdInteira.setText(String.valueOf(ingresso.getQtdinteira()));
        lblPrecoInteira.setText(String.valueOf(ingresso.getQtdinteira()*ingresso.getPrecoingresso()));
        lblQtdMeia.setText(String.valueOf(ingresso.getQtdmeia()));
        lblPrecoMeia.setText(String.valueOf(ingresso.getQtdmeia()*(ingresso.getPrecoingresso()/2)));
        lblQtdPipocaRefri.setText(String.valueOf(ingresso.getPipocarefrigerante()));
        lblPrecoPipocaRefri.setText(String.valueOf(ingresso.getPrecopipocarefrigerante()*ingresso.getPipocarefrigerante()));
    }
}
