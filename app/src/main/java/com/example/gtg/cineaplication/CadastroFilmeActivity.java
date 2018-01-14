package com.example.gtg.cineaplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class CadastroFilmeActivity extends AppCompatActivity {
    private ImageView imgCartazFilme;
    private EditText edtNome;
    private EditText edtPais;
    private Spinner spnVersao;
    private EditText edtDuracao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_filme);

    }
}
