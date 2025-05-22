package com.example.demo.model;

import java.io.Serializable;

public class Gestor implements Serializable {
    private int idGestor;
    private String nome;
    private String telefone;
    private String email;
    private String f1;
    private String f2;

    public Gestor(int idGestor, String nome, String telefone, String email, String f1, String f2) {
        this.idGestor = idGestor;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.f1 = f1;
        this.f2 = f2;
    }

    public int getIdGestor() { return idGestor; }
    public void setIdGestor(int idGestor) { this.idGestor = idGestor; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getF1() { return f1; }
    public void setF1(String f1) { this.f1 = f1; }

    public String getF2() { return f2; }
    public void setF2(String f2) { this.f2 = f2; }

    @Override
    public String toString() {
        return idGestor + " - " + nome + " - " + telefone + " - " + email + " - " + f1 + " - " + f2;
    }
}
