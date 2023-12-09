package com.example.main.model;

import java.io.Serializable;

public class Idea implements Serializable {
    public static int ideaAmount = 1;
    public int ideaID;
    private String ideaText;


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
        this.ideaID = ideaAmount;
        this.ideaText = ideaText;
        ideaAmount++;
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

    public void setIdeaID(int ideaID) {
        this.ideaID = ideaID;
    }

    public String getIdeaText() {
        return ideaText;
    }

    public void setIdeaText(String ideaText) {
        this.ideaText = ideaText;
    }

}
