package com.example.main;

import com.example.main.model.Idea;
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
        idColumn.setCellValueFactory(new PropertyValueFactory<Idea, Integer>("ideaID"));
        textColumn.setCellValueFactory(new PropertyValueFactory<Idea, String>("ideaText"));
        ideaTable.setItems(ideas);
        ClientSocketContainer.clientSocket.setClientListener(this);
    }

    @Override
    public void sendFinalMessage(Vector<Idea> bestIdeas){
        ideas.setAll(bestIdeas);

    }
}
