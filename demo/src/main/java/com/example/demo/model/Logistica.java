package com.example.demo.model;

import java.io.Serializable;

public class Logistica implements Serializable {
    private int idLogistica;
    private String origem;
    private String destino;
    private String data;
    private String status;

    public Logistica(int idLogistica, String origem, String destino, String data, String status) {
        this.idLogistica = idLogistica;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.status = status;
    }

    public int getIdLogistica() { return idLogistica; }
    public void setIdLogistica(int idLogistica) { this.idLogistica = idLogistica; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return idLogistica + " - " + origem + " - " + destino + " - " + data + " - " + status;
    }
}

