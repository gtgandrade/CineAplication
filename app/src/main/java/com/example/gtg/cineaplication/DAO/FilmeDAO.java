package com.example.gtg.cineaplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gtg.cineaplication.conexao.Conexao;
import com.example.gtg.cineaplication.modelo.Filme;

import java.util.ArrayList;
import java.util.List;


public class FilmeDAO{
    private Conexao conexao;
    private Cursor cursor;

    public FilmeDAO(Context context) {
        this.conexao = Conexao.getInstance(context);
    }

    public boolean salvar(Filme filme){
        long salvo;

        ContentValues valoresCampos = new ContentValues();
        valoresCampos.put("nome", filme.getCodigo());
        valoresCampos.put("codigo", filme.getCodigo());
        valoresCampos.put("nome", filme.getNome());
        valoresCampos.put("pais", filme.getPais());
        valoresCampos.put("versao", filme.getVersao());
        valoresCampos.put("duracao", filme.getDuracao());
        valoresCampos.put("habilitado", filme.getHabilitado());
        salvo = conexao.getDatabase().insert("filme",null, valoresCampos);

        return salvo > 0? true : false;
    }

    public boolean atualizar(Filme filme){
        long salvo;

        ContentValues valoresCampos = new ContentValues();
        valoresCampos.put("nome", filme.getCodigo());
        valoresCampos.put("codigo", filme.getCodigo());
        valoresCampos.put("nome", filme.getNome());
        valoresCampos.put("pais", filme.getPais());
        valoresCampos.put("versao", filme.getVersao());
        valoresCampos.put("duracao", filme.getDuracao());
        valoresCampos.put("habilitado", filme.getHabilitado());

        String condicaoWhere = "idfilme = '"+filme.getIdfilme()+"'";
        salvo = conexao.getDatabase().update("filme", valoresCampos, condicaoWhere, null);

        return salvo > 0? true : false;
    }

    public boolean excluir(Filme filme){
        int excluiu = 0;
        String condicaoWhere = "idfilme = '"+filme.getIdfilme()+"'";
        excluiu = conexao.getDatabase().delete("filme", condicaoWhere, null);

        return excluiu > 0? true: false;
    }

    public Filme procurarPorId(int idfilme){
        Filme filme = new Filme();
        String condicaoWhere = "idfilme = '"+idfilme+"'";
        cursor = conexao.getDatabase().query("filme",null, condicaoWhere,null,
                    null, null, null, null);
        if(cursor.moveToFirst()){
            filme.setIdfilme(cursor.getInt(0));
            filme.setCodigo(cursor.getInt(1));
            filme.setNome(cursor.getString(2));
        }

        return  filme;
    }

    public List<Filme> procurarTodos(){
        List<Filme> filmes = new ArrayList<Filme>();
        cursor = conexao.getDatabase().query(false,"filme",null,null,null,
                    null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                Filme filme = new Filme();
                filme.setIdfilme(cursor.getInt(0));
                filme.setCodigo(cursor.getInt(1));
                filme.setNome(cursor.getString(2));
                filmes.add(filme);
            }while(cursor.moveToNext());
        }

        return  filmes;
    }
}
