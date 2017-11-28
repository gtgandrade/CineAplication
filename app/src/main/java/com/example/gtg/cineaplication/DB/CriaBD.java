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
 * Created by gutemberg on 26/11/17.
 */

public class CriaBD extends SQLiteOpenHelper {
    private static final String DB_NAME = "bdcinema.db";
    private static final int DB_VERSION = 1;
    private String comandosql;

    public CriaBD(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        comandosql = "CREATE TABLE filme (idfilme   INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL,codigo    INTEGER,nome     VARCHAR (50))";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme (idfilme, codigo, nome) VALUES (1, "+ R.drawable.liga_justica+", 'Liga da Justiça')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme (idfilme, codigo, nome) VALUES (2, "+ R.drawable.depois_montanha+", 'Depois Daquela Montanha')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme (idfilme, codigo, nome) VALUES (3, "+ R.drawable.pai_dose_dupla2+", 'Pai em Dose Dupla 2')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme (idfilme, codigo, nome) VALUES (4, "+ R.drawable.thor_hagnarok+", 'Thor Hagnarok')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme (idfilme, codigo, nome) VALUES (5, "+ R.drawable.gosto_discute+", 'Gosto se Discute')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme (idfilme, codigo, nome) VALUES (6, "+ R.drawable.dona_flor+", 'Dona Flor')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme (idfilme, codigo, nome) VALUES (7, "+ R.drawable.estrela_belem+", 'A Estrela de Belém')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme (idfilme, codigo, nome) VALUES (8, "+ R.drawable.mark_felt+", 'Mark Felt')";
        db.execSQL(comandosql);

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
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (15, 2, 8, 9)";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO sessao (idsessao, sala, filme_idfilme, horario_idhorario) VALUES (16, 2, 7, 4)";
        db.execSQL(comandosql);

        comandosql = "CREATE TABLE ingresso (idingresso INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, precounitario DOUBLE, qtdinteira INTEGER, qtdmeia INTEGER, qtdlanche INTEGER, precolanche DOUBLE, sessao_idsesso INTEGER REFERENCES sessao (idsessao) NOT NULL)";
        db.execSQL(comandosql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoantiga, int novaversao) {
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
