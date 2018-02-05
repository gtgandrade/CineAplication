package com.example.gtg.cineaplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gtg.cineaplication.dao.FilmeDAO;
import com.example.gtg.cineaplication.dao.SessaoDAO;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.adapter.SessaoAdapter;
import com.example.gtg.cineaplication.modelo.Filme;
import com.example.gtg.cineaplication.modelo.Sessao;

import java.util.List;

public class ListaSessaoActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSessoes;
    private SessaoDAO sessaoDAO;
    private List<Sessao> sessoes;
    private int filmeid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sessoes);
        recyclerViewSessoes = (RecyclerView)findViewById(R.id.sessoes_rvSessoes);
        recyclerViewSessoes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSessoes.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSessoes.setHasFixedSize(true);
        sessaoDAO = new SessaoDAO(this);

        Bundle parametros = getIntent().getExtras();
        filmeid = parametros.getInt("filmeid");
        FilmeDAO filmeBD = new FilmeDAO(this);
        Filme filme = filmeBD.procurarPorId(filmeid);

        sessoes = sessaoDAO.findSessoes(filme);
        SessaoAdapter sessaoAdapter = new SessaoAdapter(this, sessoes);
        recyclerViewSessoes.setAdapter(sessaoAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void abrirCadastroSessao(View view)
    {
        Bundle parametros = new Bundle();
        parametros.putInt("filmeid",filmeid);
        Intent intent = new Intent(this, CadastroSessaoActivity.class);
        intent.putExtras(parametros);
        startActivity(intent);
    }

    public void editarCadastroSessao(View view)
    {
        Sessao sessao = (Sessao) view.getTag();
        Bundle parametros = new Bundle();
        parametros.putBoolean("edit",true);
        parametros.putInt("filmeid",sessao.getFilme().getIdfilme());
        parametros.putInt("sessaoid",sessao.getIdsessao());

        Intent intent = new Intent(this, CadastroSessaoActivity.class);
        intent.putExtras(parametros);
        startActivity(intent);
    }

}