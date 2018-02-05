package com.example.gtg.cineaplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gtg.cineaplication.conexao.Conexao;
import com.example.gtg.cineaplication.modelo.Cinema;
import com.example.gtg.cineaplication.modelo.Sessao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo_b on 27/01/2018.
 */
public class CinemaDAO {
    private Conexao conexao;
    private Cursor cursor;
    private SessaoDAO sessaoDAO;

    public CinemaDAO(Context ctx) {
        this.conexao = Conexao.getInstance(ctx);
        this.sessaoDAO = new SessaoDAO(ctx);
    }

    public boolean adicionar(Cinema cinema) {
        boolean retorno = false;
        long resultado;

        ContentValues content = new ContentValues();
        content.put("Nome", cinema.getNome());
        content.put("Endereco", cinema.getEndereco());

        resultado = conexao.getDatabase().insert("cinema", null, content);
        if (resultado > 0)
            return true;
        else return false;

    }

    public boolean atualizar(Cinema cinema) {
        String condicaoWhere;
        long resultado;

        ContentValues valores = new ContentValues();
        valores.put("Nome", cinema.getNome());
        valores.put("Endereco", cinema.getEndereco());

        condicaoWhere = "idCinema = '" + cinema.getId() + "' ";
        resultado = conexao.getDatabase().update("cinema", valores, condicaoWhere, null);

        if (resultado > 0) return true;
        else return false;
    }

    public List<Cinema> carregarcinemas(){
        List<Cinema> cinemas = new ArrayList<Cinema>();
        cursor = conexao.getDatabase().query("cinema",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Cinema cinema = new Cinema();
                cinema.setId(cursor.getInt(0));
                cinema.setNome(cursor.getString(1));
                cinema.setEndereco(cursor.getString(2));
                cinema.setLatitude(cursor.getFloat(3));
                cinema.setLongitude(cursor.getFloat(4));

                cinemas.add(cinema);
            }while (cursor.moveToNext());
        }
        return cinemas;
    }

    public Cinema procurarPorId(int idCine) {
        Cinema cinema = new Cinema();
        String whereProcurar = "idCinema= '" + idCine + "' ";

        cursor = conexao.getDatabase().query("cinema", null, whereProcurar, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            cinema.setEndereco(cursor.getString(2));
            cinema.setId(cursor.getInt(0));
            cinema.setNome(cursor.getString(1));
            cinema.setLatitude(cursor.getFloat(3));
            cinema.setLongitude(cursor.getFloat(4));
        }
        return cinema;
    }

    public boolean excluir(Cinema cinema) {
        long resultado;
        String whereExcluir = "idCinema= '" + cinema.getId() + "' ";

        resultado = conexao.getDatabase().delete("cinema", whereExcluir, null);
        if (resultado > 0) return true;
        else return false;
    }

    /**
     * Método para adicionar <b>sessão</b> ao cinema
     *
     * @param id     ID do cinema
     * @param sessao instancia de sessao a salvar
     * @return <b>Verdadeiro</b> se adicionou, <b>False</b> caso contrário
     */
    public boolean adicionarSessao(int id, Sessao sessao) {
        long resultado;

        ContentValues valores = new ContentValues();
        valores.put("_idcinema", id);
        valores.put("_idsessao", sessao.getIdsessao());

        resultado = conexao.getDatabase().insert("cinema_sessao", null, valores);
        return resultado > 0;
    }

    public List<Sessao> carregarSessoes(int id) {
        ContentValues cv = new ContentValues();
        String[] campos = {"_idsessao"};
        String where = "_idcinema = " + id;

        SQLiteDatabase db = conexao.getDatabase();
        cursor = db.query("cinema_sessao", campos, where, null, null, null, null);

        List<Sessao> sessoes = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
            //while (cursor.moveToNext()) {
                final int s = cursor.getInt(0);
                sessoes.add(sessaoDAO.findSessaBy(s));
            }while (cursor.moveToNext());
        }
//        db.close();
        return (sessoes);
    }
}