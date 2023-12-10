package com.example.main;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.main.model.Client;
import com.example.main.model.ClientSocket;
import com.example.main.model.Idea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindow implements Initializable, ClientListener {
    @FXML
    TextArea ideaText;
    @FXML
    static ObservableList<Idea> ideas = FXCollections.observableArrayList();

    @FXML
    private BorderPane root;
    @FXML
    TableView<Idea> ideaTable;
    @FXML
    private TableColumn<Idea, Integer> idColumn;
    @FXML
    private TableColumn<Idea, String> textColumn;

    public static ObservableList<Idea> getIdeaList(){
        return ideas;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Idea, Integer>("ideaID"));
        textColumn.setCellValueFactory(new PropertyValueFactory<Idea, String>("ideaText"));
        ideaTable.setItems(ideas);
        ClientSocketContainer.clientSocket.setClientListener(this);
    }

    @Override
    public void endAccepting() {}

    @Override
    public void startVote() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent mainWindow;
                try {
                    System.out.println("Loading next stage");
                    mainWindow = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("VoteWindow.fxml")));
                    Scene mainWindowsScene = new Scene(mainWindow);
                    Stage curStage = (Stage) ideaTable.getScene().getWindow();
                    curStage.setScene(mainWindowsScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    @FXML
    public void onAddIdea(){
        Idea ideaToSend = new Idea(ideaText.getText());
        ideas.add(ideaToSend);
        ClientSocketContainer.clientSocket.sendIdea(ideaToSend);
        ideaTable.refresh();
        ideaText.clear();
    }

}
