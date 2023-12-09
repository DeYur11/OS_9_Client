package com.example.main;

public interface ClientListener {
     default void endAccepting() {};
     default void startVote(){};

}
