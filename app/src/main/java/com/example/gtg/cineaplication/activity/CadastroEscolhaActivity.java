package com.example.gtg.cineaplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.activity.CadastroCinemaActivity;

/**
 * Created by leo_b on 27/01/2018.
 */

public class CadastroEscolhaActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_cadastro_escolha);

    }

    public void irParaCadastroCinemaActivity(View view) {
        Intent intentCadastroCinema = new Intent(this, CadastroCinemaActivity.class);
        Bundle parametros = new Bundle();
        parametros.putInt("idCinema",0);
        intentCadastroCinema.putExtras(parametros);
        startActivity(intentCadastroCinema);
    }

    public void irParaCadastroFilmeActivity(View view) {
        Intent intentCadastroFilme = new Intent(this, CadastroFilmeActivity.class);
        Bundle parametros = new Bundle();
        parametros.putInt("idFilme",0);
        intentCadastroFilme.putExtras(parametros);
        startActivity(intentCadastroFilme);
    }
}
