package com.example.demo.view;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import com.example.demo.model.Gestor;
import com.example.demo.repository.GestorRepository;

import java.io.IOException;

public class GestorView extends VBox {
    private TextField tfId = new TextField();
    private TextField tfNome = new TextField();
    private TextField tfTelefone = new TextField();
    private TextField tfEmail = new TextField();
    private TextField tfF1 = new TextField();
    private TextField tfF2 = new TextField();
    private TextArea taLista = new TextArea();

    private GestorRepository repo = new GestorRepository();

    public GestorView() {
        setSpacing(10);
        setPadding(new Insets(15));

        tfId.setPromptText("ID");
        tfNome.setPromptText("Nome");
        tfTelefone.setPromptText("Telefone");
        tfEmail.setPromptText("Email");
        tfF1.setPromptText("F1");
        tfF2.setPromptText("F2");
        taLista.setPrefRowCount(10);
        taLista.setEditable(false);

        Button btnSalvar = new Button("Inserir");
        Button btnBuscar = new Button("Buscar");
        Button btnAtualizar = new Button("Atualizar");
        Button btnExcluir = new Button("Excluir");
        Button btnListar = new Button("Listar Todos");

        btnSalvar.setOnAction(e -> inserirGestor());
        btnBuscar.setOnAction(e -> buscarGestor());
        btnAtualizar.setOnAction(e -> atualizarGestor());
        btnExcluir.setOnAction(e -> excluirGestor());
        btnListar.setOnAction(e -> listarGestores());

        getChildren().addAll(
                tfId, tfNome, tfTelefone, tfEmail, tfF1, tfF2,
                new HBox(10, btnSalvar, btnBuscar, btnAtualizar, btnExcluir, btnListar),
                taLista
        );
    }

    private void inserirGestor() {
        try {
            Gestor gestor = new Gestor(
                    Integer.parseInt(tfId.getText()),
                    tfNome.getText(),
                    tfTelefone.getText(),
                    tfEmail.getText(),
                    tfF1.getText(),
                    tfF2.getText()
            );
            repo.salvar(gestor);
            limparCampos();
            listarGestores();
        } catch (Exception ex) {
            taLista.setText("Erro ao inserir: " + ex.getMessage());
        }
    }

    private void buscarGestor() {
        try {
            int id = Integer.parseInt(tfId.getText());
            Gestor gestor = repo.buscarPorId(id);
            if (gestor != null) {
                tfNome.setText(gestor.getNome());
                tfTelefone.setText(gestor.getTelefone());
                tfEmail.setText(gestor.getEmail());
                tfF1.setText(gestor.getF1());
                tfF2.setText(gestor.getF2());
            } else {
                taLista.setText("Gestor não encontrado.");
            }
        } catch (Exception e) {
            taLista.setText("Erro ao buscar: " + e.getMessage());
        }
    }

    private void atualizarGestor() {
        try {
            Gestor gestor = new Gestor(
                    Integer.parseInt(tfId.getText()),
                    tfNome.getText(),
                    tfTelefone.getText(),
                    tfEmail.getText(),
                    tfF1.getText(),
                    tfF2.getText()
            );
            repo.atualizar(gestor);
            limparCampos();
            listarGestores();
        } catch (Exception ex) {
            taLista.setText("Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluirGestor() {
        try {
            int id = Integer.parseInt(tfId.getText());
            repo.excluir(id);
            limparCampos();
            listarGestores();
        } catch (IOException ex) {
            taLista.setText("Erro ao excluir: " + ex.getMessage());
        }
    }

    private void listarGestores() {
        taLista.clear();
        for (Gestor g : repo.listarTodos()) {
            taLista.appendText(g.toString() + "\n");
        }
    }

    private void limparCampos() {
        tfId.clear();
        tfNome.clear();
        tfTelefone.clear();
        tfEmail.clear();
        tfF1.clear();
        tfF2.clear();
    }
}
