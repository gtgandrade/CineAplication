package com.example.gtg.cineaplication.conexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/** Created by gtg on 31/12/17. */
public class Conexao {
    private static Conexao conexao;
    private SQLiteDatabase db;

    public Conexao(Context ctx){
        BancoDados bancoDados = new BancoDados(ctx);
        this.db = bancoDados.getWritableDatabase();
    }

    public static Conexao getInstance(Context ctx){
         if (conexao == null)
            conexao = new Conexao(ctx);
        return conexao;
    }
    
    public SQLiteDatabase getDatabase(){
        return this.db;
    }
}