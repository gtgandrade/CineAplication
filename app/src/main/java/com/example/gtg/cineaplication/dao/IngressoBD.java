package com.example.gtg.cineaplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gtg.cineaplication.modelo.Ingresso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gutemberg on 27/11/17.
 */

public class IngressoBD extends SQLiteOpenHelper {
    private static final String DB_NAME = "bdcinema.db";
    private static final int DB_VERSION = 1;
    private Context context;
    private String comandosql;
    private SessaoDAO sessaoBD;

    public IngressoBD(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void saveIngresso(Ingresso ingresso){
        SQLiteDatabase ingressoBD = getWritableDatabase();
        try{
            ContentValues valores = new ContentValues();
            valores.put("precounitario", ingresso.getPrecoingresso());
            valores.put("qtdinteira", ingresso.getQtdinteira());
            valores.put("qtdmeia", ingresso.getQtdmeia());
            valores.put("qtdlanche", ingresso.getPipocarefrigerante());
            valores.put("precolanche", ingresso.getPrecopipocarefrigerante());
            valores.put("sessao_idsesso", ingresso.getSessao().getIdsessao());
            ingressoBD.insert("ingresso","", valores);
        }finally {
            ingressoBD.close();
        }
    }

    public List<Ingresso> findAll(){
        SQLiteDatabase cineBD = getReadableDatabase();
        List<Ingresso> ingressos = new ArrayList<Ingresso>();
        sessaoBD = new SessaoDAO(this.context);
        try{
            Cursor cursor = cineBD.query(false,"ingresso",null,null,null,
                    null, null, null, null);
            if(cursor.moveToFirst()){
                do{
                    Ingresso ingresso = new Ingresso();
                    ingresso.setIdingresso(cursor.getInt(0));
                    ingresso.setPrecoingresso(cursor.getDouble(1));
                    ingresso.setQtdinteira(cursor.getInt(2));
                    ingresso.setQtdmeia(cursor.getInt(3));
                    ingresso.setPipocarefrigerante(cursor.getInt(4));
                    ingresso.setPrecopipocarefrigerante(cursor.getDouble(5));
                    ingresso.setSessao(sessaoBD.findSessaBy(cursor.getInt(6)));
                    ingressos.add(ingresso);
                }while(cursor.moveToNext());
            }
            return  ingressos;
        }finally {
            cineBD.close();
        }
    }
}
