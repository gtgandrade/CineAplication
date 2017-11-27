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

public class CinemaBD extends SQLiteOpenHelper {
    private static final String DB_NAME = "bdcinema.db";
    private static final int DB_VERSION = 1;
    private String comandosql;

    public CinemaBD(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        comandosql = "CREATE TABLE filme (" +
                "idfilme   INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "codigo    INTEGER," +
                "nome     VARCHAR (50))";

        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme(codigo, nome)"+
                    " VALUES("+ R.drawable.liga_justica+","+
                             "'Liga da Justiça')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme(codigo, nome)"+
                " VALUES("+R.drawable.depois_montanha+","+
                "'Depois Daquela Montanha')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme(codigo, nome)"+
                " VALUES("+R.drawable.pai_dose_dupla2+","+
                "'Pai em Dose Dupla 2')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme(codigo, nome)"+
                " VALUES("+R.drawable.thor_hagnarok+","+
                "'Thor Hagnarok')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme(codigo, nome)"+
                " VALUES("+R.drawable.gosto_discute+","+
                "'Gosto se Discute')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme(codigo, nome)"+
                " VALUES("+R.drawable.dona_flor+","+
                "'Dona Flor')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme(codigo, nome)"+
                " VALUES("+R.drawable.mark_felt+","+
                "'Mark Felt')";
        db.execSQL(comandosql);
        comandosql = "INSERT INTO filme(codigo, nome)"+
                " VALUES("+R.drawable.estrela_belem+","+
                "'A Estrela de Belém')";
        db.execSQL(comandosql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoantiga, int novaversao) {
        comandosql = "DROP TABLE IF EXISTS filme";
        db.execSQL(comandosql);
        onCreate(db);
    }
}
