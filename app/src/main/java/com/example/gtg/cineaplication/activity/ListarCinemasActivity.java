package com.example.gtg.cineaplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gtg.cineaplication.DAO.CinemaDAO;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Cinema;

import com.example.gtg.cineaplication.adapter.CinemaAdapter;

import java.util.List;

/**
 * Created by leo_b on 29/01/2018.
 */

public class ListarCinemasActivity extends AppCompatActivity {
    private RecyclerView rclListar;
    private CinemaDAO cinemadao;
    private List<Cinema> cinemas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cinemas);

        rclListar = (RecyclerView)findViewById(R.id.rcl_listarCinema);

        cinemadao = new CinemaDAO(getBaseContext());
        CinemaAdapter cineAdapter = new CinemaAdapter(this,cinemadao.carregarcinemas());
        rclListar.setAdapter(cineAdapter);

        LinearLayoutManager lmn = new LinearLayoutManager(this);
        rclListar.setLayoutManager(lmn);

    }



    //public void ListarCinemas(View view) {

    //}
}
