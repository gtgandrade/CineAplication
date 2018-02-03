package com.example.gtg.cineaplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.gtg.cineaplication.DAO.FilmeDAO;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.adapter.FilmeAdapter;
import com.example.gtg.cineaplication.modelo.Filme;
import java.util.List;

public class ConfiguracoesActivity extends AppCompatActivity {
    private int cinema;
    private RecyclerView recyclerView;
    private List<Filme> filmes;
    private FilmeDAO fimeBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        recyclerView = (RecyclerView) findViewById(R.id.configuracoes_rvFilmes);

        cinema = getIntent().getIntExtra("pos", 1);

        fimeBD = new FilmeDAO(this);
        FilmeAdapter filmeAdapter = new FilmeAdapter(this, fimeBD.procurarTodos());
        LinearLayoutManager glm = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(glm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(filmeAdapter);
    }

    public void irParaCadastroFilmeActivity(View view) {
        Intent intentCadastroFilme = new Intent(this, CadastroFilmeActivity.class);
        Bundle parametros = new Bundle();
        parametros.putInt("idFilme", 0);
        parametros.putInt("cinema", cinema);
        intentCadastroFilme.putExtras(parametros);
        startActivity(intentCadastroFilme);
    }
}