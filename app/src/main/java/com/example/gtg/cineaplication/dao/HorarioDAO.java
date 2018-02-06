package com.example.gtg.cineaplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gtg.cineaplication.conexao.Conexao;
import com.example.gtg.cineaplication.modelo.Horario;

import java.util.ArrayList;
import java.util.List;

/** Created by gtg on 15/01/18. */
public class HorarioDAO {
    private Conexao conexao;
    private Cursor cursor;

    public HorarioDAO(Context context) {

        this.conexao = Conexao.getInstance(context);
    }

    public boolean salvar(Horario horario){
        long salvou;

        ContentValues valoresCampos = new ContentValues();
        valoresCampos.put("descricao", horario.getDescricao());

        salvou = conexao.getDatabase().insert("horario",null, valoresCampos);

        return salvou > 0;
    }

    public boolean atualizar(Horario horario){
        long atualizou;

        ContentValues valoresCampos = new ContentValues();
        valoresCampos.put("descricao", horario.getDescricao());

        String condicaoWhere = "idhorario = '"+horario.getIdhorario()+"'";
        atualizou = conexao.getDatabase().update("horario", valoresCampos, condicaoWhere, null);

        return atualizou > 0;
    }

    public boolean excluir(Horario horario){
        int excluiu = 0;
        String condicaoWhere = "idhorario = '"+horario.getIdhorario()+"'";
        excluiu = conexao.getDatabase().delete("horario", condicaoWhere, null);

        return excluiu > 0;
    }

    public Horario procurarPorId(int idhorario){
        Horario horario = new Horario();
        String condicaoWhere = "idhorario = '"+idhorario+"'";
        cursor = conexao.getDatabase().query("horario",null, condicaoWhere,null,
                null, null, null, null);
        if(cursor.moveToFirst()){
            horario.setIdhorario(cursor.getInt(0));
            horario.setDescricao(cursor.getString(1));
        }

        return  horario;
    }

    public Horario procurarPorDescricao(String descricao){
        Horario horario = null;
        String condicaoWhere = "descricao = '"+descricao+"'";
        cursor = conexao.getDatabase().query("horario",null, condicaoWhere,null,
                null, null, null, null);
        if(cursor.moveToFirst()){
            horario = new Horario();
            horario.setIdhorario(cursor.getInt(0));
            horario.setDescricao(cursor.getString(1));
        }

        return  horario;
    }

    public List<Horario> procurarTodos(){
        List<Horario> horarios = new ArrayList<Horario>();
        cursor = conexao.getDatabase().query(false,"horario",null,null,null,
                null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                Horario horario = new Horario();
                horario.setIdhorario(cursor.getInt(0));
                horario.setDescricao(cursor.getString(1));

                horarios.add(horario);

            }while(cursor.moveToNext());
        }
        return  horarios;
    }
}
