package com.example.gtg.cineaplication.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gtg.cineaplication.modelo.Filme;
import com.example.gtg.cineaplication.modelo.Sessao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gutemberg on 27/11/17.
 */

public class SessaoBD extends SQLiteOpenHelper {
    private static final String DB_NAME = "bdcinema.db";
    private static final int DB_VERSION = 1;
    private String comandosql;
    private HorarioBD horarioBD;
    public SessaoBD(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        horarioBD = new HorarioBD(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        comandosql = "CREATE TABLE sessao (idsessao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, sala INTEGER, filme_idfilme INTEGER REFERENCES filme (idfilme) NOT NULL, horario_idhorario INTEGER REFERENCES horario (idhorario) NOT NULL)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (1, 10, 1, 1)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (2, 10, 1, 5)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (3, 10, 1, 9)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (4, 10, 1, 13)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (5, 7, 3, 12)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (6, 6, 4, 7)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (7, 6, 4, 14)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (8, 4, 5, 2)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (9, 4, 5, 4)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (10, 4, 5, 8)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (11, 4, 5, 10)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (12, 2, 6, 3)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (13, 2, 6, 6)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (14, 2, 2, 11)";
        db.execSQL(comandosql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        comandosql = "DROP TABLE IF EXISTS sessao";
        db.execSQL(comandosql);
        onCreate(db);
    }
    public List<Sessao> findSessoes(Filme filme){
        SQLiteDatabase sessaoBD = getReadableDatabase();
        List<Sessao> sessoes = new ArrayList<Sessao>();
        try{
            String condicao = "filme_idfilme = ?";
            String[] argumento = {String.valueOf(filme.getIdfilme())};
            Cursor cursor = sessaoBD.query(false,"sessao",null, condicao, argumento,
                    null, null, null, null);
            if(cursor.moveToFirst()){
                do{
                    Sessao sessao = new Sessao();
                    sessao.setIdsessao(cursor.getInt(0));
                    sessao.setSala(cursor.getInt(1));
                    sessao.setFilme(filme);
                    sessao.setHorario(horarioBD.findHorarioBy(cursor.getInt(3)));
                    sessoes.add(sessao);
                }while(cursor.moveToNext());
            }
            return  sessoes;
        }finally {
            sessaoBD.close();
        }
    }
}
