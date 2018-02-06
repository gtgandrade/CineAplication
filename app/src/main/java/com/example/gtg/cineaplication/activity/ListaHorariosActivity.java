package com.example.gtg.cineaplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gtg.cineaplication.dao.HorarioDAO;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.adapter.HorarioAdapter;
import com.example.gtg.cineaplication.adapter.ItemClickListener;
import com.example.gtg.cineaplication.modelo.Horario;

import java.util.List;

public class ListaHorariosActivity extends AppCompatActivity {
    private RecyclerView rvHorarios;
    private HorarioDAO horarioDAO;
    private EditText edtHorario;
    private Horario horarioSelecionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horarios);
        rvHorarios = (RecyclerView) findViewById(R.id.lista_horarios_rvHorarios);
        edtHorario = (EditText) findViewById(R.id.lista_horarios_edtHorario);
        horarioDAO = new HorarioDAO(this);
        HorarioAdapter horarioAdapter = new HorarioAdapter(this, horarioDAO.procurarTodos());
        horarioAdapter.setOnItemClickListener(new SelecaoHorarioClickListener());
        rvHorarios.setLayoutManager(new LinearLayoutManager(this));
        rvHorarios.setAdapter(horarioAdapter);
        horarioSelecionado = null;
        Bundle parametros = getIntent().getExtras();
    }

    public void salvarHorario(View view){
        Horario horario = horarioDAO.procurarPorDescricao(edtHorario.getText().toString());
        if(horario == null && horarioSelecionado == null) {
            horario = new Horario();
            horario.setDescricao(edtHorario.getText().toString());
            horarioDAO.salvar(horario);
            Toast.makeText(this,"Horário cadastrado.",Toast.LENGTH_SHORT).show();
            this.recreate();
        }else{
            horarioSelecionado.setDescricao(edtHorario.getText().toString());
            horarioDAO.atualizar(horarioSelecionado);
            Toast.makeText(this,"Horário atualizado.", Toast.LENGTH_SHORT).show();
            this.recreate();
        }
        horarioSelecionado = null;
   }

   private void horarioSelecionadoNaLista(Horario horario){
       Intent intentCadastroSessao = new Intent();
       Bundle parametros = new Bundle();
       parametros.putInt("idhorario", horario.getIdhorario());
       parametros.putString("descricaohorario", horario.getDescricao());
       intentCadastroSessao.putExtras(parametros);
       setResult(RESULT_OK, intentCadastroSessao);
       finish();
   }
   private void excluirHorario(Horario horario){
       horarioDAO.excluir(horario);
       Toast.makeText(this,"Horário excluído.",Toast.LENGTH_SHORT).show();
       this.recreate();
   }

   private class SelecaoHorarioClickListener implements ItemClickListener{
       List<Horario> horarios = horarioDAO.procurarTodos();
       Horario horario;
       @Override
       public void onItemClick(int position, String action) {
           horario = horarios.get(position);
           if(action.equals("SELECAO"))
               horarioSelecionadoNaLista(horario);
           if(action.equals("EDICAO")) {
               edtHorario.setText(horario.getDescricao());
               horarioSelecionado = horario;
           }
           if(action.equals("EXCLUSAO")) {
               excluirHorario(horario);
           }
       }
   }
}
