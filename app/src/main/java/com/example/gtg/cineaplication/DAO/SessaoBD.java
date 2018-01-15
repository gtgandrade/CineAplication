package com.example.gtg.cineaplication.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.gtg.cineaplication.modelo.Filme;
import com.example.gtg.cineaplication.modelo.Horario;
import com.example.gtg.cineaplication.modelo.Sessao;
import java.util.ArrayList;
import java.util.List;

/** Created by gutemberg on 27/11/17. */
public class SessaoBD extends SQLiteOpenHelper {
	private static final String DB_NAME = "bdcinema.db";
	private static final int DB_VERSION = 1;
	private String comandosql;
	private Context context;
	private FilmeDAO filmeBD;
	private HorarioDAO horDAO;

	public SessaoBD(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
		horDAO = new HorarioDAO(context);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	public Sessao findSessaBy(Filme filme, Horario horario) {
		SQLiteDatabase sessaoBD = getReadableDatabase();
		Sessao sessao;
		//		horDAO = new HorarioDAO(context);
		try {
			String condicao = "filme_idfilme= '" + filme.getIdfilme() + "' AND " + "horario_idhorario = '"
				+ horario.getIdhorario() + "'";
			Cursor cursor = sessaoBD.query("sessao", null, condicao, null,
				null, null, null, null);
			sessao = new Sessao();
			if (cursor.moveToFirst()) {
				sessao.setIdsessao(cursor.getInt(0));
				sessao.setSala(cursor.getInt(1));
				sessao.setFilme(filme);
				sessao.setHorario(horario);
			}
			return sessao;
		} finally {
			sessaoBD.close();
		}
	}

	Sessao findSessaBy(int idsessao) {
		SQLiteDatabase sessaoBD = getReadableDatabase();
		Sessao sessao;
		//		horDAO = new HorarioDAO(context);
		filmeBD = new FilmeDAO(this.context);
		try {
			String condicao = "idsessao = '" + idsessao + "'";
			Cursor cursor = sessaoBD.query("sessao", null, condicao, null,
				null, null, null, null);
			sessao = new Sessao();
			if (cursor.moveToFirst()) {
				sessao.setIdsessao(cursor.getInt(0));
				sessao.setSala(cursor.getInt(1));
				sessao.setFilme(filmeBD.procurarPorId(cursor.getInt(2)));
				//				sessao.setHorario(horarioBD.findHorarioBy(cursor.getInt(3)));
				sessao.setHorario(horDAO.busca(cursor.getInt(3)));
			}
			return sessao;
		} finally {
			sessaoBD.close();
		}
	}

	public List<Sessao> findSessoes(Filme filme) {
		SQLiteDatabase sessaoBD = getReadableDatabase();
		List<Sessao> sessoes = new ArrayList<>();
		//		horDAO = new HorarioDAO(context);
		try {
			String condicao = "filme_idfilme= '" + filme.getIdfilme() + "'";
			Cursor cursor = sessaoBD.query("sessao", null, condicao, null,
				null, null, null, null);
			if (cursor.moveToFirst()) {
				do {
					Sessao sessao = new Sessao();
					sessao.setIdsessao(cursor.getInt(0));
					sessao.setSala(cursor.getInt(1));
					sessao.setFilme(filme);
					Horario h = horDAO.busca(cursor.getInt(3));

					sessao.setHorario(h);
					sessoes.add(sessao);
				} while (cursor.moveToNext());
			}
			return sessoes;
		} finally {
			sessaoBD.close();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int i, int i1) {

	}
}
