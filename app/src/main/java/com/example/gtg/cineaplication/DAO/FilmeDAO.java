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

        ContentValues campos = new ContentValues();
        campos.put("nome", filme.getCodigo());
        campos.put("codigo", filme.getCodigo());
        campos.put("nome", filme.getNome());
        campos.put("pais", filme.getPais());
        campos.put("versao", filme.getVersao());
        campos.put("duracao", filme.getDuracao());
        campos.put("habilitado", filme.getHabilitado());
        salvo = conexao.getDatabase().insert("filme",null, campos);

        return salvo > 0? true : false;
    }

    public boolean atualizar(Filme filme){
        long salvo;

        ContentValues valores = new ContentValues();
        valores.put("nome", filme.getCodigo());
        valores.put("codigo", filme.getCodigo());
        valores.put("nome", filme.getNome());
        valores.put("pais", filme.getPais());
        valores.put("versao", filme.getVersao());
        valores.put("duracao", filme.getDuracao());
        valores.put("habilitado", filme.getHabilitado());

        String condicao = "idfilme = '"+filme.getIdfilme()+"'";
        salvo = conexao.getDatabase().update("filme", valores, condicao, null);

        return salvo > 0? true : false;
    }

    public boolean excluir(Filme filme){
        int excluiu = 0;
        String condicao = "idfilme = '"+filme.getIdfilme()+"'";
        excluiu = conexao.getDatabase().delete("filme", condicao, null);

        return excluiu > 0? true: false;
    }

    public Filme procurarPorId(int idfilme){
        Filme filme = new Filme();
        String condicao = "idfilme = '"+idfilme+"'";
        cursor = conexao.getDatabase().query("filme",null, condicao,null,
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
