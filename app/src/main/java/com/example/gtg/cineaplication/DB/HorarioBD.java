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
        comandosql = "CREATE TABLE horario (idhorario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, descricao VARCHAR (5))";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (1, '13:00')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (2, '13:15')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (3, '13:45')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (4, '15:45')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (5, '16:00')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (6, '16:15')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (7, '16:30')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (8, '18:15')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (9, '19:00')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (10, '20:15')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (11, '21:30')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (12, '21:45')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (13, '22:00')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (14, '22:15')";
        db.execSQL(comandosql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        comandosql = "DROP TABLE IF EXISTS horario";
        db.execSQL(comandosql);
        onCreate(db);
    }
    public Horario findHorarioBy(int idHorario){
        SQLiteDatabase cineBD = getReadableDatabase();
        Horario horario = new Horario();
        try{
            String condicao = "idhorario = ?";
            String[] argumento = {String.valueOf(idHorario)};
            Cursor cursor = cineBD.query(false,"horario",null, condicao, argumento,
                    null, null, null, null);
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
