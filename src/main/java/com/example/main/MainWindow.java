package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.main.model.Client;
import com.example.main.model.ClientSocket;
import com.example.main.model.Idea;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    @FXML
    TextArea ideaText;
    @FXML
    ObservableList<Idea> ideas = FXCollections.observableArrayList();

    @FXML
    TableView<Idea> ideaTable;

    @FXML
    public void addIdea(){

    }
    @FXML
    private TableColumn<Idea, Integer> idColumn;
    @FXML
    private TableColumn<Idea, String> textColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Idea, Integer>("ideaID"));
        textColumn.setCellValueFactory(new PropertyValueFactory<Idea, String>("ideaText"));
        ideaTable.setItems(ideas);
    }

    @FXML
    public void onAddIdea(){
        Idea ideaToSend = new Idea(ideaText.getText());
        ideas.add(ideaToSend);
        ClientSocketContainer.clientSocket.sendIdea(ideaToSend);
        ideaTable.refresh();
    }
}
