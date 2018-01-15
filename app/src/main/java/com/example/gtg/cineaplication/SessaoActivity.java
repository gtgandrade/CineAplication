package com.example.gtg.cineaplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gtg.cineaplication.DAO.HorarioDAO;
import com.example.gtg.cineaplication.DAO.SessaoBD;
import com.example.gtg.cineaplication.modelo.Filme;
import com.example.gtg.cineaplication.modelo.Horario;
import com.example.gtg.cineaplication.modelo.Sessao;
import java.util.Iterator;
import java.util.List;

public class SessaoActivity extends AppCompatActivity {
	private CheckBox chkInteira;
	private CheckBox chkMeia;
	private EditText edtQtdInteira;
	private EditText edtQtdMeia;
	private Filme filme;
	private HorarioDAO horDAO;
	private TextView lblFilmeEscolhido;
	private int qtdInteira;
	private int qtdMeia;
	private RadioGroup rgPipocaRefri;
	private Sessao sessao;
	private SessaoBD sessaoBD;
	private Spinner spnHorarios;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sessao);
		lblFilmeEscolhido = findViewById(R.id.sessao_lblFilmeEscolhido);
		spnHorarios = findViewById(R.id.sessao_spnHorarios);
		edtQtdInteira = findViewById(R.id.sessao_edtQtdInteira);
		edtQtdMeia = findViewById(R.id.sessao_edtQtdMeia);
		chkInteira = findViewById(R.id.sessao_chkInteira);
		chkMeia = findViewById(R.id.sessao_chkMeia);
		rgPipocaRefri = findViewById(R.id.sessao_rgPipocaRefrigerante);
		Bundle paramentros = getIntent().getExtras();
		horDAO = new HorarioDAO(this);
		filme = new Filme();
		filme.setIdfilme(paramentros.getInt("idfilme"));
		filme.setCodigo(paramentros.getInt("codigo"));
		filme.setNome(paramentros.getString("nome"));
		lblFilmeEscolhido.setText(filme.getNome());
		sessaoBD = new SessaoBD(this);
		List<Sessao> sessoes = sessaoBD.findSessoes(filme);
		Iterator<Sessao> iteratorSessoes = sessoes.iterator();
		String horarios[] = new String[sessoes.size() + 1];
		int contador = 1;
		horarios[0] = "-----";
		while (iteratorSessoes.hasNext()) {
			Sessao s = iteratorSessoes.next();
			horarios[contador] = s.getHorario().getDescricao();
			contador++;
		}

		ArrayAdapter<String> adapterSpinnerHorarios = new ArrayAdapter<>(this,
			R.layout.support_simple_spinner_dropdown_item,
			horarios);
		adapterSpinnerHorarios.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		spnHorarios.setAdapter(adapterSpinnerHorarios);
	}

	public void decrementarInteira(View view) {
		qtdInteira = Integer.parseInt(edtQtdInteira.getText().toString());
		if (qtdInteira > 0)
			edtQtdInteira.setText(String.valueOf(--qtdInteira));
		if (qtdInteira == 0)
			chkInteira.setChecked(false);
	}

	public void decrementarMeia(View view) {
		qtdMeia = Integer.parseInt(edtQtdMeia.getText().toString());
		if (qtdMeia > 0)
			edtQtdMeia.setText(String.valueOf(--qtdMeia));
		if (qtdMeia == 0)
			chkMeia.setChecked(false);
	}

	public void incrementarInteira(View view) {
		qtdInteira = Integer.parseInt(edtQtdInteira.getText().toString());
		edtQtdInteira.setText(String.valueOf(++qtdInteira));
		chkInteira.setChecked(true);
	}

	public void incrementarMeia(View view) {
		qtdMeia = Integer.parseInt(edtQtdMeia.getText().toString());
		edtQtdMeia.setText(String.valueOf(++qtdMeia));
		chkMeia.setChecked(true);
	}

	public void irParaTelaResumo(View view) {
		if (validaEntradaDeDados()) {
			Intent intentResumo = new Intent(this, ResumoActivity.class);
			Bundle parametros = new Bundle();
			Horario h = horDAO.busca(spnHorarios.getSelectedItem().toString());

			sessao = new Sessao();
			sessao = sessaoBD.findSessaBy(filme, h);
			parametros.putInt("idfilme", filme.getIdfilme());
			parametros.putString("nome", filme.getNome());
			parametros.putInt("numeroSala", sessao.getSala());
			parametros.putString("horario", h.getDescricao());
			parametros.putInt("idsessao", sessao.getIdsessao());
			parametros.putString("qtdInteira", edtQtdInteira.getText().toString());
			parametros.putString("qtdMeia", edtQtdMeia.getText().toString());
			int opcaoSim = R.id.sessao_rbPipocaRefrigeranteSim;
			parametros.putInt("qtdLanche", (rgPipocaRefri.getCheckedRadioButtonId() == opcaoSim ? 1 : 0));
			parametros.putDouble("precoInteira", 22.00);
			parametros.putDouble("precoLanche", 18.00);

			intentResumo.putExtras(parametros);
			startActivity(intentResumo);
		} else {
			Toast.makeText(this, "Escolha a sessão e o tipo de ingresso.", Toast.LENGTH_SHORT).show();
		}
	}

	public void verificarMaracacaoInteira(View view) {
		if (!chkInteira.isChecked())
			edtQtdInteira.setText("0");
		else
			edtQtdInteira.setText("1");
	}

	public void verificarMaracacaoMeia(View view) {
		if (!chkMeia.isChecked())
			edtQtdMeia.setText("0");
		else
			edtQtdMeia.setText("1");
	}

	private boolean validaEntradaDeDados() {
		return ((chkInteira.isChecked() && !getString(edtQtdInteira).equals("0")) ||
			(chkMeia.isChecked() && !getString(edtQtdMeia).equals("0"))) && spnHorarios.getSelectedItemId() != 0;
	}

	private String getString(final EditText edt) {
		return (edt.getText().toString());
	}
}
