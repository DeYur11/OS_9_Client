package com.example.main;

import com.example.main.model.Idea;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.Vector;

public interface ClientListener {
     default void end() {};
     default void startVote(){};
     default void sendFinalMessage(Vector<Idea> bestIdeas){};
     default void nextStage(){};
     default void endWork() {
          Platform.runLater(new Runnable() {
               @Override
               public void run() {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Помилка з'єдання, сервер припинив свою роботу.");
                    alert.setTitle("Помилка");
                    alert.setHeaderText("Помилка з'єднання");
                    alert.showAndWait();
               }
          });
          try {
               Thread.sleep(3000);
          } catch (InterruptedException e) {
               throw new RuntimeException(e);
          }
          Platform.exit();
     }
}
