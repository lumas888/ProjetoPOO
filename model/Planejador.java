package com.example.demo.model;

import java.io.Serializable;

public class Planejador implements Serializable {
    private int idPlanejador;
    private String nome;
    private String setor;
    private String telefone;
    private String email;

    public Planejador(int idPlanejador, String nome, String setor, String telefone, String email) {
        this.idPlanejador = idPlanejador;
        this.nome = nome;
        this.setor = setor;
        this.telefone = telefone;
        this.email = email;
    }

    public int getIdPlanejador() { return idPlanejador; }
    public void setIdPlanejador(int idPlanejador) { this.idPlanejador = idPlanejador; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return idPlanejador + " - " + nome + " - " + setor + " - " + telefone + " - " + email;
    }
}
