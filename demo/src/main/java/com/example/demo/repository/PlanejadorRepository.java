package com.example.demo.repository;

import com.example.demo.model.Planejador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlanejadorRepository {
    private static final String FILE_NAME = "planejadores.dat";

    public void salvar(Planejador planejador) throws IOException {
        List<Planejador> lista = listarTodos();
        lista.add(planejador);
        salvarLista(lista);
    }

    public List<Planejador> listarTodos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Planejador>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void salvarLista(List<Planejador> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(lista);
        }
    }

    public void excluir(int idPlanejador) throws IOException {
        List<Planejador> lista = listarTodos();
        lista.removeIf(p -> p.getIdPlanejador() == idPlanejador);
        salvarLista(lista);
    }

    public void atualizar(Planejador planejadorAtualizado) throws IOException {
        List<Planejador> lista = listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPlanejador() == planejadorAtualizado.getIdPlanejador()) {
                lista.set(i, planejadorAtualizado);
                break;
            }
        }
        salvarLista(lista);
    }

    public Planejador buscarPorId(int idPlanejador) {
        for (Planejador p : listarTodos()) {
            if (p.getIdPlanejador() == idPlanejador) return p;
        }
        return null;
    }
}
