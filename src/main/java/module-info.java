module com.example.isstracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.isstracker to javafx.fxml;
    exports com.example.isstracker;
}