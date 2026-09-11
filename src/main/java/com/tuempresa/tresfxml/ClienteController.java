package com.tuempresa.tresfxml;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ClienteController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private ComboBox<String> cbTipoCliente;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtDirectorio;

    private File archivoDocumento;
    private File directorioCliente;

    @FXML
    public void initialize() {
        cbTipoCliente.setItems(FXCollections.observableArrayList("Individual", "Empresarial", "Gubernamental"));
    }

    @FXML
    private void seleccionarDocumento(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Seleccionar Documento de Identificación");
        archivoDocumento = chooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (archivoDocumento != null) {
            txtDocumento.setText(archivoDocumento.getAbsolutePath());
        }
    }

    @FXML
    private void seleccionarDirectorio(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Seleccionar Directorio del Cliente");
        directorioCliente = chooser.showDialog(((Node) event.getSource()).getScene().getWindow());
        if (directorioCliente != null) {
            txtDirectorio.setText(directorioCliente.getAbsolutePath());
        }
    }

    @FXML
    private void guardar(ActionEvent event) {
        if (validarCampos()) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente guardado correctamente.");
        }
    }

    @FXML
    private void crearSolicitud(ActionEvent event) {
        if (validarCampos()) {
            try {
                // Aquí se cargaba Solicitud.fxml con la ruta corregida:
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tuempresa/tresfxml/Solicitud.fxml"));
                Parent root = loader.load();

                SolicitudController solicitudCtrl = loader.getController();
                solicitudCtrl.cargarDatosCliente(txtNombre.getText(), txtCorreo.getText(), cbTipoCliente.getValue());

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Solicitud de Servicio");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        cbTipoCliente.setValue(null);
        txtDocumento.clear();
        txtDirectorio.clear();
        archivoDocumento = null;
        directorioCliente = null;
    }

    @FXML
    private void cerrar(ActionEvent event) {
        try {
            // Aquí se cargaba Menu.fxml con la ruta corregida:
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tuempresa/tresfxml/Menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Sistema de Soporte Técnico");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtCorreo.getText().trim().isEmpty() ||
                txtTelefono.getText().trim().isEmpty() ||
                cbTipoCliente.getValue() == null ||
                txtDocumento.getText().trim().isEmpty() ||
                txtDirectorio.getText().trim().isEmpty()) {

            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Todos los campos son obligatorios.");
            return false;
        }

        if (!txtCorreo.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Formato de correo electrónico inválido.");
            return false;
        }

        return true;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}