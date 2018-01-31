package com.example.gtg.cineaplication.modelo;

/**
 * Created by gutemberg on 26/11/17.
 */

public class Horario{
    private int idhorario = 0;
    private String descricao = null;

    public Horario()
    {
        idhorario = 0;
        descricao = "";
    }

    public int getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(int idhorario) {
        this.idhorario = idhorario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
