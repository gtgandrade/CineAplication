package com.example.gtg.cineaplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtg.cineaplication.DAO.SessaoDAO;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Filme;
import com.example.gtg.cineaplication.modelo.Horario;
import com.example.gtg.cineaplication.modelo.Sessao;

public class CadastroSessaoActivity extends AppCompatActivity {

    private EditText etSalaSessao;
    private CheckBox checkVipSessao;
    private TextView tvIdSessao;
    private TextView tvHorarioSessao;
    private TextView tvIdHorarioSessao;
    private TextView tvLabelHorarioSessao;
    private Button btnRemove;
    private Button btnHorariosSessao;
    private boolean editmode;
    private int filmeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_sessao);

        btnRemove = (Button) findViewById(R.id.btRemoveSessao);
        btnHorariosSessao = (Button) findViewById(R.id.btHorariosSessao);
        etSalaSessao = (EditText) findViewById(R.id.etSalaSessao);
        checkVipSessao = (CheckBox) findViewById(R.id.checkVipSessao);
        tvIdSessao = (TextView) findViewById(R.id.tvIdSessao);
        tvHorarioSessao = (TextView) findViewById(R.id.tvHorarioSessao);
        tvIdHorarioSessao = (TextView) findViewById(R.id.tvIdHorarioSessao);
        tvLabelHorarioSessao = (TextView) findViewById(R.id.tvLabelHorarioSessao);

        this.filmeid = getIntent().getExtras().getInt("filmeid");

        if ( getIntent().hasExtra("edit") )
        {
            int sessaoid = getIntent().getExtras().getInt("sessaoid");
            SessaoDAO sessaobd = new SessaoDAO(this);
            Sessao sessao = sessaobd.findSessaBy(sessaoid);

            editmode = true;
            btnRemove.setTag(sessao);
            tvIdSessao.setText(String.valueOf(sessaoid));
            etSalaSessao.setText(String.valueOf(sessao.getSala()));
            checkVipSessao.setChecked(sessao.isVip());

            tvLabelHorarioSessao.setVisibility(View.VISIBLE);
            if ( sessao.getHorario().getIdhorario() == 0 )
            {
                tvIdHorarioSessao.setText("1");
                tvHorarioSessao.setText("--:--");
            }
            else
            {
                tvIdHorarioSessao.setText(String.valueOf(sessao.getHorario().getIdhorario()));
                tvHorarioSessao.setText(String.valueOf(sessao.getHorario().getDescricao()));
            }
            tvHorarioSessao.setVisibility(View.VISIBLE);
            btnHorariosSessao.setVisibility(View.VISIBLE);
            btnRemove.setVisibility(View.VISIBLE);
        }
        else
        {
            editmode = false;
            tvLabelHorarioSessao.setVisibility(View.INVISIBLE);
            tvHorarioSessao.setVisibility(View.INVISIBLE);
            btnHorariosSessao.setVisibility(View.INVISIBLE);
            btnRemove.setVisibility(View.INVISIBLE);
        }
    }

    public void salvarSessao(View view)
    {
        if ( editmode )
        {
            if ( etSalaSessao.getText().equals("") || tvIdHorarioSessao.getText().equals("0") )
            {
                Toast.makeText(this,"Informe a sala e o horário!",Toast.LENGTH_SHORT).show();
                return;
            }

            int sala = Integer.parseInt( etSalaSessao.getText().toString() );
            SessaoDAO sessaoBD = new SessaoDAO(this);

            Sessao sessao = new Sessao();
            sessao.setIdsessao(Integer.parseInt(tvIdSessao.getText().toString()));
            sessao.setSala(Integer.parseInt(etSalaSessao.getText().toString()));
            sessao.setVip(checkVipSessao.isChecked());

            Filme filme = new Filme();
            filme.setIdfilme(filmeid);
            sessao.setFilme(filme);

            Horario horario = new Horario();
            horario.setIdhorario(Integer.parseInt(tvIdHorarioSessao.getText().toString()));
            sessao.setHorario(horario);

            sessaoBD.save(sessao);
        }
        else
        {
            if ( etSalaSessao.getText().toString().equals("") )
            {
                Toast.makeText(this,"Informe a sala!",Toast.LENGTH_SHORT).show();
                return;
            }

            int sala = Integer.parseInt( etSalaSessao.getText().toString() );
            SessaoDAO sessaoBD = new SessaoDAO(this);

            Sessao sessao = new Sessao();
            sessao.setSala(Integer.parseInt(etSalaSessao.getText().toString()));
            sessao.setVip(checkVipSessao.isChecked());

            Filme filme = new Filme();
            filme.setIdfilme(filmeid);
            sessao.setFilme(filme);

            sessaoBD.save(sessao);
        }

        Bundle parametros = new Bundle();
        parametros.putInt("filmeid",filmeid);
        Intent intent = new Intent(this, ListaSessaoActivity.class);
        intent.putExtras(parametros);
        navigateUpTo(intent);
    }

    public void removerSessao(View view)
    {
        Sessao sessao = (Sessao) view.getTag();
        SessaoDAO sessaoBD = new SessaoDAO(this);
        sessaoBD.remove(sessao.getIdsessao());

        Bundle parametros = new Bundle();
        parametros.putInt("filmeid",filmeid);
        Intent intent = new Intent(this, ListaSessaoActivity.class);
        intent.putExtras(parametros);
        navigateUpTo(intent);
    }
    public void listaHorarios(View view){
        Intent intentListaHorarios = new Intent(this, ListaHorariosActivity.class);
        startActivityForResult(intentListaHorarios, 1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 ) {
            if(resultCode == Activity.RESULT_OK){
                Bundle resposta = data.getExtras();
                tvIdHorarioSessao.setText(String.valueOf(resposta.getInt("idhorario")));
                tvHorarioSessao.setText(resposta.getString("descricaohorario"));
            }
        }
    }
}
