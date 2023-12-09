package model;

public class Idea {
    public static int ideaAmount = 1;
    private int ideaID;
    private String ideaText;
    private Client ideaAuthor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Idea idea = (Idea) o;

        if (ideaID != idea.ideaID) return false;
        if (!ideaText.equals(idea.ideaText)) return false;
        return ideaAuthor.equals(idea.ideaAuthor);
    }

    public Idea(String ideaText, Client ideaAuthor) {
        this.ideaID = ideaAmount;
        this.ideaText = ideaText;
        this.ideaAuthor = ideaAuthor;
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

    public Client getIdeaAuthor() {
        return ideaAuthor;
    }

    public void setIdeaAuthor(Client ideaAuthor) {
        this.ideaAuthor = ideaAuthor;
    }
}
