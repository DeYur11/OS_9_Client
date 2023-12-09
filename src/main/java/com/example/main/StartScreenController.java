package com.example.main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.example.main.model.ClientSocket;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable, ClientListener {
    @FXML
    Button skip;

    @FXML
    StackPane parentContainer;

    @FXML
    BorderPane root;

    @Override
    public void endAccepting() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent mainWindow;
                try {
                    mainWindow = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainWindow.fxml")));
                    Scene mainWindowsScene = new Scene(mainWindow);
                    Stage curStage = (Stage) root.getScene().getWindow();
                    curStage.setScene(mainWindowsScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClientSocketContainer.clientSocket = new ClientSocket(this);
    }
}
