package com.example.demo.view;

import com.example.demo.model.Planejador;
import com.example.demo.repository.PlanejadorRepository;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

import java.io.IOException;

public class PlanejadorView extends VBox {
    private TextField tfId = new TextField();
    private TextField tfNome = new TextField();
    private TextField tfSetor = new TextField();
    private TextField tfTelefone = new TextField();
    private TextField tfEmail = new TextField();
    private TextArea taLista = new TextArea();

    private PlanejadorRepository repo = new PlanejadorRepository();

    public PlanejadorView() {
        setSpacing(10);
        setPadding(new Insets(15));

        tfId.setPromptText("ID");
        tfNome.setPromptText("Nome");
        tfSetor.setPromptText("Setor");
        tfTelefone.setPromptText("Telefone");
        tfEmail.setPromptText("Email");
        taLista.setPrefRowCount(10);
        taLista.setEditable(false);

        Button btnSalvar = new Button("Inserir");
        Button btnBuscar = new Button("Buscar");
        Button btnAtualizar = new Button("Atualizar");
        Button btnExcluir = new Button("Excluir");
        Button btnListar = new Button("Listar Todos");

        btnSalvar.setOnAction(e -> inserirPlanejador());
        btnBuscar.setOnAction(e -> buscarPlanejador());
        btnAtualizar.setOnAction(e -> atualizarPlanejador());
        btnExcluir.setOnAction(e -> excluirPlanejador());
        btnListar.setOnAction(e -> listarPlanejadores());

        getChildren().addAll(tfId, tfNome, tfSetor, tfTelefone, tfEmail,
                new HBox(10, btnSalvar, btnBuscar, btnAtualizar, btnExcluir, btnListar),
                taLista);
    }

    private void inserirPlanejador() {
        try {
            Planejador planejador = new Planejador(
                    Integer.parseInt(tfId.getText()),
                    tfNome.getText(),
                    tfSetor.getText(),
                    tfTelefone.getText(),
                    tfEmail.getText()
            );
            repo.salvar(planejador);
            limparCampos();
            listarPlanejadores();
        } catch (Exception ex) {
            taLista.setText("Erro ao inserir: " + ex.getMessage());
        }
    }

    private void buscarPlanejador() {
        try {
            int id = Integer.parseInt(tfId.getText());
            Planejador planejador = repo.buscarPorId(id);
            if (planejador != null) {
                tfNome.setText(planejador.getNome());
                tfSetor.setText(planejador.getSetor());
                tfTelefone.setText(planejador.getTelefone());
                tfEmail.setText(planejador.getEmail());
            } else {
                taLista.setText("Planejador não encontrado.");
            }
        } catch (Exception e) {
            taLista.setText("Erro ao buscar: " + e.getMessage());
        }
    }

    private void atualizarPlanejador() {
        try {
            Planejador planejador = new Planejador(
                    Integer.parseInt(tfId.getText()),
                    tfNome.getText(),
                    tfSetor.getText(),
                    tfTelefone.getText(),
                    tfEmail.getText()
            );
            repo.atualizar(planejador);
            limparCampos();
            listarPlanejadores();
        } catch (Exception ex) {
            taLista.setText("Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluirPlanejador() {
        try {
            int id = Integer.parseInt(tfId.getText());
            repo.excluir(id);
            limparCampos();
            listarPlanejadores();
        } catch (IOException ex) {
            taLista.setText("Erro ao excluir: " + ex.getMessage());
        }
    }

    private void listarPlanejadores() {
        taLista.clear();
        for (Planejador p : repo.listarTodos()) {
            taLista.appendText(p.toString() + "\n");
        }
    }

    private void limparCampos() {
        tfId.clear();
        tfNome.clear();
        tfSetor.clear();
        tfTelefone.clear();
        tfEmail.clear();
    }
}
