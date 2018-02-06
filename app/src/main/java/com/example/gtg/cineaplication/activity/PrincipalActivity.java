package com.example.gtg.cineaplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gtg.cineaplication.R;

public class PrincipalActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private final int LOCAL_GRANT = 2;

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

    public void irParaActivityCinemas(View view){
        Intent intentCinemas = new Intent(this, ListaCinemaActivity.class);
        startActivity(intentCinemas);
    }

    public void irParaActivityMap(View view){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCAL_GRANT);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        if (requestCode == LOCAL_GRANT && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            Intent intent = new Intent(this, CinemasProximosActivity.class);
            startActivity(intent);

            /*
            Intent intent = new Intent(this, CinemaLocalActivity.class);
            startActivityForResult(intent,1);
            */
        }
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
