package com.tuempresa.tresfxml.application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.tuempresa.tresfxml.application.HelloApplication;
import com.tuempresa.tresfxml.controller.RegistroController;

import java.io.IOException;

public class RegistroClienteApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(RegistroClienteApplication.class.getResource("/org/example/empresafx/registro-cliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Registro de Cliente");
        stage.setScene(scene);
        stage.show();
    }
}
