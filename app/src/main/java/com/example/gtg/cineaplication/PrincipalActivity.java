package com.example.gtg.cineaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gtg.cineaplication.DAO.CriaBD;

public class PrincipalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Button btnFilmesCartaz = findViewById(R.id.principal_btnFilmesCartaz);
        CriaBD criaBD = new CriaBD(this);
        try{
            criaBD.getReadableDatabase();
        }finally {
            criaBD.close();
        }
    }

    public void irParaActivityFilme(View view){
        Intent intentFilme = new Intent(this, FilmeActivity.class);
        startActivity(intentFilme);
    }
    public void irParaActivityIngressos(View view){
        Intent intentIngressos = new Intent(this, IngressosActivity.class);
        startActivity(intentIngressos);
    }
    public void irParaActivityConfiguracoes(View view){
        Intent intentConfiguracoes = new Intent(this, ConfiguracoesActivity.class);
        startActivity(intentConfiguracoes);
    }
}
