package com.example.main;

import com.example.main.model.Idea;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class VoteWindow implements Initializable, ClientListener {
    private boolean canVote = true;
    private static ObservableList<Idea> selectedIdeas = FXCollections.observableArrayList();
    public static ObservableList<Idea> getSelectedIdeas(){
        return selectedIdeas;
    }
    private ObservableList<Idea> totalIdeas;
    @FXML
    private TableView<Idea> ideaTable;
    @FXML
    private TableColumn<Idea, Integer> ideaNumberColumn;
    @FXML
    private TableColumn<Idea, String> ideaTextColumn;
    @FXML
    private TableColumn<Idea, Boolean> ideaIsSelected;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalIdeas = MainWindow.getIdeaList();

        ideaIsSelected.setCellValueFactory( new PropertyValueFactory<>( "isVoted" ));
        ideaIsSelected.setCellFactory( tc -> new CheckBoxTableCell<>());

        ideaNumberColumn.setCellValueFactory(new PropertyValueFactory<Idea, Integer>("ideaID"));
        ideaTextColumn.setCellValueFactory(new PropertyValueFactory<Idea, String>("ideaText"));
        ideaTable.setItems(totalIdeas);
        ideaTable.setEditable(true);
        ClientSocketContainer.clientSocket.setClientListener(this);
    }
    @Override
    public void nextStage(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent mainWindow;
                try {
                    mainWindow = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FinalResult.fxml")));
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
    public void sendVotes(){
        ObservableList<Idea> checkedIdeas = FXCollections.observableArrayList();
        for(var i: totalIdeas){

            if(i.isIsVoted()){
                if(checkedIdeas.size() > 3){
                    return;
                }
                checkedIdeas.add(i);
            }
        }
        System.out.println(checkedIdeas);
        selectedIdeas.setAll(checkedIdeas);
    }
}

