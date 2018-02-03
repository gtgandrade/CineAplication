package com.example.gtg.cineaplication.modelo;

import java.util.ArrayList;
import java.util.List;

/** Created by Leoman on 27/01/2018. */
public class Cinema {
    private int id;
    private String nome;
    private String endereco;
    private double longitude;
    private double latitude;
    private String cartaz=null;

    private List<Filme> filmes = new ArrayList<>();

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCartaz(){
        return this.cartaz;
    }

    public void setCartaz(String cartaz){
        this.cartaz = cartaz;
    }
}
