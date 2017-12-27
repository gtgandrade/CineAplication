package com.example.gtg.cineaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.gtg.cineaplication.DB.IngressoBD;
import com.example.gtg.cineaplication.adapter.IngressosAdapter;
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
        recyclerViewIngressos = (RecyclerView)findViewById(R.id.rvIngressos);
        recyclerViewIngressos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewIngressos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewIngressos.setHasFixedSize(true);
        ingressoBD = new IngressoBD(this);
        ingressos = ingressoBD.findAll();
        IngressosAdapter ingressosAdapter = new IngressosAdapter(this, ingressos);
        recyclerViewIngressos.setAdapter(ingressosAdapter);

        }
}
