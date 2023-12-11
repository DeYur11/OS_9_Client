package com.example.main.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serializable;

public class Idea implements Serializable {
    public static int ideaAmount = 0;
    public int ideaID;
    private String ideaText;
    private transient BooleanProperty isVoted = new SimpleBooleanProperty();
    private int ideaVotes;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Idea idea = (Idea) o;

        if (ideaID != idea.ideaID) return false;
        if (!ideaText.equals(idea.ideaText)) return false;
        return false;
    }

    public Idea(String ideaText) {
        ideaAmount++;
        this.ideaID = ideaAmount;
        this.ideaText = ideaText;
    }

    public void setIsVoted(BooleanProperty isVoted) {
        this.isVoted = isVoted;
        this.isVoted.set(false);
    }

    public static int getIdeaAmount() {
        return ideaAmount;
    }

    public static void setIdeaAmount(int ideaAmount) {
        Idea.ideaAmount = ideaAmount;
    }

    public int getIdeaID() {
        return ideaID;
    }

    public boolean isIsVoted() {
        return isVoted.get();
    }

    public BooleanProperty isVotedProperty() {
        return isVoted;
    }

    public int getIdeaVotes() {
        return ideaVotes;
    }

    public void setIdeaID(int ideaID) {
        this.ideaID = ideaID;
    }

    public String getIdeaText() {
        return ideaText;
    }

    public void setIdeaText(String ideaText) {
        this.ideaText = ideaText;
    }

    public void increaseVoteAmount(){
        this.ideaVotes++;
    }

    @Override
    public String toString() {
        return "Idea{" +
                "ideaID=" + ideaID +
                ", ideaText='" + ideaText + '\'' +
                ", ideaVotes=" + ideaVotes +
                '}';
    }
}
