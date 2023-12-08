module com.example.os_9_client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.os_9_client to javafx.fxml;
    exports com.example.os_9_client;
}