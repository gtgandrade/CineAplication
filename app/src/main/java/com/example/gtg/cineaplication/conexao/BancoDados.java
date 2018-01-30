package com.example.gtg.cineaplication.conexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


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
                                         "habilitado INTEGER,"+
                                         "estreia    INTEGER)";
        db.execSQL(comandosql);

        comandosql = "CREATE TABLE horario (idhorario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                           "descricao VARCHAR (5))";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO horario (idhorario, descricao) VALUES (1, '13:00')";
        db.execSQL(comandosql);

        comandosql = "CREATE TABLE sessao (idsessao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                          "sala INTEGER, " +
                                          "filme_idfilme INTEGER REFERENCES filme (idfilme) NOT NULL, " +
                                          "horario_idhorario INTEGER," +
                                           "vip INTEGER)";
        db.execSQL(comandosql);

        comandosql = "CREATE TABLE ingresso (idingresso INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                            "precounitario DOUBLE, " +
                                            "qtdinteira INTEGER, " +
                                            "qtdmeia INTEGER, " +
                                            "qtdlanche INTEGER, " +
                                            "precolanche DOUBLE, " +
                                            "sessao_idsesso INTEGER REFERENCES sessao (idsessao) NOT NULL)";
        db.execSQL(comandosql);

        comandosql = "CREATE TABLE cinema (idcinema INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                            "nome VARCHAR(30),"+
                                            "endereco VARCHAR(50)," +
                                            "latitude DOUBLE," +
                                             "longitude DOUBLE";
        db.execSQL(comandosql);

        comandosql = "CREATE TABLE cinema_sessao (" +
            "_idcinema INTEGER REFERENCES  cinema (idcinema) NOT NULL," +
            "_idsessao INTEGER REFERENCES sessao (idsessao) NOT NULL";

        db.execSQL(comandosql);

        comandosql = "CREATE TABLE cinema_ingresso (" +
                "_idcinema INTEGER REFERENCES cinema (idcinema) NOT NULL," +
                "_idingresso INTEGER REFERENCES ingresso (idingresso) NOT NULL";

        db.execSQL(comandosql);

        comandosql = ("INSERT INTO cinema(nome,endereco,latitude,longitude) VALUES('Cinépolis',Bairro Jaracaty,-2.510609, -44.285094)");

        db.execSQL(comandosql);

        comandosql = ("INSERT INTO cinema(nome,endereco,latitude,longitude) VALUES('Rio Anil Shopping','Av.São Luís Rei de França,Turu',-2.533553, -44.224921)");
        db.execSQL(comandosql);

        comandosql = ("INSERT INTO cinema(nome,endereco,latitude,longitude) VALUES('Shopping da Ilha','Avenida Daniel de la Touche - Cohama',-2.527092, -44.255182)");
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
        comandosql = "DROP TABLE IF EXISTS cinema";
        db.execSQL(comandosql);
        onCreate(db);
    }
}