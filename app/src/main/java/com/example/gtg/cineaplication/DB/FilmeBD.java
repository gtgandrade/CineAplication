package com.example.gtg.cineaplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Filme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gutemberg on 27/11/17.
 */

public class FilmeBD extends SQLiteOpenHelper {
    private static final String DB_NAME = "bdcinema.db";
    private static final int DB_VERSION = 1;
    private String comandosql;

    public FilmeBD(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


    public long saveFilme(Filme filme){
        SQLiteDatabase cineBD = getWritableDatabase();
        try{
            ContentValues valores = new ContentValues();
            valores.put("codigo", filme.getCodigo());
            valores.put("nome", filme.getNome());

            return cineBD.insert("filme","", valores);
        }finally {
            cineBD.close();
        }
    }
    public Filme findFilmeBy(int idfilme){
        SQLiteDatabase cineBD = getReadableDatabase();
        Filme filme = new Filme();
        try{
            String condicao = "idfilme = '"+idfilme+"'";
            Cursor cursor = cineBD.query("filme",null,condicao,null,
                    null, null, null, null);
            if(cursor.moveToFirst()){
                     filme.setIdfilme(cursor.getInt(0));
                    filme.setCodigo(cursor.getInt(1));
                    filme.setNome(cursor.getString(2));
             }
            return  filme;
        }finally {
            cineBD.close();
        }
    }
    public List<Filme> findAll(){
        SQLiteDatabase cineBD = getReadableDatabase();
        List<Filme> filmes = new ArrayList<Filme>();
        try{
            Cursor cursor = cineBD.query(false,"filme",null,null,null,
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
        }finally {
            cineBD.close();
        }
    }
}
