package com.tuempresa.tresfxml;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private void abrirRegistroClientes(ActionEvent event) {
        cargarPantalla("/com/tuempresa/tresfxml/Cliente.fxml", "Registro de Clientes", event);
    }

    @FXML
    private void abrirSolicitudServicio(ActionEvent event) {
        cargarPantalla("/com/tuempresa/tresfxml/Solicitud.fxml", "Solicitud de Servicio", event);
    }

    @FXML
    private void salir(ActionEvent event) {
        Platform.exit();
    }

    private void cargarPantalla(String fxmlPath, String titulo, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}