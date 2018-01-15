package com.example.gtg.cineaplication.conexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/** Created by gtg on 31/12/17. */
public class Conexao {
	private static Conexao conexao;
	private BancoDados bancoDados;

	public static Conexao getInstance(Context ctx) {
		if (conexao == null)
			conexao = new Conexao(ctx);
		return conexao;
	}

	private Conexao(Context ctx) {
		bancoDados = new BancoDados(ctx);
	}

	public SQLiteDatabase getWritable() {
		return (bancoDados.getWritableDatabase());
	}

	public SQLiteDatabase getReadable() {
		return (bancoDados.getReadableDatabase());
	}
}