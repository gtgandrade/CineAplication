package com.example.gtg.cineaplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.gtg.cineaplication.R;

public class PrincipalActivity extends AppCompatActivity {

	private int cinema;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		cinema = getIntent().getIntExtra("pos", 1);
	}

	public void irParaActivityConfiguracoes(View view) {
		Intent intentConfiguracoes = new Intent(this, ConfiguracoesActivity.class);
		intentConfiguracoes.putExtra("pos", cinema);
		startActivity(intentConfiguracoes);
	}

	public void irParaActivityFilme(View view) {
		Intent intentFilme = new Intent(this, FilmeActivity.class);
		startActivity(intentFilme);
	}

	public void irParaActivityIngressos(View view) {
		Intent intentIngressos = new Intent(this, IngressosActivity.class);
		startActivity(intentIngressos);
	}
}