package com.example.main;

import com.example.main.model.Idea;

import java.util.Vector;

public interface ClientListener {
     default void endAccepting() {};
     default void startVote(){};
     default void sendFinalMessage(Vector<Idea> bestIdeas){};

}
