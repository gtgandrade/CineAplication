package com.example.gtg.cineaplication.DAO;

import static com.example.gtg.cineaplication.conexao.BancoDados.HR_DESCRICAO;
import static com.example.gtg.cineaplication.conexao.BancoDados.HR_ID;
import static com.example.gtg.cineaplication.conexao.BancoDados.TB_HORARIO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.gtg.cineaplication.conexao.Conexao;
import com.example.gtg.cineaplication.modelo.Horario;

/** Created by MarioH on 14/01/2018. */
public class HorarioDAO {
	private Conexao conexao;

	public HorarioDAO(Context context) {
		this.conexao = Conexao.getInstance(context);
	}

	public boolean adicionar(Horario hr) {
		SQLiteDatabase db = conexao.getWritable();
		ContentValues cv = new ContentValues();

		cv.put(HR_DESCRICAO, hr.getDescricao());

		long result = db.insert(TB_HORARIO, null, cv);
		db.close();

		return (result > 0);
	}

	public boolean alterar(Horario hr) {
		SQLiteDatabase db = conexao.getWritable();
		ContentValues cv = new ContentValues();
		String where = HR_ID + "=" + hr.getIdhorario();

		cv.put(HR_DESCRICAO, hr.getDescricao());

		int result = db.update(TB_HORARIO, cv, where, null);
		db.close();

		return (result > 0);
	}

	public Horario busca(String desc) {
		SQLiteDatabase db = conexao.getReadable();
		Horario h = new Horario();
		String campos[] = {HR_ID, HR_DESCRICAO};
		String where = HR_DESCRICAO + "=" + desc;

		Cursor c = db.query(TB_HORARIO, campos, where, null, null, null, null);

		try {
			if (c.moveToFirst())
				h = new Horario(c.getInt(0), c.getString(1));

			return h;

		} finally {
			db.close();
		}
	}

	// retorna Cursor porque pode ser jogado diretamente no adapter do RecyclerView
	public Cursor consulta() {
		SQLiteDatabase db = conexao.getReadable();
		Cursor c;
		String campos[] = {HR_ID, HR_DESCRICAO};

		c = db.query(TB_HORARIO, campos, null, null, null, null, null);

		if (c != null)
			c.moveToFirst();

		db.close();
		return (c);
	}

	public boolean excluir(int id) {
		SQLiteDatabase db = conexao.getReadable();
		String where = HR_ID + "=" + id;

		int result = db.delete(TB_HORARIO, where, null);
		return (result > 0);
	}

	@SuppressLint("Recycle")
	Horario busca(int id) {
		SQLiteDatabase db = conexao.getReadable();
		Horario h = new Horario();
		String campos[] = {HR_ID, HR_DESCRICAO};
		String where = HR_ID + "=" + id;

		Cursor c = db.query(TB_HORARIO, campos, where, null, null, null, null);

		try {
			if (c.moveToFirst())
				h = new Horario(c.getInt(0), c.getString(1));

			return h;

		} finally {
			db.close();
		}
	}
}