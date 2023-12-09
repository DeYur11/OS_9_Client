module com.example.os_9_client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.main to javafx.fxml;
    opens com.example.main.model to javafx.fxml;
    exports com.example.main;
    exports com.example.main.model;
}