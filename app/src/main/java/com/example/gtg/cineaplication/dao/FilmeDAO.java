package com.example.gtg.cineaplication.dao;

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
        long salvou;

        ContentValues valoresCampos = new ContentValues();
        valoresCampos.put("nome", filme.getNome());
        valoresCampos.put("cartaz", filme.getCartaz());
        valoresCampos.put("pais", filme.getPais());
        valoresCampos.put("versao", filme.getVersao());
        valoresCampos.put("duracao", filme.getDuracao());
        valoresCampos.put("habilitado", filme.getHabilitado());
        valoresCampos.put("estreia", filme.getEstreia());
        salvou = conexao.getDatabase().insert("filme",null, valoresCampos);

        return salvou > 0;
    }

    public boolean atualizar(Filme filme){
        long atualizou;

        ContentValues valoresCampos = new ContentValues();
        valoresCampos.put("nome", filme.getNome());
        valoresCampos.put("cartaz", filme.getCartaz());
        valoresCampos.put("nome", filme.getNome());
        valoresCampos.put("pais", filme.getPais());
        valoresCampos.put("versao", filme.getVersao());
        valoresCampos.put("duracao", filme.getDuracao());
        valoresCampos.put("habilitado", filme.getHabilitado());
        valoresCampos.put("estreia", filme.getEstreia());

        String condicaoWhere = "idfilme = '"+filme.getIdfilme()+"'";
        atualizou = conexao.getDatabase().update("filme", valoresCampos, condicaoWhere, null);

        return atualizou > 0;
    }

    public boolean excluir(Filme filme){
        int excluiu = 0;

        String condicaoWhere = "idfilme = '"+filme.getIdfilme()+"'";
        excluiu = conexao.getDatabase().delete("filme", condicaoWhere, null);

        return excluiu > 0;
    }

    public Filme procurarPorId(int idfilme){
        Filme filme = new Filme();

        String condicaoWhere = "idfilme = '"+idfilme+"'";
        cursor = conexao.getDatabase().query("filme",null, condicaoWhere,null,
                    null, null, null, null);
        if(cursor.moveToFirst()){
            filme.setIdfilme(cursor.getInt(0));
            filme.setNome(cursor.getString(1));
            filme.setCartaz(cursor.getString(2));
            filme.setPais(cursor.getString(3));
            filme.setVersao(cursor.getString(4));
            filme.setDuracao(cursor.getInt(5));
            filme.setHabilitado(cursor.getInt(6));
            filme.setEstreia(cursor.getInt(7));
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
                filme.setNome(cursor.getString(1));
                filme.setCartaz(cursor.getString(2));
                filme.setPais(cursor.getString(3));
                filme.setVersao(cursor.getString(4));
                filme.setDuracao(cursor.getInt(5));
                filme.setHabilitado(cursor.getInt(6));
                filme.setEstreia(cursor.getInt(7));
                filmes.add(filme);
            }while(cursor.moveToNext());
        }

        return  filmes;
    }

    public List<Filme> procurarHabilitados(){
        List<Filme> filmes = new ArrayList<Filme>();
        String condicaoWhere = "habilitado = 1";

        cursor = conexao.getDatabase().query("filme",null, condicaoWhere,null,
                null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                Filme filme = new Filme();
                filme.setIdfilme(cursor.getInt(0));
                filme.setNome(cursor.getString(1));
                filme.setCartaz(cursor.getString(2));
                filme.setPais(cursor.getString(3));
                filme.setVersao(cursor.getString(4));
                filme.setDuracao(cursor.getInt(5));
                filme.setHabilitado(cursor.getInt(6));
                filme.setEstreia(cursor.getInt(7));
                filmes.add(filme);
            }while(cursor.moveToNext());
        }

        return  filmes;
    }
}
