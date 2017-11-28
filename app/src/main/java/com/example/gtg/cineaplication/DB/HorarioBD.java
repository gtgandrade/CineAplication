package com.example.gtg.cineaplication.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gtg.cineaplication.modelo.Horario;

/**
 * Created by gutemberg on 27/11/17.
 */

public class HorarioBD extends SQLiteOpenHelper {
    private static final String DB_NAME = "bdcinema.db";
    private static final int DB_VERSION = 1;
    private String comandosql;
    public HorarioBD(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
    public Horario findHorarioBy(int idHorario){
        SQLiteDatabase cineBD = getReadableDatabase();
        Horario horario = new Horario();
        try{
            String condicao = "idhorario = '"+idHorario+"'";
            Cursor cursor = cineBD.query("horario",null, condicao, null, null, null, null);
            if(cursor.moveToFirst()){
                horario.setIdhorario(cursor.getInt(0));
                horario.setDescricao(cursor.getString(1));
            }
            return  horario;
        }finally {
            cineBD.close();
        }
    }
    public Horario findHorarioBy(String descricao){
        SQLiteDatabase cineBD = getReadableDatabase();
        Horario horario = new Horario();
        try{
            String condicao = "descricao = '"+descricao+"'";
            Cursor cursor = cineBD.query("horario",null, condicao, null, null, null, null);
            if(cursor.moveToFirst()){
                horario.setIdhorario(cursor.getInt(0));
                horario.setDescricao(cursor.getString(1));
            }
            return  horario;
        }finally {
            cineBD.close();
        }
    }
}
