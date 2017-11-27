package com.example.gtg.cineaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SessaoActivity extends AppCompatActivity {
    private TextView lblFilmeEscolhido;
    private Spinner spnHorarios;
    private EditText edtQtdInteira;
    private EditText edtQtdMeia;
    private CheckBox chkInteira;
    private CheckBox chkMeia;
    private int qtdInteira;
    private int qtdMeia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessao);
        lblFilmeEscolhido = findViewById(R.id.lblFilmeEscolhido);
        spnHorarios = findViewById(R.id.spnHorarios);
        edtQtdInteira = findViewById(R.id.edtQtdInteira);
        edtQtdMeia = findViewById(R.id.edtQtdMeia);
        chkInteira = findViewById(R.id.chkInteira);
        chkMeia = findViewById(R.id.chkMeia);

        Bundle paramentros = getIntent().getExtras();
        lblFilmeEscolhido.setText(paramentros.getString("nome"));

        String horarios[] = {"15:00", "18:30", "19:10", "22:00"};
        ArrayAdapter<String> adapterSpinnerHorarios = new ArrayAdapter<String>(this,
                                                                              R.layout.support_simple_spinner_dropdown_item,
                                                                              horarios);
        adapterSpinnerHorarios.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnHorarios.setAdapter(adapterSpinnerHorarios);
    }

    public void incrementarInteira(View view){
        qtdInteira = Integer.parseInt(edtQtdInteira.getText().toString());
        edtQtdInteira.setText(String.valueOf(++qtdInteira));
        chkInteira.setChecked(true);
    }
    public void decrementarInteira(View view){
        qtdInteira = Integer.parseInt(edtQtdInteira.getText().toString());
        if(qtdInteira > 0)
            edtQtdInteira.setText(String.valueOf(--qtdInteira));
        if(qtdInteira == 0)
            chkInteira.setChecked(false);
    }
    public void incrementarMeia(View view){
        qtdMeia = Integer.parseInt(edtQtdMeia.getText().toString());
        edtQtdMeia.setText(String.valueOf(++qtdMeia));
        chkMeia.setChecked(true);
    }
    public void decrementarMeia(View view){
        qtdMeia = Integer.parseInt(edtQtdMeia.getText().toString());
        if(qtdMeia > 0)
            edtQtdMeia.setText(String.valueOf(--qtdMeia));
        if(qtdMeia == 0)
            chkMeia.setChecked(false);
    }
}
