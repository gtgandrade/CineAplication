package com.example.gtg.cineaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gtg.cineaplication.DAO.HorarioDAO;
import com.example.gtg.cineaplication.adapter.HorarioAdapter;
import com.example.gtg.cineaplication.modelo.Horario;

public class ListaHorariosActivity extends AppCompatActivity {
    private RecyclerView rvHorarios;
    private HorarioDAO horarioDAO;
    private EditText edtHorario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horarios);
        rvHorarios = (RecyclerView) findViewById(R.id.lista_horarios_rvHorarios);
        edtHorario = (EditText) findViewById(R.id.lista_horarios_edtHorario);
        horarioDAO = new HorarioDAO(this);
        HorarioAdapter horarioAdapter = new HorarioAdapter(this, horarioDAO.procurarTodos());
        rvHorarios.setLayoutManager(new LinearLayoutManager(this));
        rvHorarios.setAdapter(horarioAdapter);
    }

    public void salvarHorario(View view){
        Horario horario = horarioDAO.procurarPorDescricao(edtHorario.getText().toString());
        if(horario.getDescricao() == null) {
            horario.setDescricao(edtHorario.getText().toString());
            horarioDAO.salvar(horario);
            Toast.makeText(this,"Horário cadastrado.",Toast.LENGTH_SHORT).show();
        }
   }
}
