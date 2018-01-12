package com.example.gtg.cineaplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gtg.cineaplication.DAO.IngressoBD;
import com.example.gtg.cineaplication.adapter.IngressoAdapter;
import com.example.gtg.cineaplication.modelo.Ingresso;

import java.util.List;

public class IngressosActivity extends AppCompatActivity {
    private RecyclerView recyclerViewIngressos;
    private IngressoBD ingressoBD;
    private List<Ingresso> ingressos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingressos);
        recyclerViewIngressos = (RecyclerView)findViewById(R.id.ingressos_rvIngressos);
        recyclerViewIngressos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewIngressos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewIngressos.setHasFixedSize(true);
        ingressoBD = new IngressoBD(this);
        ingressos = ingressoBD.findAll();
        IngressoAdapter ingressosAdapter = new IngressoAdapter(this, ingressos);
        recyclerViewIngressos.setAdapter(ingressosAdapter);

        }
}
