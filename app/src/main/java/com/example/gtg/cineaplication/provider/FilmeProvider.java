package com.example.gtg.cineaplication.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.gtg.cineaplication.conexao.Conexao;
import com.example.gtg.cineaplication.dao.FilmeDAO;
import com.example.gtg.cineaplication.modelo.Filme;

import java.util.HashMap;


public class FilmeProvider extends ContentProvider{
    private FilmeDAO filmeDAO;
    private UriMatcher uriFilme;
    private Conexao conexao;
    private static HashMap<String, String> colunas;
    private static final int FILMES = 1;
    private static final int FILME_ID = 2;
    private static final String AUTHORITY = "com.example.gtg.cineaplication";

    private static String getAuthority() {
        return AUTHORITY;
    }

    @Override
    public boolean onCreate() {
        uriFilme = new UriMatcher(UriMatcher.NO_MATCH);
        uriFilme.addURI(getAuthority(), "filme", FILMES);
        uriFilme.addURI(getAuthority(), "filme/#", FILME_ID);
        filmeDAO = new FilmeDAO(getContext());
        conexao = Conexao.getInstance(getContext());
        colunas = new HashMap<String, String>();
        colunas.put(Filmes._ID, Filmes._ID);
        colunas.put(Filmes.NOME, Filmes.NOME);
        colunas.put(Filmes.CARTAZ, Filmes.CARTAZ);
        colunas.put(Filmes.PAIS, Filmes.PAIS);
        colunas.put(Filmes.VERSAO, Filmes.VERSAO);
        colunas.put(Filmes.DURACAO, Filmes.DURACAO);
        colunas.put(Filmes.HABILITADO, Filmes.HABILITADO);
        colunas.put(Filmes.ESTREIA, Filmes.ESTREIA);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor = null;
        switch (uriFilme.match(uri)){
            case FILMES:cursor = conexao.getDatabase().query(false,"filme",null,null,null,
                    null, null, null, null);

                break;
            case FILME_ID:
                int idfilme = Integer.parseInt(uri.getPathSegments().get(1));
                String condicaoWhere = "idfilme = '"+idfilme+"'";
                cursor = conexao.getDatabase().query("filme",null, condicaoWhere,null,
                        null, null, null, null);

                break;
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriFilme.match(uri)){
            case FILMES:
                return Filmes.CONTENT_TYPE;
            case FILME_ID:
                return Filmes.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("URI Inválida: "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if(uriFilme.match(uri)!= FILMES){
            throw new IllegalArgumentException("URI Inválida: "+uri);
        }
        ContentValues valores = null;
        if(contentValues != null){
            valores = new ContentValues(contentValues);
        }
        Filme filme = new Filme();
        filme.setNome(valores.getAsString("nome"));
        filme.setCartaz(valores.getAsString("cartaz"));
        filme.setPais(valores.getAsString("pais"));
        filme.setVersao(valores.getAsString("versao"));
        filme.setDuracao(Integer.parseInt(valores.getAsString("duracao")));
        filme.setHabilitado(Integer.parseInt(valores.getAsString("habilitado")));
        filme.setEstreia(Integer.parseInt(valores.getAsString("estreia")));

        long id = filmeDAO.salvar(filme);

        if(id > 0){
            Uri uriFilme = Filmes.getUriID(id);
            getContext().getContentResolver().notifyChange(uriFilme, null);
            return uriFilme;
        }
        throw new SQLException("Falha durante inserção de dados");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        if(uriFilme.match(uri)!= FILMES){
            throw new IllegalArgumentException("URI Inválida: "+uri);
        }
        ContentValues valores = null;
        if(contentValues != null){
            valores = new ContentValues(contentValues);
        }
        Filme filme = new Filme();
        filme.setIdfilme(Integer.parseInt(valores.getAsString("idfilme")));
        filme.setNome(valores.getAsString("nome"));
        filme.setCartaz(valores.getAsString("cartaz"));
        filme.setPais(valores.getAsString("pais"));
        filme.setVersao(valores.getAsString("versao"));
        filme.setDuracao(Integer.parseInt(valores.getAsString("duracao")));
        filme.setHabilitado(Integer.parseInt(valores.getAsString("habilitado")));
        filme.setEstreia(Integer.parseInt(valores.getAsString("estreia")));

        int id = filmeDAO.atualizar(filme);

        if(id > 0){
            Uri uriFilme = Filmes.getUriID(id);
            getContext().getContentResolver().notifyChange(uriFilme, null);
            return id;
        }
        throw new SQLException("Falha durante inserção de dados");
    }

    public static final class Filmes implements BaseColumns{
        private Filmes(){};

        public static final Uri CONTENT_URI = Uri.parse("content://"+getAuthority()+"/filme");
        public static String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + getAuthority() + "." + "filme";
        public static String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + getAuthority() + "." + "filme";

        public static final String IDFILME = "idfilme";
        public static final String NOME = "nome";
        public static final String CARTAZ = "cartaz";
        public static final String PAIS = "pais";
        public static final String VERSAO = "versao";
        public static final String DURACAO = "duracao";
        public static final String HABILITADO = "habilitado";
        public static final String ESTREIA = "estreia";

        public static Uri getUriID(long id){
            Uri uriFilme = ContentUris.withAppendedId(Filmes.CONTENT_URI, id);
            return uriFilme;
        }
    }
}
