package com.example.gtg.cineaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtg.cineaplication.DB.HorarioBD;
import com.example.gtg.cineaplication.DB.SessaoBD;
import com.example.gtg.cineaplication.modelo.Filme;
import com.example.gtg.cineaplication.modelo.Horario;
import com.example.gtg.cineaplication.modelo.Sessao;

import java.util.Iterator;
import java.util.List;

public class SessaoActivity extends AppCompatActivity {
    private TextView lblFilmeEscolhido;
    private Spinner spnHorarios;
    private EditText edtQtdInteira;
    private EditText edtQtdMeia;
    private CheckBox chkInteira;
    private CheckBox chkMeia;
    private RadioGroup rgPipocaRefri;
    private int qtdInteira;
    private int qtdMeia;
    private HorarioBD horarioBD;
    private SessaoBD sessaoBD;
    private Filme filme;

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
        rgPipocaRefri = findViewById(R.id.rgPipocaRefrigerante);
        Bundle paramentros = getIntent().getExtras();
        filme = new Filme();
        filme.setIdfilme(paramentros.getInt("idfilme"));
        filme.setCodigo(paramentros.getInt("codigo"));
        filme.setNome(paramentros.getString("nome"));
        lblFilmeEscolhido.setText(filme.getNome());
        sessaoBD = new SessaoBD(this);
        List<Sessao> sessoes = sessaoBD.findSessoes(filme);
        Iterator<Sessao> iteratorSessoes = sessoes.iterator();
        String horarios[] = new String[sessoes.size()+1];
        int contador = 1;
        horarios[0] = "-----";
        while (iteratorSessoes.hasNext()){
            Sessao s = iteratorSessoes.next();
            horarios[contador] = s.getHorario().getDescricao();
            contador++;
        }

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
    public void verificarMaracacaoInteira(View view){
        if(!chkInteira.isChecked())
            edtQtdInteira.setText("0");
        else
            edtQtdInteira.setText("1");
    }
    public void verificarMaracacaoMeia(View view){
        if(!chkMeia.isChecked())
            edtQtdMeia.setText("0");
        else
            edtQtdMeia.setText("1");
    }
    public void irParaTelaResumo(View view){
        if(validaEntradaDeDados()){
            Intent intentResumo = new Intent(this, ResumoActivity.class);
            Bundle parametros = new Bundle();
            parametros.putInt("idfilme", filme.getIdfilme());
            parametros.putString("nome", filme.getNome());
            parametros.putInt("qtdInteira", Integer.parseInt(edtQtdInteira.getText().toString()));
            parametros.putInt("qtdMeia", Integer.parseInt(edtQtdMeia.getText().toString()));
            int selecaoPipocaRefri = rgPipocaRefri.getCheckedRadioButtonId();
            parametros.putInt("pipocaRefri", selecaoPipocaRefri);

            startActivity(intentResumo);
        }else {
            Toast.makeText(this,"Escolha o tipo de ingresso",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validaEntradaDeDados(){
        if((chkInteira.isChecked() && !edtQtdInteira.getText().equals("0")) ||
                (chkMeia.isChecked() && !edtQtdMeia.getText().equals("0"))){
         return true;
        }else{
            return false;
        }
    }
}
