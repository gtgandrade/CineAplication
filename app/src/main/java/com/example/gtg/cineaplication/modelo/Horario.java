package com.example.gtg.cineaplication.modelo;

/** Created by gutemberg on 26/11/17. */
public class Horario {
	private String descricao;
	private int idhorario;

	public Horario(final int id, final String desc) {
		this.idhorario = id;
		this.descricao = desc;
	}

	public Horario() {
		this(0, ""); // inicialização padrão
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getIdhorario() {
		return idhorario;
	}

	public void setIdhorario(int idhorario) {
		this.idhorario = idhorario;
	}
}