package com.example.main;

import com.example.main.model.Idea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VoteWindow {
    private static ObservableList<Idea> selectedIdeas = FXCollections.observableArrayList();
    public static ObservableList<Idea> getSelectedIdeas(){
        return selectedIdeas;
    }

}
