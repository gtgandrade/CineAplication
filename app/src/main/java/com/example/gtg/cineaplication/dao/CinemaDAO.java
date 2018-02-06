package com.example.gtg.cineaplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gtg.cineaplication.conexao.Conexao;
import com.example.gtg.cineaplication.modelo.Cinema;
import com.example.gtg.cineaplication.modelo.Sessao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo_b on 27/01/2018.
 */
public class CinemaDAO {
    private Conexao conexao;
    private Cursor cursor;

    public CinemaDAO(Context ctx) {
        this.conexao = Conexao.getInstance(ctx);
    }

    public boolean adicionar(Cinema cinema) {
        boolean retorno = false;
        long resultado;

        ContentValues content = new ContentValues();
        content.put("nome", cinema.getNome());
        content.put("endereco", cinema.getEndereco());
        content.put("latitude", cinema.getLatitude());
        content.put("longitude", cinema.getLongitude());

        resultado = conexao.getDatabase().insert("cinema", null, content);
        if (resultado > 0)
            return true;
        else return false;
    }

    public boolean atualizar(Cinema cinema) {
        String condicaoWhere;
        long resultado;

        ContentValues content = new ContentValues();
        content.put("nome", cinema.getNome());
        content.put("endereco", cinema.getEndereco());
        content.put("latitude", cinema.getLatitude());
        content.put("longitude", cinema.getLongitude());

        condicaoWhere = "idcinema = '" + cinema.getId() + "' ";
        resultado = conexao.getDatabase().update("cinema", content, condicaoWhere, null);

        if (resultado > 0)
            return true;
        else
            return false;
    }

    public List<Cinema> carregarcinemas(){
        List<Cinema> cinemas = new ArrayList<Cinema>();
        cursor = conexao.getDatabase().query("cinema",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Cinema cinema = new Cinema();
                cinema.setId(cursor.getInt(0));
                cinema.setNome(cursor.getString(1));
                cinema.setEndereco(cursor.getString(2));
                cinema.setLatitude(cursor.getFloat(3));
                cinema.setLongitude(cursor.getFloat(4));

                cinemas.add(cinema);
            }while (cursor.moveToNext());
        }
        return cinemas;
    }

    public Cinema procurarPorId(int idCine) {
        Cinema cinema = new Cinema();
        String whereProcurar = "idcinema= '" + idCine + "' ";

        cursor = conexao.getDatabase().query("cinema", null, whereProcurar, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            cinema.setEndereco(cursor.getString(2));
            cinema.setId(cursor.getInt(0));
            cinema.setNome(cursor.getString(1));
            cinema.setLatitude(cursor.getFloat(3));
            cinema.setLongitude(cursor.getFloat(4));
        }
        return cinema;
    }

    public boolean excluir(int idCine) {
        long resultado;
        String whereExcluir = "idcinema= '" +idCine + "' ";

        resultado = conexao.getDatabase().delete("cinema", whereExcluir, null);
        if (resultado > 0) return true;
        else return false;
    }
}