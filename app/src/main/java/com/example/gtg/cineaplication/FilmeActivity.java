package com.example.gtg.cineaplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtg.cineaplication.DAO.FilmeDAO;
import com.example.gtg.cineaplication.modelo.Filme;

import java.util.List;

public class FilmeActivity extends AppCompatActivity {
    private TextView lblTituloFilme;
    private ImageView imgFilme;
    private int indiceFilme = -1;
    private List<Filme> filmes;
    private Filme filme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);
        lblTituloFilme = findViewById(R.id.filme_lblTituloFilme);
        FilmeDAO fimeBD = new FilmeDAO(this);
        filmes = fimeBD.procurarHabilitados();
        if(filmes.size() > 0) {
            filme = filmes.get(++indiceFilme);
            imgFilme = findViewById(R.id.filme_imgFilme);
            imgFilme.setImageURI(Uri.parse(filme.getCartaz()));
            lblTituloFilme.setText(filme.getNome());
        }
    }

    public void irParaFilmeProximo(View view){
        if(filmes.size() > 0) {
            indiceFilme = (indiceFilme + 1) % filmes.size();
            filme = filmes.get(indiceFilme);
            imgFilme.setImageURI(Uri.parse(filme.getCartaz()));
            lblTituloFilme.setText(filme.getNome());
        }
    }
    public void irParaFilmeAnterior(View view){
        if(filmes.size() > 0) {
            if (indiceFilme == 0)
                indiceFilme = filmes.size();
            indiceFilme = (indiceFilme - 1) % filmes.size();
            filme = filmes.get(indiceFilme);
            imgFilme.setImageURI(Uri.parse(filme.getCartaz()));
            lblTituloFilme.setText(filme.getNome());
        }
    }
    public void irParaSessao(View view){
        if(filmes.size() > 0) {
            Intent intentSessao = new Intent(this, SessaoActivity.class);
            Bundle parametros = new Bundle();
            parametros.putInt("idfilme", filme.getIdfilme());
            imgFilme.setImageURI(Uri.parse(filme.getCartaz()));
            parametros.putString("nome", filme.getNome());
            intentSessao.putExtras(parametros);
            startActivity(intentSessao);
        }
    }
}
