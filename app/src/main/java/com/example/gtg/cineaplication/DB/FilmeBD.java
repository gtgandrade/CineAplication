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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        comandosql = "DROP TABLE IF EXISTS filme";
        db.execSQL(comandosql);
        onCreate(db);
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
