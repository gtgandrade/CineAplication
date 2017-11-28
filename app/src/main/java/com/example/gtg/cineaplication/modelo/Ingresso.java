package com.example.gtg.cineaplication.modelo;

/**
 * Created by gutemberg on 26/11/17.
 */

public class Ingresso {
    private int idingresso;
    private int qtdinteira;
    private int qtdmeia;
    private double precoingresso;
    private int pipocarefrigerante;
    private double precopipocarefrigerante;
    private Sessao sessao;

    public int getIdingresso() {
        return idingresso;
    }

    public void setIdingresso(int idingresso) {
        this.idingresso = idingresso;
    }

    public int getQtdinteira() {
        return qtdinteira;
    }

    public void setQtdinteira(int qtdinteira) {
        this.qtdinteira = qtdinteira;
    }

    public int getQtdmeia() {
        return qtdmeia;
    }

    public void setQtdmeia(int qtdmeia) {
        this.qtdmeia = qtdmeia;
    }

    public double getPrecoingresso() {
        return precoingresso;
    }

    public void setPrecoingresso(double precoingresso) {
        this.precoingresso = precoingresso;
    }

    public int getPipocarefrigerante() {
        return pipocarefrigerante;
    }

    public void setPipocarefrigerante(int pipocarefrigerante) {
        this.pipocarefrigerante = pipocarefrigerante;
    }

    public double getPrecopipocarefrigerante() {
        return precopipocarefrigerante;
    }

    public void setPrecopipocarefrigerante(double precopipocarefrigerante) {
        this.precopipocarefrigerante = precopipocarefrigerante;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
}
