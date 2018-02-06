package com.example.gtg.cineaplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gtg.cineaplication.adapter.CinemaAdapter;
import com.example.gtg.cineaplication.dao.CinemaDAO;
import com.example.gtg.cineaplication.dao.FilmeDAO;
import com.example.gtg.cineaplication.dao.SessaoDAO;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.adapter.SessaoAdapter;
import com.example.gtg.cineaplication.modelo.Cinema;
import com.example.gtg.cineaplication.modelo.Filme;
import com.example.gtg.cineaplication.modelo.Sessao;

import java.util.List;

public class ListaCinemaActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCinemas;
    private CinemaDAO cinemaDAO;
    private List<Cinema> cinemas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cinemas);
        recyclerViewCinemas = (RecyclerView)findViewById(R.id.cinemas_rvCinemas);
        recyclerViewCinemas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCinemas.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCinemas.setHasFixedSize(true);
        cinemaDAO = new CinemaDAO(this);

        cinemas = cinemaDAO.carregarcinemas();
        CinemaAdapter cinemaAdapter = new CinemaAdapter(this, cinemas);
        recyclerViewCinemas.setAdapter(cinemaAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void abrirCadastroCinema(View view)
    {
        Intent intent = new Intent(this, CadastroCinemaActivity.class);
        startActivity(intent);
    }

    public void editarCadastroCinema(View view)
    {
        Cinema cinema = (Cinema) view.getTag();
        Bundle parametros = new Bundle();
        parametros.putBoolean("edit",true);
        parametros.putInt("cinemaid",cinema.getId());

        Intent intent = new Intent(this, CadastroCinemaActivity.class);
        intent.putExtras(parametros);
        startActivity(intent);
    }

}