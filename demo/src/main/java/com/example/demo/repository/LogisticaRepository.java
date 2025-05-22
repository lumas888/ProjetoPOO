package com.example.demo.repository;

import com.example.demo.model.Logistica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogisticaRepository {
    private static final String FILE_NAME = "logisticas.dat";

    public void salvar(Logistica logistica) throws IOException {
        List<Logistica> lista = listarTodos();
        lista.add(logistica);
        salvarLista(lista);
    }

    public List<Logistica> listarTodos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Logistica>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void salvarLista(List<Logistica> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(lista);
        }
    }

    public void excluir(int idLogistica) throws IOException {
        List<Logistica> lista = listarTodos();
        lista.removeIf(l -> l.getIdLogistica() == idLogistica);
        salvarLista(lista);
    }

    public void atualizar(Logistica logisticaAtualizada) throws IOException {
        List<Logistica> lista = listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdLogistica() == logisticaAtualizada.getIdLogistica()) {
                lista.set(i, logisticaAtualizada);
                break;
            }
        }
        salvarLista(lista);
    }

    public Logistica buscarPorId(int idLogistica) {
        for (Logistica l : listarTodos()) {
            if (l.getIdLogistica() == idLogistica) return l;
        }
        return null;
    }
}
