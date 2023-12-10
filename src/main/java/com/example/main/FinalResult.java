package com.example.main;

import com.example.main.model.Idea;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;


public class FinalResult implements Initializable, ClientListener {
    private ObservableList<Idea> ideas = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Idea, Integer> idColumn;

    @FXML
    private TableView<Idea> ideaTable;

    @FXML
    private TableColumn<Idea, String> textColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClientSocketContainer.clientSocket.setClientListener(this);
        idColumn.setCellValueFactory(new PropertyValueFactory<Idea, Integer>("ideaID"));
        textColumn.setCellValueFactory(new PropertyValueFactory<Idea, String>("ideaText"));
        ideaTable.setItems(ideas);
    }

    @Override
    public void sendFinalMessage(Vector<Idea> bestIdeas){
        System.out.println("Applying ideas");
        System.out.println(bestIdeas);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ideas.setAll(bestIdeas);
                ideaTable.refresh();
            }
        });
    }
}
