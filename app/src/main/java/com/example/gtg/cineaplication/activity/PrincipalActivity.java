package com.example.gtg.cineaplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gtg.cineaplication.R;

public class PrincipalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
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

    public void irParaActivityMap(View view){
        Intent intent = new Intent(this, CinemasProximosActivity.class);
        startActivity(intent);

        /*
        Intent intent = new Intent(this, CinemaLocalActivity.class);
        startActivityForResult(intent,1);
        */
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==1)
        {
            Toast.makeText(this, data.getStringExtra("lat") + "," + data.getStringExtra("lng"), Toast.LENGTH_SHORT).show();
        }
    }
}
