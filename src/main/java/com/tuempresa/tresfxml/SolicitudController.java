package com.tuempresa.tresfxml;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SolicitudController {

    @FXML private TextField txtCliente;
    @FXML private TextField txtCorreoCliente;
    @FXML private TextField txtTipoCliente;
    @FXML private TextField txtAsunto;
    @FXML private ComboBox<String> cbTipoServicio;
    @FXML private ToggleGroup tgPrioridad;
    @FXML private RadioButton rbBaja;
    @FXML private RadioButton rbMedia;
    @FXML private RadioButton rbAlta;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtAdjunto;
    @FXML private TextField txtEvidencias;

    private File archivoAdjunto;
    private File carpetaEvidencias;

    @FXML
    public void initialize() {
        cbTipoServicio.setItems(FXCollections.observableArrayList("Mantenimiento", "Reparación", "Instalación", "Consultoría"));
    }

    public void cargarDatosCliente(String cliente, String correo, String tipo) {
        txtCliente.setText(cliente);
        txtCorreoCliente.setText(correo);
        txtTipoCliente.setText(tipo);
    }

    @FXML
    private void seleccionarAdjunto(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Seleccionar Archivo Adjunto");
        archivoAdjunto = chooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (archivoAdjunto != null) {
            txtAdjunto.setText(archivoAdjunto.getAbsolutePath());
        }
    }

    @FXML
    private void seleccionarEvidencias(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Seleccionar Carpeta de Evidencias");
        carpetaEvidencias = chooser.showDialog(((Node) event.getSource()).getScene().getWindow());
        if (carpetaEvidencias != null) {
            txtEvidencias.setText(carpetaEvidencias.getAbsolutePath());
        }
    }

    @FXML
    private void guardar(ActionEvent event) {
        if (validarCampos()) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Solicitud de servicio registrada correctamente.");
        }
    }

    @FXML
    private void crearSolicitud(ActionEvent event) {
        if (validarCampos()) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Nueva Solicitud", "Solicitud generada con éxito.");
            limpiar(null);
        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
        txtCliente.clear();
        txtCorreoCliente.clear();
        txtTipoCliente.clear();
        txtAsunto.clear();
        cbTipoServicio.setValue(null);
        if (tgPrioridad.getSelectedToggle() != null) {
            tgPrioridad.getSelectedToggle().setSelected(false);
        }
        txtDescripcion.clear();
        txtAdjunto.clear();
        txtEvidencias.clear();
        archivoAdjunto = null;
        carpetaEvidencias = null;
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
        if (txtCliente.getText().trim().isEmpty() ||
                txtCorreoCliente.getText().trim().isEmpty() ||
                txtTipoCliente.getText().trim().isEmpty() ||
                txtAsunto.getText().trim().isEmpty() ||
                cbTipoServicio.getValue() == null ||
                tgPrioridad.getSelectedToggle() == null ||
                txtDescripcion.getText().trim().isEmpty() ||
                txtAdjunto.getText().trim().isEmpty() ||
                txtEvidencias.getText().trim().isEmpty()) {

            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Todos los campos obligatorios y selecciones deben completarse.");
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