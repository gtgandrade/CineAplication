package com.example.gtg.cineaplication.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gtg.cineaplication.DAO.CinemaDAO;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Cinema;

import com.example.gtg.cineaplication.adapter.CinemaAdapter;

import java.util.List;

/**
 * Created by leo_b on 29/01/2018.
 */

public class ListarCinemasActivity extends AppCompatActivity {
    private RecyclerView rclListar;
    private CinemaDAO cinemadao;
    private Cinema cinema;
    private List<Cinema> cinemas;
    private ImageView imageView;
    private Uri uriImagemPadrao;
    private Uri uriImagemSelecionada;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cinemas);

        rclListar = (RecyclerView) findViewById(R.id.rcl_listarCinema);
        imageView = (ImageView) findViewById(R.id.imageView);

        uriImagemPadrao = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(R.drawable.icone_adic_imagem)
                + '/' + getResources().getResourceTypeName(R.drawable.icone_adic_imagem)
                + '/' + getResources().getResourceEntryName(R.drawable.icone_adic_imagem));

        cinema = new Cinema();

        cinema.setCartaz(uriImagemSelecionada != null ? uriImagemSelecionada.toString() : uriImagemPadrao.toString());

        cinemadao = new CinemaDAO(getBaseContext());

        cinemadao.adicionar(cinema);

        CinemaAdapter cineAdapter = new CinemaAdapter(this, cinemadao.carregarcinemas());
        rclListar.setAdapter(cineAdapter);

        LinearLayoutManager lmn = new LinearLayoutManager(this);
        rclListar.setLayoutManager(lmn);

        //public void salvarFilme(View view){
        //    if(validarCampos()) {
        /*
            filme.setCartaz(uriImagemSelecionada != null ? uriImagemSelecionada.toString() : uriImagemPadrao.toString());
            filme.setNome(edtNome.getText().toString());
            filme.setPais(edtPais.getText().toString());
            filme.setVersao(spnVersao.getSelectedItem().toString());
            filme.setDuracao(Integer.parseInt(edtDuracao.getText().toString()));
            int ehEstreia = rgEstreia.getCheckedRadioButtonId() == R.id.cadastroFilme_rbEstreiaSim ? 1 : 0;
            int emExibicao = rgExibicao.getCheckedRadioButtonId() == R.id.cadastroFilme_rbExibicaoSim ? 1 : 0;
            filme.setHabilitado(emExibicao);
            filme.setEstreia(ehEstreia);
            if (filme.getIdfilme() == 0)
                filmeDAO.salvar(filme);
            else
                filmeDAO.atualizar(filme);
            //Intent intentConfiguracoes = new Intent(this, ConfiguracoesActivity.class);
            //navigateUpTo(intentConfiguracoes);
        //}else{
         //   Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
        //}
 //   }

   // }

*/

        //public void ListarCinemas(View view) {

        //}
    }
}
