package model.messages;

import model.Idea;

import java.io.Serializable;

public class newIdeaMessage implements Serializable {
    private final Idea newIdea;

    public newIdeaMessage(Idea newIdea) {
        this.newIdea = newIdea;
    }

    public Idea getNewIdea() {
        return newIdea;
    }
}