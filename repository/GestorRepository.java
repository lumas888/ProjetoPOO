package com.example.demo.repository;

import com.example.demo.model.Gestor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorRepository {
    private static final String FILE_NAME = "gestores.dat";

    public void salvar(Gestor gestor) throws IOException {
        List<Gestor> lista = listarTodos();
        lista.add(gestor);
        salvarLista(lista);
    }

    public List<Gestor> listarTodos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Gestor>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void salvarLista(List<Gestor> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(lista);
        }
    }

    public void excluir(int idGestor) throws IOException {
        List<Gestor> lista = listarTodos();
        lista.removeIf(c -> c.getIdGestor() == idGestor);
        salvarLista(lista);
    }

    public void atualizar(Gestor gestorAtualizado) throws IOException {
        List<Gestor> lista = listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdGestor() == gestorAtualizado.getIdGestor()) {
                lista.set(i, gestorAtualizado);
                break;
            }
        }
        salvarLista(lista);
    }

    public Gestor buscarPorId(int idGestor) {
        for (Gestor g : listarTodos()) {
            if (g.getIdGestor() == idGestor) return g;
        }
        return null;
    }
}
