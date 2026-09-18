package com.tuempresa.tresfxml.Controller;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.tuempresa.tresfxml.application.RegistroClienteApplication;
import com.tuempresa.tresfxml.model.Cliente;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class SolicitudServicioController {
    @FXML
    private TextField txtCliente;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTipoCliente;
    @FXML
    private TextField txtAsunto;
    @FXML
    private ComboBox<String> cmbServicio;
    @FXML
    private TextArea txtAreaDescripcion;
    @FXML
    private Button btnSelectArchivoServicio;
    @FXML
    private TextField txtArchivo;
    @FXML
    private Button btnEvidencias;
    @FXML
    private TextField txtEvidencias;

    @FXML
    private RadioButton btnRadioAlta;

    @FXML
    private RadioButton btnRadioMediana;

    @FXML
    private RadioButton btnRadioBaja;

    private ToggleGroup grupoPrioridad;

    private Cliente cliente;


    @FXML
    public void initialize() {


        cmbServicio.getItems().addAll(
                "Soporte técnico",
                "Mantenimiento",
                "Instalación",
                "Consultoría"
        );


        grupoPrioridad = new ToggleGroup();

        btnRadioAlta.setToggleGroup(grupoPrioridad);
        btnRadioMediana.setToggleGroup(grupoPrioridad);
        btnRadioBaja.setToggleGroup(grupoPrioridad);
    }


    public void recibirCliente(Cliente cliente){
        if(cliente == null){
            return;
        }
        this.cliente = cliente;
        txtCliente.setText(cliente.getNombre());
        txtCorreo.setText(cliente.getCorreo());
        txtTipoCliente.setText(cliente.getTipoCLiente());

    }
    public void clickLimpiarServicio(ActionEvent actionEvent) {
        txtCliente.clear();
        txtCorreo.clear();
        txtTipoCliente.clear();
        txtAsunto.clear();
        cmbServicio.getSelectionModel().clearSelection();
        txtAreaDescripcion.clear();
        txtEvidencias.clear();
        txtArchivo.clear();


    }

    public void clickSolicitud(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistroClienteApplication.class.getResource("/org/example/empresafx/registro-cliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Registro de Cliente");
        stage.setScene(scene);
        stage.show();
    }

    public void clickGuardarServicio(ActionEvent actionEvent) {
        if(!validateForm()) {
            return;
        }
        String informacionCliente;
        if(cliente != null){
            informacionCliente = "Datos del cliente: \n" ;
            informacionCliente += cliente.getNombre() + "\n" ;
            informacionCliente += cliente.getCorreo() + "\n" ;
            informacionCliente += cliente.getTipoCLiente() + "\n" ;
        }
        else{
            informacionCliente = "Datos del cliente: \n" + "Solicitud sin cliente";
        }
        String resultado = informacionCliente + "\n" + "Solicitud de servicio\n" +
                "Asunto: " + txtAsunto.getText() + "\n"+
                "Tipo de servicio: " + cmbServicio.getValue() + "\n" +
                "Prioridad: " + grupoPrioridad.getSelectedToggle()+ "\n" +
                "Descripcion del problema: " + txtAreaDescripcion.getText() + "\n" +
                "Archivo adjunto: " + txtArchivo.getText() + "\n"+
                "Carpeta de evidencia: " + txtEvidencias.getText() + "\n";

    }

    private boolean validateForm() {

        if (
                txtCliente.getText().isEmpty() ||
                        txtArchivo.getText().isEmpty() ||
                        txtEvidencias.getText().isEmpty() ||
                        txtCorreo.getText().isEmpty() ||
                        txtAsunto.getText().isEmpty() ||
                        txtTipoCliente.getText().isEmpty() ||
                        cmbServicio.getValue() == null ||
                        txtAreaDescripcion.getText().isEmpty() ||
                        grupoPrioridad.getSelectedToggle() == null
        ) {

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Formulario incompleto");
            alert.setHeaderText(null);

            alert.setContentText(
                    "Por favor, complete todos los campos antes de guardar."
            );

            alert.showAndWait();

            return false;
        }

        return true;
    }

    public void clickExaminarEvidencias(ActionEvent actionEvent) {

        DirectoryChooser dc = new DirectoryChooser();

        dc.setTitle("Seleccionar carpeta de evidencias");

        dc.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );

        File f = dc.showDialog(
                btnEvidencias.getScene().getWindow()
        );

        if (f != null) {
            txtEvidencias.setText(f.getAbsolutePath());
        }
    }

    public void clickExaminarArchivoServicio(ActionEvent actionEvent) {

        FileChooser fc = new FileChooser();

        fc.setTitle("Seleccionar archivo");

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"),
                new FileChooser.ExtensionFilter("Word", "*.docx"),
                new FileChooser.ExtensionFilter("Excel", "*.xlsx"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );

        File f = fc.showOpenDialog(
                btnSelectArchivoServicio.getScene().getWindow()
        );

        if (f != null) {
            txtArchivo.setText(f.getAbsolutePath());
        }
    }


}