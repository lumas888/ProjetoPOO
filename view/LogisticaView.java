package com.example.demo.view;

import com.example.demo.model.Logistica;
import com.example.demo.repository.LogisticaRepository;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

import java.io.IOException;

public class LogisticaView extends VBox {
    private TextField tfId = new TextField();
    private TextField tfOrigem = new TextField();
    private TextField tfDestino = new TextField();
    private TextField tfData = new TextField();
    private TextField tfStatus = new TextField();
    private TextArea taLista = new TextArea();

    private LogisticaRepository repo = new LogisticaRepository();

    public LogisticaView() {
        setSpacing(10);
        setPadding(new Insets(15));

        tfId.setPromptText("ID");
        tfOrigem.setPromptText("Origem");
        tfDestino.setPromptText("Destino");
        tfData.setPromptText("Data");
        tfStatus.setPromptText("Status");
        taLista.setPrefRowCount(10);
        taLista.setEditable(false);

        Button btnSalvar = new Button("Inserir");
        Button btnBuscar = new Button("Buscar");
        Button btnAtualizar = new Button("Atualizar");
        Button btnExcluir = new Button("Excluir");
        Button btnListar = new Button("Listar Todos");

        btnSalvar.setOnAction(e -> inserirLogistica());
        btnBuscar.setOnAction(e -> buscarLogistica());
        btnAtualizar.setOnAction(e -> atualizarLogistica());
        btnExcluir.setOnAction(e -> excluirLogistica());
        btnListar.setOnAction(e -> listarLogisticas());

        getChildren().addAll(tfId, tfOrigem, tfDestino, tfData, tfStatus,
                new HBox(10, btnSalvar, btnBuscar, btnAtualizar, btnExcluir, btnListar),
                taLista);
    }

    private void inserirLogistica() {
        try {
            Logistica logistica = new Logistica(
                    Integer.parseInt(tfId.getText()),
                    tfOrigem.getText(),
                    tfDestino.getText(),
                    tfData.getText(),
                    tfStatus.getText()
            );
            repo.salvar(logistica);
            limparCampos();
            listarLogisticas();
        } catch (Exception ex) {
            taLista.setText("Erro ao inserir: " + ex.getMessage());
        }
    }

    private void buscarLogistica() {
        try {
            int id = Integer.parseInt(tfId.getText());
            Logistica logistica = repo.buscarPorId(id);
            if (logistica != null) {
                tfOrigem.setText(logistica.getOrigem());
                tfDestino.setText(logistica.getDestino());
                tfData.setText(logistica.getData());
                tfStatus.setText(logistica.getStatus());
            } else {
                taLista.setText("Logistica não encontrada.");
            }
        } catch (Exception e) {
            taLista.setText("Erro ao buscar: " + e.getMessage());
        }
    }

    private void atualizarLogistica() {
        try {
            Logistica logistica = new Logistica(
                    Integer.parseInt(tfId.getText()),
                    tfOrigem.getText(),
                    tfDestino.getText(),
                    tfData.getText(),
                    tfStatus.getText()
            );
            repo.atualizar(logistica);
            limparCampos();
            listarLogisticas();
        } catch (Exception ex) {
            taLista.setText("Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluirLogistica() {
        try {
            int id = Integer.parseInt(tfId.getText());
            repo.excluir(id);
            limparCampos();
            listarLogisticas();
        } catch (IOException ex) {
            taLista.setText("Erro ao excluir: " + ex.getMessage());
        }
    }

    private void listarLogisticas() {
        taLista.clear();
        for (Logistica l : repo.listarTodos()) {
            taLista.appendText(l.toString() + "\n");
        }
    }

    private void limparCampos() {
        tfId.clear();
        tfOrigem.clear();
        tfDestino.clear();
        tfData.clear();
        tfStatus.clear();
    }
}
