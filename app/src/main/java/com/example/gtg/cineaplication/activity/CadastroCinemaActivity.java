package com.example.gtg.cineaplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtg.cineaplication.dao.CinemaDAO;
import com.example.gtg.cineaplication.dao.SessaoDAO;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Cinema;
import com.example.gtg.cineaplication.modelo.Filme;
import com.example.gtg.cineaplication.modelo.Horario;
import com.example.gtg.cineaplication.modelo.Sessao;

import java.util.List;

public class CadastroCinemaActivity extends AppCompatActivity {

    private TextView tvIdCinema;
    private TextView tvNomeCinema;
    private EditText etNomeCinema;
    private TextView tvEnderecoCinema;
    private EditText etEnderecoCinema;
    private TextView tvLatCinema;
    private TextView tvLngCinema;
    private Button btnLocalizaCinema;
    private Button btnRemove;
    private Button btnSalva;
    private boolean editmode;
    private final int REQUEST_CINEMA_LOCAL = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cinema);

        btnRemove = (Button) findViewById(R.id.btRemoveCinema);
        btnSalva = (Button) findViewById(R.id.btCadastroCinema);
        btnLocalizaCinema = (Button) findViewById(R.id.btLocalCinema);
        tvIdCinema = (TextView) findViewById(R.id.tvIdCinema);
        tvNomeCinema = (TextView) findViewById(R.id.tvNomeCinema);
        etNomeCinema = (EditText) findViewById(R.id.etNomeCinema);
        tvEnderecoCinema = (TextView) findViewById(R.id.tvEnderecoCinema);
        etEnderecoCinema = (EditText) findViewById(R.id.etEnderecoCinema);

        tvLatCinema = (TextView) findViewById(R.id.tvLatCinema);
        tvLngCinema = (TextView) findViewById(R.id.tvLngCinema);

        if ( getIntent().hasExtra("edit") )
        {
            int cinemaid = getIntent().getExtras().getInt("cinemaid");
            CinemaDAO cinemaDAO = new CinemaDAO(this);
            Cinema cinema = cinemaDAO.procurarPorId(cinemaid);

            editmode = true;

            Log.d("Teste",String.valueOf(cinema.getId()));

            btnRemove.setVisibility(View.VISIBLE);
            btnRemove.setTag(cinema);
            tvIdCinema.setText(String.valueOf(cinemaid));
            etNomeCinema.setText(String.valueOf(cinema.getNome()));
            etEnderecoCinema.setText(String.valueOf(cinema.getEndereco()));
            tvLatCinema.setText(String.valueOf(cinema.getLatitude()));
            tvLngCinema.setText(String.valueOf(cinema.getLongitude()));
        }
        else
        {
            editmode = false;
            btnRemove.setVisibility(View.INVISIBLE);
        }
    }

    public void salvarCinema(View view)
    {
        if ( editmode )
        {
            if ( tvIdCinema.getText().equals("0") ||
                    etNomeCinema.getText().equals("") ||
                    etEnderecoCinema.getText().equals("") ||
                    tvLatCinema.getText().equals("") ||
                    tvLngCinema.getText().equals("")
                    )
            {
                Toast.makeText(this,"Informe os dados corretamente!",Toast.LENGTH_SHORT).show();
                return;
            }

            CinemaDAO cinemaDAO = new CinemaDAO(this);

            Cinema cinema = new Cinema();
            cinema.setId(Integer.parseInt(tvIdCinema.getText().toString()));
            cinema.setNome(etNomeCinema.getText().toString());
            cinema.setEndereco(etEnderecoCinema.getText().toString());
            cinema.setLatitude(Float.parseFloat(tvLatCinema.getText().toString()));
            cinema.setLongitude(Float.parseFloat(tvLngCinema.getText().toString()));

            cinemaDAO.atualizar(cinema);
        }
        else
        {
            if ( etNomeCinema.getText().equals("") ||
                    etEnderecoCinema.getText().equals("") ||
                    tvLatCinema.getText().equals("") ||
                    tvLngCinema.getText().equals("")
                    )
            {
                Toast.makeText(this,"Informe os dados corretamente!",Toast.LENGTH_SHORT).show();
                return;
            }

            CinemaDAO cinemaDAO = new CinemaDAO(this);

            Cinema cinema = new Cinema();
            cinema.setNome(etNomeCinema.getText().toString());
            cinema.setEndereco(etEnderecoCinema.getText().toString());
            cinema.setLatitude(Float.parseFloat(tvLatCinema.getText().toString()));
            cinema.setLongitude(Float.parseFloat(tvLngCinema.getText().toString()));

            cinemaDAO.adicionar(cinema);
        }

        Intent intent = new Intent(this, ListaCinemaActivity.class);
        navigateUpTo(intent);
    }

    public void removerCinema(View view)
    {
        Cinema cinema = (Cinema) view.getTag();
        CinemaDAO cinemaDAO = new CinemaDAO(this);
        cinemaDAO.excluir(cinema.getId());

        Intent intent = new Intent(this, ListaCinemaActivity.class);
        navigateUpTo(intent);
    }

    public void localizarCinema(View view)
    {
        Intent intent = new Intent(this, CinemaLocalActivity.class);
        startActivityForResult(intent,REQUEST_CINEMA_LOCAL);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == REQUEST_CINEMA_LOCAL ) {
            Bundle resposta = data.getExtras();
            tvLatCinema.setText(resposta.getString("lat"));
            tvLngCinema.setText(resposta.getString("lng"));
            Toast.makeText(this, resposta.getString("lat")+","+resposta.getString("lng"), Toast.LENGTH_SHORT).show();
        }
    }
}
