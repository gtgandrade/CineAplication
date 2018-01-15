package com.example.gtg.cineaplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.ImageWriter;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gtg.cineaplication.DAO.FilmeDAO;
import com.example.gtg.cineaplication.modelo.Filme;

public class CadastroFilmeActivity extends AppCompatActivity {
    private ImageView imgCartazFilme;
    private EditText edtNome;
    private EditText edtPais;
    private Spinner spnVersao;
    private EditText edtDuracao;
    private RadioGroup rgExibicao;
    private RadioButton rbExibicaoSim;
    private RadioButton rbExibicaoNao;
    private Uri uriImagemSelecionada;
    private ArrayAdapter<String> adapterVersao;

    private Filme filme;
    private  FilmeDAO filmeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_filme);
        imgCartazFilme = (ImageView) findViewById(R.id.cadastroFilme_imgCartazFilme);
        spnVersao = (Spinner) findViewById(R.id.cadastroFilme_spnVersao);
        edtNome = (EditText) findViewById(R.id.cadastroFilme_edtNome);
        edtPais = (EditText) findViewById(R.id.cadastroFilme_edtPais);
        edtDuracao = (EditText) findViewById(R.id.cadastroFilme_edtDuracao);
        rgExibicao = (RadioGroup) findViewById(R.id.cadastroFilme_rgExibicao);
        rbExibicaoSim = (RadioButton) findViewById(R.id.cadastroFilme_rbExibicaoSim);
        rbExibicaoNao = (RadioButton) findViewById(R.id.cadastroFilme_rbExibicaoNao);

        adapterVersao = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.versao));
        spnVersao.setAdapter(adapterVersao);
        Bundle parametrosEntrada = getIntent().getExtras();
        int idFilme = parametrosEntrada.getInt("idFilme");
        filme = new Filme();
        filmeDAO = new FilmeDAO(this);
        if(idFilme != 0){
            filme = filmeDAO.procurarPorId(idFilme);
            inserirDadosInterface(filme);
        }
    }

    public void buscarImagemCartaz(View view){
        Intent intentImagemCartaz = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intentImagemCartaz, "Selecione uma imagem"), 1);
    }

    public void salvarFilme(View view){
        Toast.makeText(this,"Filme ID: "+filme.getIdfilme(),Toast.LENGTH_SHORT).show();
        filme.setNome(edtNome.getText().toString());
        filme.setCartaz(uriImagemSelecionada.toString());
        filme.setPais(edtPais.getText().toString());
        filme.setVersao(spnVersao.getSelectedItem().toString());
        filme.setDuracao(Integer.parseInt(edtDuracao.getText().toString()));
        int opcaoSelecionada = rgExibicao.getCheckedRadioButtonId() == R.id.cadastroFilme_rbExibicaoSim?1:0;
        filme.setHabilitado(opcaoSelecionada);
        if(filme.getIdfilme() == 0)
            filmeDAO.salvar(filme);
        else
            filmeDAO.atualizar(filme);
        Intent intentPrincipal= new Intent(this, PrincipalActivity.class);
        navigateUpTo(intentPrincipal);
    }

    private void inserirDadosInterface(Filme filme){
        uriImagemSelecionada = Uri.parse(filme.getCartaz());
        imgCartazFilme.setImageURI(uriImagemSelecionada);
        edtNome.setText(filme.getNome());
        edtPais.setText(filme.getPais());
        spnVersao.setSelection(adapterVersao.getPosition(filme.getVersao()));
        edtDuracao.setText(String.valueOf(filme.getDuracao()));
        if(filme.getHabilitado() == 1){
            rbExibicaoSim.setChecked(true);
        }else {
            rbExibicaoNao.setChecked(true);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 1){
                uriImagemSelecionada = data.getData();
                imgCartazFilme.setImageURI(uriImagemSelecionada);
            }
        }
    }
}
