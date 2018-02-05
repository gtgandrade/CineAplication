package com.example.gtg.cineaplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gtg.cineaplication.dao.CinemaDAO;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Cinema;

/**
 * Created by leo_b on 27/01/2018.
 */

public class CadastroCinemaActivity extends AppCompatActivity {
    private EditText edtNome;
    private EditText edtEndereco;
    private Button btnListar;
    private Button btnRemover;
    private Cinema cinema;
    private CinemaDAO cinemaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cinema);

        edtNome = (EditText)findViewById(R.id.cadastroCinema_edtNome);
        edtEndereco = (EditText)findViewById(R.id.cadastroCinema_edtEndereco);
        btnListar.setVisibility(View.GONE);
        btnRemover.setVisibility(View.GONE);

        Bundle parametrosEntradaC = getIntent().getExtras();
        int idCine = parametrosEntradaC.getInt("idCinema");
        cinema = new Cinema();
        cinemaDAO = new CinemaDAO(this);
        if(idCine!=0){
            cinema = cinemaDAO.procurarPorId(idCine);
            inserirCinemaExistente(cinema);
            btnListar.setVisibility(View.VISIBLE);
            btnRemover.setVisibility(View.VISIBLE);
        }
    }

    private void inserirCinemaExistente(Cinema cinema) {
        edtNome.setText(cinema.getNome());
        edtEndereco.setText(cinema.getEndereco());

    }

    public void salvarCinema(View view) {
        String endereco="";
        int id=0;

        endereco = edtEndereco.getText().toString();

        Cinema cinema = new Cinema();
        cinema.setEndereco(endereco);

        id = cinema.getId();

        CinemaDAO daocinema = new CinemaDAO(this);
        if( id==0 ){
            daocinema.adicionar(cinema);
        }
        else{
            daocinema.atualizar(cinema);
        }
        Intent intentConfiguracoes = new Intent(this, ConfiguracoesActivity.class);
        navigateUpTo(intentConfiguracoes);
    }

    public void removerCinema(View view) {
        cinemaDAO.excluir(cinema);
        Intent intentConfiguracoes = new Intent(this,ConfiguracoesActivity.class);
        startActivity(intentConfiguracoes);
    }


    public void listarCinemas(View view) {
        Intent intentListarCinemas = new Intent(this,ListarCinemasActivity.class);
        Bundle parametros = new Bundle();
        parametros.putInt("idcinema",cinema.getId());
        intentListarCinemas.putExtras(parametros);
        startActivity(intentListarCinemas);
    }
}
