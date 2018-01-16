package com.example.gtg.cineaplication.conexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gtg on 31/12/17.
 */

public class BancoDados extends SQLiteOpenHelper {
    private static final String DB_NAME = "bdcinema.db";
    private static final int DB_VERSION = 1;
    private String comandosql;

    public BancoDados(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        comandosql = "CREATE TABLE filme (idfilme    INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL," +
                                         "nome       VARCHAR (50),"+
                                         "cartaz     VARCHAR (50),"+
                                         "pais       VARCHAR (30),"+
                                         "versao     VARCHAR (20),"+
                                         "duracao    INTEGER,"+
                                         "habilitado INTEGER)";
        db.execSQL(comandosql);

        comandosql = "CREATE TABLE horario (idhorario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                           "descricao VARCHAR (5))";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (1, '13:00')";
        db.execSQL(comandosql);

        comandosql = "CREATE TABLE sessao (idsessao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                          "sala INTEGER, " +
                                          "filme_idfilme INTEGER REFERENCES filme (idfilme) NOT NULL, " +
                                          "horario_idhorario INTEGER)";
        db.execSQL(comandosql);

        comandosql = "CREATE TABLE ingresso (idingresso INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                            "precounitario DOUBLE, " +
                                            "qtdinteira INTEGER, " +
                                            "qtdmeia INTEGER, " +
                                            "qtdlanche INTEGER, " +
                                            "precolanche DOUBLE, " +
                                            "sessao_idsesso INTEGER REFERENCES sessao (idsessao) NOT NULL)";
        db.execSQL(comandosql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        comandosql = "DROP TABLE IF EXISTS ingresso";
        db.execSQL(comandosql);
        comandosql = "DROP TABLE IF EXISTS sessao";
        db.execSQL(comandosql);
        comandosql = "DROP TABLE IF EXISTS filme";
        db.execSQL(comandosql);
        comandosql = "DROP TABLE IF EXISTS horario";
        db.execSQL(comandosql);
        onCreate(db);
    }
}
