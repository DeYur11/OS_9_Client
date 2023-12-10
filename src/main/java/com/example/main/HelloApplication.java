package com.example.main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StartScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Client");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                ClientSocketContainer.clientSocket.sendDeathMessage();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}