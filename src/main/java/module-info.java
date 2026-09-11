module com.tuempresa.tresfxml {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.tuempresa.tresfxml to javafx.fxml;
    exports com.tuempresa.tresfxml;
}