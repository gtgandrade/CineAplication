package com.example.gtg.cineaplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtg.cineaplication.dao.FilmeDAO;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Filme;

import java.util.List;

public class FilmeActivity extends AppCompatActivity {
    private TextView lblEstreiaFilme;
    private TextView lblTituloFilme;
    private TextView lblVersaoFilme;
    private ImageView imgFilme;
    private int indiceFilme = -1;
    private List<Filme> filmes;
    private Filme filme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);
        lblEstreiaFilme = (TextView) findViewById(R.id.filme_lblEstreiaFilme);
        lblTituloFilme = (TextView) findViewById(R.id.filme_lblTituloFilme);
        lblVersaoFilme = (TextView) findViewById(R.id.filme_lblVersaoFilme);
        FilmeDAO fimeBD = new FilmeDAO(this);
        filmes = fimeBD.procurarHabilitados();
        if(filmes.size() > 0) {
            filme = filmes.get(++indiceFilme);
            imgFilme = findViewById(R.id.filme_imgFilme);
            imgFilme.setImageURI(Uri.parse(filme.getCartaz()));
            lblEstreiaFilme.setText(filme.getEstreia() == 1?"ESTREIA":"");
            lblTituloFilme.setText(filme.getNome());
            lblVersaoFilme.setText(filme.getVersao());
        }
    }

    public void irParaFilmeProximo(View view){
        if(filmes.size() > 0) {
            indiceFilme = (indiceFilme + 1) % filmes.size();
            filme = filmes.get(indiceFilme);
            imgFilme.setImageURI(Uri.parse(filme.getCartaz()));
            lblEstreiaFilme.setText(filme.getEstreia() == 1?"ESTREIA":"");
            lblTituloFilme.setText(filme.getNome());
            lblVersaoFilme.setText(filme.getVersao());
        }
    }
    public  void irParaFilmeAnterior(View view){
        if(filmes.size() > 0) {
            if (indiceFilme == 0)
                indiceFilme = filmes.size();
            indiceFilme = (indiceFilme - 1) % filmes.size();
            filme = filmes.get(indiceFilme);
            imgFilme.setImageURI(Uri.parse(filme.getCartaz()));
            lblEstreiaFilme.setText(filme.getEstreia() == 1?"ESTREIA":"");
            lblTituloFilme.setText(filme.getNome());
            lblVersaoFilme.setText(filme.getVersao());
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
