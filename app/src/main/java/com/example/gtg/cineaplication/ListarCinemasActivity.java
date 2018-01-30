package com.example.gtg.cineaplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gtg.cineaplication.DAO.CinemaDAO;

/**
 * Created by leo_b on 29/01/2018.
 */

public class ListarCinemasActivity extends AppCompatActivity {
    private RecyclerView rclListar;
    private CinemaDAO dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cinemas);

        rclListar = (RecyclerView)findViewById(R.id.rcl_listarCinema);
        rclListar.setLayoutManager(new LinearLayoutManager(this));

        dao = new CinemaDAO(getBaseContext());

    }

    public void ListarCinemas(View view) {

    }
}
