package com.example.gtg.cineaplication.modelo;

/**
 * Created by gutemberg on 26/11/17.
 */

public class Sessao {
    private int idsessao = 0;
    private int sala;
    private Filme filme;
    private Horario horario;
    private boolean vip;

    public Sessao()
    {
        sala = 0;
        filme = new Filme();
        horario = new Horario();
        vip = false;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public int getIdsessao() {
        return idsessao;
    }

    public void setIdsessao(int idsessao) {
        this.idsessao = idsessao;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
