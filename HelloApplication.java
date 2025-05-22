package com.example.demo;
import com.example.demo.view.ClienteView;
import com.example.demo.view.GestorView;
import com.example.demo.view.EquipamentoView;
import com.example.demo.view.LogisticaView;
import com.example.demo.view.PlanejadorView;
import com.example.demo.view.UsuarioView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Gestão");

        Button btnCliente = new Button("CRUD Cliente");
        Button btnGestor = new Button("CRUD Gestor");
        Button btnEquipamento = new Button("CRUD Equipamento");
        Button btnLogistica = new Button("CRUD Logistica");
        Button btnPlanejador = new Button("CRUD Planejador");
        Button btnUsuario = new Button("CRUD Usuario");

        btnCliente.setOnAction(e -> abrirJanela(new ClienteView(), "CRUD Cliente"));
        btnGestor.setOnAction(e -> abrirJanela(new GestorView(), "CRUD Gestor"));
        btnEquipamento.setOnAction(e -> abrirJanela(new EquipamentoView(), "CRUD Equipamento"));
        btnLogistica.setOnAction(e -> abrirJanela(new LogisticaView(), "CRUD Logistica"));
        btnPlanejador.setOnAction(e -> abrirJanela(new PlanejadorView(), "CRUD Planejador"));
        btnUsuario.setOnAction(e -> abrirJanela(new UsuarioView(), "CRUD Usuario"));

        VBox vbox = new VBox(15, btnCliente, btnGestor, btnEquipamento, btnLogistica, btnPlanejador, btnUsuario);
        vbox.setPadding(new javafx.geometry.Insets(20));

        Scene scene = new Scene(vbox, 450, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void abrirJanela(VBox view, String titulo) {
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(new Scene(view, 600, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
