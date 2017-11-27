package com.example.gtg.cineaplication.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gutemberg on 27/11/17.
 */

public class IngressoBD extends SQLiteOpenHelper {
    private static final String DB_NAME = "bdcinema.db";
    private static final int DB_VERSION = 1;
    private String comandosql;

    public IngressoBD(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        comandosql = "CREATE TABLE ingresso (idingresso INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, precounitario DOUBLE, qtdinteira INTEGER, qtdmeia INTEGER, qtdlanche INTEGER, precolanche DOUBLE, sessao_idsesso INTEGER REFERENCES sessao (idsessao) NOT NULL)";
        db.execSQL(comandosql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        comandosql = "DROP TABLE IF EXISTS ingresso";
        db.execSQL(comandosql);
        onCreate(db);
    }
}
