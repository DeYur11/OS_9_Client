package com.example.os_9_client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import model.ClientSocket;
import model.Idea;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    @FXML
    public void onAddIdea(){
        Idea ideaToSend = new Idea(ideaText.getText(), new Client(ClientSocketContainer.clientSocket.getSocket()));
        ClientSocketContainer.clientSocket.sendIdea(ideaToSend);
    }
    @FXML
    TextArea ideaText;

    @FXML
    TableView ideaTable;

    @FXML
    public void addIdea(){

    }
    @FXML
    private TableColumn<Idea, Integer> idColumn;
    @FXML
    private TableColumn<Idea, String> textColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Idea, Integer>("id"));

    }
}
