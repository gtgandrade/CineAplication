package com.example.gtg.cineaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gtg.cineaplication.DAO.FilmeDAO;
import com.example.gtg.cineaplication.adapter.FilmeAdapter;
import com.example.gtg.cineaplication.modelo.Filme;

import java.util.List;

public class ConfiguracoesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Filme> filmes;
    private FilmeDAO fimeBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        recyclerView = (RecyclerView) findViewById(R.id.configuracoes_rvFilmes);
        fimeBD = new FilmeDAO(this);
        FilmeAdapter filmeAdapter = new FilmeAdapter(this, fimeBD.procurarTodos());
        GridLayoutManager glm = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(glm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(filmeAdapter);
    }

    public void carregarImagem(View view){
        Intent intentCadastroFilme = new Intent(this, CadastroFilmeActivity.class);
        startActivity(intentCadastroFilme);
    }
}
